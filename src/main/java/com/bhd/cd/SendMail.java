package com.bhd.cd;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bhd.cd.utils.HibernateUtils;

public class SendMail extends Thread {

	public static int threadCounter = 0;
	private int threadCount;
	private List<Email> emailList = new ArrayList<Email>();

	static Properties properties = new Properties();
	static {
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "25");
	}

	public String sendEmailMessage(Email currentEmail) {
		String ret = "SUCCESS";
		try {
			Session session = Session.getInstance(properties, null);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(currentEmail.getFromEmailAddr()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(currentEmail.getToEmailAddr()));
			message.setSubject(currentEmail.getSubject());
			message.setText(currentEmail.getBody());
			Transport.send(message);
		} catch (Exception e) {
			ret = "ERROR";
			e.printStackTrace();
		}
		return ret;
	}

	SendMail() {
		this.threadCount = ++threadCounter;
	}

	public void run(){
		this.emailList = HibernateUtils.retrievePendingMails(this);
		if(emailList != null){
			for(Email currentEmail: emailList){
				System.out.println(currentEmail.getId()+this.getName());
				this.sendEmailMessage(currentEmail);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendMail thread1 = new SendMail();
		SendMail thread2 = new SendMail();
		SendMail thread3 = new SendMail();
		SendMail thread4 = new SendMail();
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

	}

	/**
	 * @return the threadCount
	 */
	public int getThreadCount() {
		return threadCount;
	}

	/**
	 * @param threadCount
	 *            the threadCount to set
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

}
