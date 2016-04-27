package com.ibm.de.scprism.testappconsole;

import java.util.Date;
import java.util.Scanner;
import com.ibm.de.messenger.model.Message;
import com.ibm.de.scprism.restfacade.RestFacade;

/**
 * @author sjadhav
 *
 */
public class TestAppConsole {
	public static String resourceUri = "http://localhost:9999/messenger/webapi/messages";
	public static String resourceType = "application/json";
	public static Message message = new Message();

	public static void main(String[] args) {
		RestFacade facade = new RestFacade();
		int num = 0;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter a number GET[1], POST[2], DELETE[3]");
			num = scanner.nextInt();
			switch (num) {
			case 1:
				System.out.println(facade.getJaxFacade(resourceUri, resourceType));
				break;
			case 2:
				message.setAuthor("shankar");
				message.setCreated(new Date());
				message.setMessage("POST method called");
				System.out.println(facade.postJaxFacade(resourceUri, resourceType, message));
				break;
			case 3:
				System.out.println(facade.deleteJaxFacade(resourceUri, resourceType));
				break;
			default:
				System.out.println("No case selected");
				break;
			}
		} catch (Exception e) {
			System.out.println("Wrong Input!! Try Again");
		}
	}
}