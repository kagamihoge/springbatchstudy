package com.example.kagamihoge.springbatchstudy.withrest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppWeb {
    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    Job job1;

    @Autowired
    Job job2;
    
    @RequestMapping("/job1")
    String requestJob1() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
        jobLauncher.run(job1, createInitialJobParameterMap());
        return "Job1!";
    }

    @RequestMapping("/job2")
    String requestJob2() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
        jobLauncher.run(job2, createInitialJobParameterMap());
        return "Job2!";
    }
    
    private JobParameters createInitialJobParameterMap() {
        Map<String, JobParameter> m = new HashMap<>();
        m.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters p = new JobParameters(m);
        return p;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(AppWeb.class, args);
    }
}
