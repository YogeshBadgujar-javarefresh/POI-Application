/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.exception;

/**
 * Purpose:If any exception happen at run time then it will throw POIException.
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
public class POIException extends Exception {

	private static final long serialVersionUID = 1L;

	public POIException(String message) {
		super(message);
	}

}
