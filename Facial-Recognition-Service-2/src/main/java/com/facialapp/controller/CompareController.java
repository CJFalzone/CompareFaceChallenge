package com.facialapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facialapp.model.ImagesModel;
import com.facialapp.service.CompareService;

@RestController("CompareController")
@RequestMapping("/upload")
@CrossOrigin("*")
public class CompareController {
	
	CompareService cServ = new CompareService();
	

	@PostMapping(value = "/compare")
	public List<Double> compareImage(@RequestBody ImagesModel iM) throws IOException {
		
		List<Float> confid = new ArrayList<>();

		String imageName1 = iM.getImageName1();
		String imageName2 = iM.getImageName2();
		String imageName3 = iM.getImageName3();
		confid = cServ.compareImages(imageName1, imageName2, imageName3);
		
		List<Double> confid2 = new ArrayList<>();
		confid2.add(confid.get(0).doubleValue());
		confid2.add(confid.get(1).doubleValue());
		return confid2;
	}
}
