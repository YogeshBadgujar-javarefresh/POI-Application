/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tomtom.poi.exception.DocumentStorageException;
import tomtom.poi.exception.POIException;
import tomtom.poi.model.CategoryBean;
import tomtom.poi.model.PoiBean;
import tomtom.poi.model.PoiDetailBean;
import tomtom.poi.service.IPOIService;

/**
 * Purpose:
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
@RestController
public class POIRest {

	@Autowired
	IPOIService poiService;

	@RequestMapping(value = "/poi", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public List<PoiBean> getPois(@RequestBody PoiBean poiBean) throws POIException {
		return poiService.getFuturePOI(poiBean);
	}

	//@RequestMapping(value = "/uploadPoiDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PostMapping("/uploadPoiDetails")
	public String uploadPOIDetails(@RequestParam("file") MultipartFile file,
			@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude,
			@RequestParam("categoryName") String categoryName) throws DocumentStorageException, POIException {
		
		PoiDetailBean bean = new PoiDetailBean();
		bean.setCategoryName(categoryName);
		PoiBean poi = new PoiBean(); 
		poi.setLatitude(latitude);
		poi.setLongitude(longitude);
		bean.setPoi(poi);
		return poiService.uploadPOI(file, bean);
	}
	
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryBean> getCategory() throws POIException {
		return poiService.getCategory();
	}

}
