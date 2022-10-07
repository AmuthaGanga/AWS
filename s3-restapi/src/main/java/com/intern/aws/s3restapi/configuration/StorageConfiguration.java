package com.intern.aws.s3restapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfiguration {
	
	// generate the interface for accessing the Amazon S3 web service.
	@Bean
	public AmazonS3 s3() {
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		return AmazonS3ClientBuilder.standard()
		.withRegion(Regions.US_EAST_1)
		.withCredentials(credentialsProvider)
		.build();
	}
}
