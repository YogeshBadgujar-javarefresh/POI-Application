/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.scheduler;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import tomtom.poi.dao.CommonDao;
import tomtom.poi.jpa.Category;
import tomtom.poi.model.CategoryBean;

/**
 * Purpose:
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
public class SynchCategorySchedule extends CommonDao {

	@Value("${poi.receiver.app.category}")
	private String url;

	private static Logger logger = Logger.getLogger(SynchCategorySchedule.class.getName());

	// Testing
	//@Scheduled(fixedRate = 1000, initialDelay = 500)
	//Run the cron job, every day - 1.01 am in the morning
	@Scheduled(cron = "0 1 1 * * ?")
	public void scheduleTaskWithFixRate() {

		RestTemplate restTemplate = new RestTemplate();
		// String result = restTemplate.getForObject(url, String.class);

		ResponseEntity<List<CategoryBean>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CategoryBean>>() {
				});
		List<CategoryBean> categoryBeanList = responseEntity.getBody();

		//TODO - Now update the category into local DB
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Category.class);

	}

}
