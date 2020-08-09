/**
 * Copyright (c) 2004, 2020, Yogesh Badgujar and/or its affiliates. 
 * All rights reserved and PROPRIETARY/CONFIDENTIAL. 
 * Use is subject to Yogesh Badgujar terms.
 */
package tomtom.poi.util;

/**
 * Purpose:
 *
 * Description:
 *
 * @author Yogesh Badgujar
 * email me in case any problem - badgujar.yogesh@gmail.com
 *
 */
public class POIUtil {
	
	/**
	 * Prepares string with full stack of exceptions.
	 *
	 * @param exception - initial exception
	 * @return String with all causes and trace elements
	 */
	public static String buildMessageWithCause(Throwable exception) {
		StringBuilder builder = new StringBuilder();
		int issueNo = 1;
		for (Throwable t = exception; t != null; t = t.getCause()) {
			builder.append("Issue ").append(issueNo).append(" ").append(exception != t ? "Caused by: " : "");
			builder.append(t.getLocalizedMessage()).append("\n");
			for (StackTraceElement ste : t.getStackTrace()) {
				builder.append("    ").append(ste.toString()).append("\n");
			}
			builder.append("\n");
			issueNo++;
		}

		return builder.toString();
	}

}
