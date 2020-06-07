package com.olx.grpc.service.businesslogic;

import java.util.List;

import com.olx.grpc.service.models.Log;

public interface LogManager {
	
	List<Log> getAllLogs();
	
	Log save(Log log);

}
