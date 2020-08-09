/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tomtom.poi.exception.DocumentStorageException;
import tomtom.poi.exception.POIException;
import tomtom.poi.model.CategoryBean;
import tomtom.poi.model.PoiBean;
import tomtom.poi.model.PoiDetailBean;

/**
 * Purpose:
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
public interface IPOIService {

	/**
	 * Get the list of POI based on some condition.
	 * 
	 * @param bean
	 * @return
	 * @throws POIException
	 */
	public List<PoiBean> getFuturePOI(PoiBean bean) throws POIException;

	public String uploadPOI(MultipartFile file, PoiDetailBean bean) throws DocumentStorageException, POIException;
	
	public List<CategoryBean> getCategory();

}
