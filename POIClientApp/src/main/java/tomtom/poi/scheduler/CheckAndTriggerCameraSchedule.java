/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.scheduler;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import tomtom.poi.dao.CommonDao;
import tomtom.poi.jpa.POI;
import tomtom.poi.model.PoiBean;

/**
 * Purpose:Call after every 1 sec, scheduler will call to server to get the
 * future POI details.
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
@Component
@Transactional
@Repository
public class CheckAndTriggerCameraSchedule extends CommonDao {

	private static Logger logger = Logger.getLogger(CheckAndTriggerCameraSchedule.class.getName());

	@Value("${radius.value}")
	private String radius;

	@Value("${poi.receiver.app.poi}")
	private String url;

	// Run the task after 1 sec
	// @Scheduled(fixedRate = 1000, initialDelay=500)
	public void scheduleTaskWithFixRate() {

		// Step 1 - Get the current location set to bean
		// TODO - Get the correct location.
		PoiBean bean = new PoiBean();	
		bean.setLatitude("");
		bean.setLongitude("");
		bean.setRadius(Integer.parseInt(radius));

		// Step 2 - Get the future POI details from local history
		// TODO - not found matching result then call to further
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(POI.class);
		criteria.add(Restrictions.eq("active", 1));
		List<POI> checkList = (List<POI>) criteria.list();

		if (checkList == null || checkList.size() == 0) {
			// Step 3 - Not found POI at local DB then get the POI from server
			RestTemplate restTemplate = new RestTemplate();
			// String result = restTemplate.getForObject(url, String.class);

			ResponseEntity<List<POI>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<POI>>() {
					});
			checkList = responseEntity.getBody();
		}

		// Step 4 -Trigger the once location matches very close to POI co-ordinates.

	}
}
