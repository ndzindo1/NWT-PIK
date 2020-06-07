package com.olx.grpc.service.grpc;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import com.olx.grpc.service.businesslogic.LogManager;
import com.olx.grpc.service.grpc.Log.Empty;
import com.olx.grpc.service.grpc.Log.Event;

import io.grpc.stub.StreamObserver;

@GRpcService
public class LoginEventImp extends LogingEventGrpc.LogingEventImplBase {
	
	@Autowired
	LogManager logManager;

	@Override
	public void logEvent(Event request, StreamObserver<Empty> responseObserver) {
		
		logManager.save(new com.olx.grpc.service.models.Log(request.getTimestamp(), 
				request.getUsername(), 
				request.getMicroserviceName(), 
				request.getMethodType(), 
				request.getResurs(), 
				request.getStatusCode(), 
				request.getBodyResponse()));
		
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}
	
}
