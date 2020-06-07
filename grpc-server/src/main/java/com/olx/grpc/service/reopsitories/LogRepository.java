package com.olx.grpc.service.reopsitories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.olx.grpc.service.models.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
	
	List<Log> findByBodyResponseContaining(String bodyResponse);

}