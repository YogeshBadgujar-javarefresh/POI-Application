/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.exception;

/**
 * Purpose:While upload the file it might give exception
 * DocumentStorageException.
 *
 * Description:
 *
 * @author Yogesh Badgujar email me in case any problem -
 *         badgujar.yogesh@gmail.com
 *
 */
public class DocumentStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentStorageException(String message) {
		super(message);
	}

	public DocumentStorageException(String message, Throwable cause) {
		super(message, cause);
	}

}