////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com;

import org.apache.spark.ml.feature.RFormula;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.regression.LinearRegressionTrainingSummary;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.time.LocalDate;
import java.util.TimeZone;
import java.util.concurrent.Executor;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.to_timestamp;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.logistica.repositories", "com.configuration.security.repositories"})
@EnableAsync
@EnableAspectJAutoProxy
public class LogisticaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticaApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("AsyncMethodTest-");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /*   @Bean
       public Jackson2ObjectMapperBuilder objectMapperBuilder() {
           Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
           builder.serializationInclusion(JsonInclude.Include.NON_NULL);
           return builder;
       }*/

}
