package com.example.SpringBatchSample.MultiThread;

import java.util.concurrent.Callable;

public class AdditionTask implements Callable<Integer> {

	private Integer input;
	
	public AdditionTask(Integer input) {
		this.input = input;
	}
	
	@Override
	public Integer call() {
		// macbookproM2の2022モデル(8GB)
		// 並列化しない場合
		// 100000000×10で3秒+-1前後
		// 100000000×100で20秒+-1前後
		// 4並列の場合
		// 100000000×100で10秒を切る時もあれば30秒かかる時もある
		
		for (int i = 0; i <= 1000000000; i++) {
			input++;
		}
		
		return input;
	}
	

	
}
