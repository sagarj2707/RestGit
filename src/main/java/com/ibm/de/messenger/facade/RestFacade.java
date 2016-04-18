package com.ibm.de.messenger.facade;

import com.ibm.de.messenger.database.DatabaseClass;
import com.ibm.de.messenger.model.Message;
import com.ibm.de.messenger.model.Profile;
import com.ibm.de.messenger.resources.MessageResource;
import com.ibm.de.messenger.service.MessageService;

public class RestFacade {
	
	private DatabaseClass dbase;
	private Message msg;
	private Profile profile;
	private MessageResource msgRsrc;
	private MessageService msgSrvc;

	public RestFacade(){
		this.dbase = new DatabaseClass();
		this.msg = new Message();
		this.profile = new Profile();
		this.msgRsrc = new MessageResource();
		this.msgSrvc = new MessageService();
	}
	
}
