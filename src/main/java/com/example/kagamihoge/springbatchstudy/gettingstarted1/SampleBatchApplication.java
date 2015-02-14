package com.example.kagamihoge.springbatchstudy.gettingstarted1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SampleBatchApplication {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job job(JobBuilderFactory jobs, Step s1, Step s2) {
        return jobs
                .get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(s1)
                .next(s2)
                .build();
    }

    @Bean(name = "s1")
    public Step step1() {
        return steps.get("step1").tasklet((stepContribution, chunkContext) -> {
            System.out.println("step 1");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean(name = "s2")
    public Step step2() {
        return steps.get("step2").tasklet((stepContribution, chunkContext) -> {
            System.out.println("step 2");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
