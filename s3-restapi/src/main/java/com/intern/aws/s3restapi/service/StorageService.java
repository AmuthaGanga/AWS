package com.intern.aws.s3restapi.service;

import static com.intern.aws.s3restapi.constants.S3UploadConstant.STATUS_UPLOADED;
import static com.intern.aws.s3restapi.constants.S3UploadConstant.UPLOAD_SUCCESS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.intern.aws.s3restapi.exception.UnableToProcessInputFileException;
import com.intern.aws.s3restapi.model.CustomResponse;
import com.intern.aws.s3restapi.model.ObjectData;

@Service
public class StorageService {
	
	private Logger logger = LoggerFactory.getLogger(StorageService.class);
	
	@Autowired
	private AmazonS3 s3;
	
	@Value("${bucketName}")
	private String bucketName;
	
	/**
	 * Method to upload the file from REST Client
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
	public ResponseEntity<CustomResponse> saveFile(MultipartFile file, String key) {
		String fileName = null;
		ResponseEntity<CustomResponse> responseEntity = null;
		
		try {
			if(null != key) {
				fileName = key;
			}else {
				fileName = file.getOriginalFilename() + LocalDateTime.now();
			}
			logger.info("file name to be uploaded ==> " + fileName);
			
			// adding meta data of the object - optional
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.addUserMetadata("author","Java Intern");
            metadata.addUserMetadata("version","1.0.0.0");
            
            //convert the multipart file from the request to file
            File inputFile = covertMultipartToFile(file);
            FileInputStream ipStream = new FileInputStream(inputFile);
			
			
			PutObjectResult objectResult = s3.putObject(bucketName, fileName, ipStream, metadata);
			
			// generating a S3 pre-signed URL
			URL presignedUrl = s3.generatePresignedUrl(bucketName, fileName, getNextDay());
			
	//		CloudFrontUrlSigner.getSignedURLWithCannedPolicy(fileName, key, null, getNextDay())
			
			//constructing response back to REST Client
			ObjectData data = new ObjectData(objectResult.getETag(), presignedUrl);
			CustomResponse response = new CustomResponse(STATUS_UPLOADED, UPLOAD_SUCCESS, new Date(), data);
			responseEntity = new ResponseEntity<CustomResponse>(response, HttpStatus.OK);
		}catch (SdkClientException sdkException) {
			logger.error(sdkException.toString());
			throw sdkException;
		}catch (IOException ioe) {
			logger.error(ioe.toString());
			throw new UnableToProcessInputFileException("Unable to process the Input File. Retry with a correct file!");
		}catch(Exception e) {
			throw e;
		}
		
		return responseEntity;
	}
	
	/**
	 * Method to convert the Multipart file to normal file object
	 * @param file
	 * @return
	 */
	private File covertMultipartToFile(MultipartFile file) throws IOException{
		File convertedFile = null;
		convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
	
		return convertedFile;
	}
	
	/**
	 * Method to get the next day date
	 * 
	 * @return
	 */
	private Date getNextDay() {
		long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

		Date today = new Date();
		long nextDayMilliSeconds = today.getTime() + MILLIS_IN_A_DAY;
		Date nextDate = new Date(nextDayMilliSeconds);
		return nextDate;
	}
	
	
}
