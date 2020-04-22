package com.facialapp.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.facialapp.model.ImagesModel;

public class CompareService {

	private String bucketName = "facialrecogntion";
	ImagesModel iM;

	public CompareService() {	}
	
	public List<Float> compareImages(String imageName1, String imageName2, String imageName3) {

		List<Float> confid = new ArrayList<>();

		BasicAWSCredentials awsCreds = new BasicAWSCredentials("YOUR  ACCESS KEY ID", "YOUR SECRET ACCESS KEY");

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2).build();

		// Load source and target images and create input parameters
		CompareFacesRequest request1 = new CompareFacesRequest()
				.withSourceImage(new Image()
						.withS3Object(new S3Object().withName(imageName1).withBucket(bucketName)))
				.withTargetImage(new Image()
						.withS3Object(new S3Object().withName(imageName2).withBucket(bucketName)))
				.withSimilarityThreshold(0F);

		// Call operation
		CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request1);
		// Display results
		List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
		for (CompareFacesMatch match : faceDetails) {
			ComparedFace face = match.getFace();
			BoundingBox position = face.getBoundingBox();
			System.out.println("Face at " + position.getLeft().toString() + " " + position.getTop() + " matches with "
					+ match.getSimilarity().toString() + "% confidence.");
			confid.add(match.getSimilarity());
		}

		CompareFacesRequest request2 = new CompareFacesRequest()
				.withSourceImage(new Image()
						.withS3Object(new S3Object().withName(imageName2).withBucket(bucketName)))
				.withTargetImage(new Image()
						.withS3Object(new S3Object().withName(imageName3).withBucket(bucketName)))
				.withSimilarityThreshold(0F);

		// Call operation
		compareFacesResult = rekognitionClient.compareFaces(request2);
		// Display results
		faceDetails = compareFacesResult.getFaceMatches();
		for (CompareFacesMatch match : faceDetails) {
			ComparedFace face = match.getFace();
			BoundingBox position = face.getBoundingBox();
			System.out.println("Face at " + position.getLeft().toString() + " " + position.getTop() + " matches with "
					+ match.getSimilarity().toString() + "% confidence.");
			confid.add(match.getSimilarity());
		}
		return confid;
	}
}
