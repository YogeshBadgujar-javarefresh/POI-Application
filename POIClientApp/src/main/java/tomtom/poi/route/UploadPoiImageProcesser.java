/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

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
public class UploadPoiImageProcesser extends RouteBuilder {

	public static final String ROUTE_NAME = "TIMER_ROUTE";

	@Override
	public void configure() throws Exception {
		// from("file:C://inputFolder?noop=true").to("file:C://outputFolder");
		System.out.println("Testing....ImageProcesser");
		from("timer:initial//start?period=100").routeId(ROUTE_NAME).to("log:executed");

		// Step 1 - Pull the file from images directory

		// Step 2 - Extract the coordinates from the Image.

		// Step 3 - Validate the image using Google Vision AI
		// https://cloud.google.com/vision/?utm_source=google&utm_medium=cpc&utm_campaign=japac-IN-all-en-dr-skws-all-all-trial-b-dr-1009137&utm_content=text-ad-none-none-DEV_m-CRE_252507513762-ADGP_Hybrid+%7C+AW+SEM+%7C+SKWS+~+T1+%7C+BMM+%7C+ML+%7C+M:1+%7C+IN+%7C+en+%7C+Vision+%7C+Recognition-KWID_43700030478059714-kwd-39779487698&userloc_9040229-network_g&utm_term=KW_%2Bimage%20%2Brecognition&ds_rl=1264446&gclid=CJqUt5TThOsCFUNzjgodXa4Ncg

		// Step 4 - Get all the categories if matches then send the file to server.

		// Step 4 - If file not matches in step 4 then check with drool rules if matches
		// then upload the file to server.

		// Step 5 - Move the file to another folder (there will be another scheduler
		// that will delete depending on the setting).

	}
}
