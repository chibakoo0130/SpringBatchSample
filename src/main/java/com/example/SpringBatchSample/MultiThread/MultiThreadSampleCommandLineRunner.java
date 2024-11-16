package com.example.SpringBatchSample.MultiThread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MultiThreadSampleCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		LocalDateTime startDateTime = LocalDateTime.now();
		System.out.println(startDateTime);
		List<Integer> list = new ArrayList<>();
		List<Integer> resultList = new ArrayList<>();
		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
			tasks.add(new AdditionTask(i));
		}
		
		try {
			List<Future<Integer>> results = executor.invokeAll(tasks);
			
			for (Future<Integer> result: results) {
				resultList.add(result.get());
			} 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		LocalDateTime endDateTime = LocalDateTime.now();
		System.out.println(endDateTime);
		System.out.println(resultList);
	}
}
