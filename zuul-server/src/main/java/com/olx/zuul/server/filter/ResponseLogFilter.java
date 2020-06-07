package com.olx.zuul.server.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.olx.zuul.server.grpc.Log.Event;
import com.olx.zuul.server.grpc.LogingEventGrpc;
import com.olx.zuul.server.grpc.LogingEventGrpc.LogingEventBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class ResponseLogFilter extends ZuulFilter {
	
	private final String authService = "AUTH SERVICE";
	private final String userService = "USER SERVICE";
	private final String itemService = "ITEM SERVICE";
	private final String messageService = "MESSAGE SERVICE";
	private final String transactionService = "TRANSACTION SERVICE";
	private static final String X_LOGIN = "X-Login";
    
	@Autowired
    private EurekaClient client;
	
    @Override
    public String filterType() {
        return "post";
    }
 
    @Override
    public int filterOrder() {
        return 100;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() throws ZuulException {
    	
    	RequestContext context = RequestContext.getCurrentContext();
    	HttpServletRequest request = context.getRequest();
    	HttpServletResponse response = context.getResponse();
    	
    	//timestamp
    	Long timestamp = System.currentTimeMillis();
    	
    	//microserviceName and resurs
    	String microserviceName = authService;
    	String resurs= "Token";
    	String path = request.getRequestURI();
    	
    	if(path.contains("userservice")) {
    		microserviceName = userService;
    		resurs = "User";
    	}
    	else if(path.contains("itemservice")) {
    		microserviceName = itemService;
    		
    		if(path.contains("category"))
    			resurs = "Category";
    		else if(path.contains("user"))
    			resurs = "User";
    		else 
    			resurs = "Product";
    	}
    	else if(path.contains("messageservice")) {
    		microserviceName =messageService;
    		if(path.contains("chat/"))
    			resurs = "Chat";
    		else 
    			resurs = "User";
    		
    	}
    	else if(path.contains("transactionservice")) {
    		microserviceName = transactionService;
    		if(path.contains("products/"))
    			resurs = "Product";
    		else if(path.contains("user/"))
    			resurs = "User";
    		else 
    			resurs = "Transaction";
    	}
    	else if(path.contains("grpcservice")) {
    		microserviceName = "GRPC SERVICE";
    		resurs = "Log";
    	}
    	
    	//methodType
    	String methodType = request.getMethod();
    	
    	//statusCode
    	int statusCode = response.getStatus();
    	
    	//body
    	String bodyResponse ="";
    	InputStream responseDataStream = context.getResponseDataStream();
    	try {
    		bodyResponse = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
    	    context.setResponseBody(bodyResponse);
    	} catch (IOException e) {
    		
    	}
    	
    	//username
    	String username="guest";
    	String header = request.getHeader("Authorization");
    	if(header != null) {
    		String token = header.replace("Bearer ", "");
        	username=token;
       	} 
    	
    	if (microserviceName.equalsIgnoreCase(authService) &&  statusCode == 200) {
    		try {
				Res res = new ObjectMapper().readValue(bodyResponse, Res.class);
				username = res.getName();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
 
    	
    	//GRPC
    	if(!microserviceName.equalsIgnoreCase("GRPC SERVICE")) {
    		InstanceInfo instanceInfo = client.getNextServerFromEureka("grpc-service", false);
    		ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), 6565).usePlaintext().build();
        	LogingEventBlockingStub stub = LogingEventGrpc.newBlockingStub(channel);
        	Event log = Event.newBuilder()
        			.setTimestamp(timestamp)
        			.setUsername(username)
        			.setMicroserviceName(microserviceName)
        			.setMethodType(methodType)
        			.setResurs(resurs)
        			.setStatusCode(statusCode)
        			.setBodyResponse(bodyResponse)
        			.build();
        	stub.logEvent(log);
        	channel.shutdown();
    	}
    	
    	
    	Log log2 = new Log(timestamp, username, microserviceName, methodType, resurs, statusCode, bodyResponse);
    	
    	System.out.println(log2.toString());
    	
    	return response;
    }
}
