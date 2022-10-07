package com.intern.s3;

import java.io.File;
import java.text.DecimalFormat;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class UploadVideo {
	
	public static long totalBytesTransferred = 0;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
		String bucketName = "optisol-intern-images-videos";
        String keyName    = "Jellyfish.mp4";
        String filePath   = "D:\\Udemy\\AWS\\S3 files\\Jellyfish.mp4"; 
        TransferManager tm = null;
        
        try {
			AmazonS3 amazonS3 = AmazonS3ClientBuilder
					  			.standard()
					  			.withCredentials(new DefaultAWSCredentialsProviderChain())
					  			.withRegion(Regions.US_EAST_1)
					  			.build();
			
			tm = TransferManagerBuilder.standard()
					  				.withS3Client(amazonS3)
					  				.withMultipartUploadThreshold((long) (5 * 1024 * 1025))
					  				.build();
			
			File file = new File(filePath);
	        // size of a file (in bytes)
	        final long totalBytes = file.length();
			
	        System.out.println("Uploading Video to S3 bucket...");
			Upload upload = tm.upload(bucketName, keyName, file);
			
			// adding listener for the upload progress
			upload.addProgressListener(new ProgressListener() {
				public void progressChanged(ProgressEvent progressEvent) {
					long partUploadBytes = progressEvent.getBytesTransferred();
					totalBytesTransferred = totalBytesTransferred + partUploadBytes;
					double temp = (double)totalBytesTransferred/totalBytes;
					System.out.println("Uploaded percentage :: " + df.format(temp*100) + "%");
				}
	
			});
			
			// You can block and wait for the upload to finish
	    	upload.waitForCompletion();
	    	if(upload.isDone()) {
	    		tm.shutdownNow();
	    		System.out.println("Upload of the video is completed successfully!!");
	    	}
        }catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
			System.out.println("Unable to upload file, upload aborted.");
        	tm.shutdownNow();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unable to upload file, upload aborted.");
        	tm.shutdownNow();
		}
	}

}
