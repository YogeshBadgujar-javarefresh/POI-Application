/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tomtom.poi.dao.IPOIDao;
import tomtom.poi.exception.DocumentStorageException;
import tomtom.poi.exception.POIDaoException;
import tomtom.poi.exception.POIException;
import tomtom.poi.jpa.Category;
import tomtom.poi.jpa.POI;
import tomtom.poi.jpa.POIDetails;
import tomtom.poi.model.CategoryBean;
import tomtom.poi.model.PoiBean;
import tomtom.poi.model.PoiDetailBean;
import tomtom.poi.util.POIUtil;

/**
 * Purpose:
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
@Service
@Transactional // for future
public class POIServiceImpl implements IPOIService {

	@Autowired
	IPOIDao poiDao;

	@Value("${file.upload.folder.dir}")
	private String folderName;

	@Value("${file.upload.folder.dir}")
	private String uploadDir;

	// TODO - Depending on logic need to fetch only given radius POI but currently
	// we are pulling all the records
	public List<PoiBean> getFuturePOI(PoiBean bean) throws POIException {
		List<PoiBean> pois = new ArrayList<>();
		try {
			// TODO set the POI to filter based on criteria, currently null.
			List<POI> poiList = poiDao.getFuturePOI(null);
			if (!poiList.isEmpty()) {
				poiList.stream().forEach(x -> {
					PoiBean tempBean = new PoiBean();
					tempBean.setName(x.getName());
					tempBean.setLatitude(x.getLatitude());
					tempBean.setLongitude(x.getLongitude());
					pois.add(tempBean);
				});
			}
		} catch (POIDaoException e) {
			throw new POIException(POIUtil.buildMessageWithCause(e.getCause()));
		}

		return pois;
	}

	public String uploadPOI(MultipartFile file, PoiDetailBean bean) throws DocumentStorageException, POIException {

		// Check 1 - Got the correct latitude and longitude further upload
		String latitude = bean.getPoi().getLatitude();
		String longitude = bean.getPoi().getLongitude();
		POI poi = poiDao.getPOIDetailsCount(latitude, longitude);
		int count = 0;
		if (poi == null) {
			return "Fail";
		} else if (poi.getPoiDetails() != null) {
			count = poi.getPoiDetails().size();
		}

		// Check 2 - Check the category
		Category category = poiDao.getCategory(bean.getCategoryName());

		// Check 3 - Check the Drool details.
		if (category == null) {
			return "Fail";
		}

		// Normalize file name
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = "";
		try {
			// Check if the file's name contains invalid characters
			if (originalFileName.contains("..")) {
				throw new DocumentStorageException("Filename contains invalid path sequence " + originalFileName);
			}
			String fileExtension = "";
			try {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			} catch (Exception e) {
				fileExtension = "";
			}
			fileName = latitude + "_" + longitude + "_" + (count + 1) + fileExtension;
			String fullDirPath = System.getProperty(uploadDir) + folderName;
			Path path = Paths.get(uploadDir, fileName);
			Files.write(path, file.getBytes());

			// Save POI Details to DB
			POIDetails poiDetails = new POIDetails();
			poiDetails.setFileName(fileName);
			poiDetails.setUploadDir(fullDirPath);
			poiDetails.setPoi(poi);
			poiDetails.setCategory(category);
			// TODO - Need to extra data from JWT token, such as user details.
			poiDetails.setCreatedBy("Yogesh");
			poiDetails.setCreatedDate(new Date());
			poiDetails.setModifyBy("Yogesh");
			poiDetails.setModifyDate(new Date());
			poiDetails.setVersion(count + 1);

			poiDao.savePOIDetails(poiDetails);

		} catch (IOException ex) {
			throw new DocumentStorageException("Could not store file " + fileName + ". Please try again!", ex);
		} catch (POIDaoException e) {
			throw new POIException(POIUtil.buildMessageWithCause(e.getCause()));
		}
		return "Success";
	}

	public List<CategoryBean> getCategory() {
		List<CategoryBean> categoryBeanList = new ArrayList<>();
		List<Category> categoryList = poiDao.getCategory();
		categoryList.stream().forEach(x -> {
			CategoryBean bean = new CategoryBean();
			bean.setName(x.getName());
			categoryBeanList.add(bean);	
		});
		return categoryBeanList;
	}

}
