/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.dao;

import java.util.List;

import tomtom.poi.exception.POIDaoException;
import tomtom.poi.jpa.Category;
import tomtom.poi.jpa.POI;
import tomtom.poi.jpa.POIDetails;

/**
 * Purpose:
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
public interface IPOIDao {

	public List<POI> getFuturePOI(POI poi) throws POIDaoException;

	public POI getPOIDetailsCount(String latitude, String longitude);

	public Category getCategory(String categoryName);

	public String savePOIDetails(POIDetails poiDetails) throws POIDaoException;

	public List<Category> getCategory();

}
