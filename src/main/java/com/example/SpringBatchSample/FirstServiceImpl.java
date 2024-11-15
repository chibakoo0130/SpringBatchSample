package com.example.SpringBatchSample;

import org.springframework.batch.core.ExitStatus;

public class FirstServiceImpl implements FirstService {

	@Override
	public ExitStatus execute() {
		System.out.println("first step job");
		return ExitStatus.COMPLETED;
	}

}
