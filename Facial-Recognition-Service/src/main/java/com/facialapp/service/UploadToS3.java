package com.facialapp.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadToS3 {

	private AmazonS3 s3;
	private String bucketName = "facialrecogntion";

	public UploadToS3() {	}
	
	public void uploadToS3(File file1, File file2, File file3, String file1Name, String file2Name, String file3Name) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("YOUR  ACCESS KEY ID", "YOUR SECRET ACCESS KEY");

		s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.US_EAST_2).build();

		s3.putObject(new PutObjectRequest(bucketName, file1Name, file1)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		file1.delete();
		s3.putObject(new PutObjectRequest(bucketName, file2Name, file2)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		file2.delete();
		s3.putObject(new PutObjectRequest(bucketName, file3Name, file3)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		file3.delete();
	}
	
}
