/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
@Repository
@Component
public class POIDaoImpl extends CommonDao implements IPOIDao {

	private static Logger logger = Logger.getLogger(POIDaoImpl.class.getName());
	private final static String STATUS = "sucess";

	public List<POI> getFuturePOI(POI poi) throws POIDaoException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(POI.class);
		criteria.add(Restrictions.eq("active", 1));
		logger.info("POI list size -->" + criteria.list().size());
		return (List<POI>) criteria.list();
	}

	/**
	 * Get the POI Details to store the file with the unique file name.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws POIDaoException
	 */
	public POI getPOIDetailsCount(String latitude, String longitude) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(POI.class);
		criteria.add(Restrictions.eq("latitude", latitude));
		criteria.add(Restrictions.eq("longitude", longitude));
		criteria.add(Restrictions.eq("active", 1));
		POI poi = (POI) criteria.uniqueResult();
		return poi;
	}

	public Category getCategory(String categoryName) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("name", categoryName));
		criteria.add(Restrictions.eq("active", 1));
		return (Category) criteria.uniqueResult();
	}

	public String savePOIDetails(POIDetails poiDetails) throws POIDaoException {
		getSessionFactory().getCurrentSession().save(poiDetails);
		return STATUS;
	}

	public List<Category> getCategory() {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Category.class);
		criteria.add(Restrictions.eq("active", 1));
		return (List<Category>) criteria.list();
	}

}
