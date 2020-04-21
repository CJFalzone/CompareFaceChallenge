package com.facialapp.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.facialapp.model.ImagesModel;
import com.facialapp.service.MultipartConverter;
import com.facialapp.service.UploadToS3;

@RestController("InputImageController")
@RequestMapping("/upload")
@CrossOrigin("*")
public class InputImageController {

	MultipartConverter mConv = new MultipartConverter();
	UploadToS3 uTS3 = new UploadToS3();

	public InputImageController() {	}

	@PostMapping(value = "/addimages")
	public List<Double> addImage(@RequestParam("imageFile1") MultipartFile image1,
			@RequestParam("imageFile2") MultipartFile image2, @RequestParam("imageFile3") MultipartFile image3)
			throws IOException {
		try {

			File file1 = mConv.convertMultiPartToFile(image1);
			File file2 = mConv.convertMultiPartToFile(image2);
			File file3 = mConv.convertMultiPartToFile(image3);

			uTS3.uploadToS3(file1, file2, file3, image1.getOriginalFilename(), image2.getOriginalFilename(),
					image3.getOriginalFilename());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ImagesModel iM = new ImagesModel();
		iM.setImageName1(image1.getOriginalFilename());
		iM.setImageName2(image2.getOriginalFilename());
		iM.setImageName3(image3.getOriginalFilename());

//		final String uri = "http://localhost:9884/upload/compare";
		final String uri = "http://ec2-18-216-87-68.us-east-2.compute.amazonaws.com:9884/upload/compare";
		RestTemplate restTemplate = new RestTemplate();
		List<Double> confid = restTemplate.postForObject(uri, iM, List.class);
		
		return confid;
	}
}
