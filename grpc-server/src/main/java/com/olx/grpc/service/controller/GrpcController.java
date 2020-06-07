package com.olx.grpc.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olx.grpc.service.businesslogic.LogManager;
import com.olx.grpc.service.models.Log;


@RestController
public class GrpcController {
	
	@Autowired
	private LogManager logManager;

	@RequestMapping(value = "logs", method = RequestMethod.GET)
	public List<Log> gelAllLogs() {
		return logManager.getAllLogs();
	}
}
