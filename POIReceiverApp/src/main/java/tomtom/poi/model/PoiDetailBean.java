/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.model;

import java.io.Serializable;

/**
 * Purpose:POIBean hold all the details which require for POI.
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
public class PoiDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Tag is nothing but famous name given to that POI
	private String categoryName;

	private PoiBean poi;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public PoiBean getPoi() {
		return poi;
	}

	public void setPoi(PoiBean poi) {
		this.poi = poi;
	}

}
