package com.example.SpringBatchSample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
 * SampleJob設定クラス
 * SpringBatchのバージョンアップにより実装方法が変わりこの内容では動かなそう。非推奨にもなっている。
 */
@Configuration
@EnableBatchProcessing
public class SampleJobConfig {
	
	public static final String JOB_NAME = "sampleJob";
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job sampleJob() throws Exception {
		return jobBuilderFactory.get(JOB_NAME).start(firstStep()).build();
	}
	
	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("firstStep").tasklet(firstTasklet()).build();
	}
	
	@Bean
	public MethodInvokingTaskletAdapter firstTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(firstService());
		adapter.setTargetMethod("execute");
		return adapter;
	}
	
	@Bean
	public FirstService firstService() {
		return new FirstServiceImpl();
	}
}
