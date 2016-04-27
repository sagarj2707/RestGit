package com.ibm.de.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.ibm.de.messenger.model.Message;

/**
 * @author sjadhav
 *
 */
public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

}
