package com.bhd.cd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

	public static int threadOffset = 0;
	private int threadStart=0,threadEnd=0;
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

	SendMail(int last) {
		this.threadStart = threadOffset;
		threadOffset+=(last-1);
		this.threadEnd=threadOffset++;
	}

	public void run(){
		this.emailList = HibernateUtils.retrievePendingMails(this);
		if(emailList != null){
			for(Email currentEmail: emailList){
				this.sendEmailMessage(currentEmail);
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		InputStream inputStream = SendMail.class.getResourceAsStream("/config.properties");
		Properties props = new Properties();
		props.load(inputStream);
		int num_process = Integer.parseInt((String) props.get("numberOfProcess"));
		int num_threads = Integer.parseInt((String)props.get("numberOfThreads"));
		int pId = Integer.parseInt((String) props.get("processID"));
		int count = HibernateUtils.retrieveRowCount();
		int curr_process_count = (count/num_process);
		if(num_process != 1 && pId == num_process){
			curr_process_count = count - (num_process-1)*curr_process_count;
		}
		int per_thread_count = (curr_process_count/num_threads);
		threadOffset = ((pId-1)*curr_process_count)+1;
		SendMail[] mailSendingThreads = new SendMail[num_threads]; 
		for(int i=0;i<num_threads;i++){
			if(i==(num_threads-1)){
				per_thread_count = curr_process_count - (num_threads-1)*per_thread_count;
			}
			mailSendingThreads[i] = new SendMail(per_thread_count);
			mailSendingThreads[i].start();
		}
	}

	/**
	 * @return the threadStart
	 */
	public int getThreadStart() {
		return threadStart;
	}

	/**
	 * @param threadStart the threadStart to set
	 */
	public void setThreadStart(int threadStart) {
		this.threadStart = threadStart;
	}

	/**
	 * @return the threadEnd
	 */
	public int getThreadEnd() {
		return threadEnd;
	}

	/**
	 * @param threadEnd the threadEnd to set
	 */
	public void setThreadEnd(int threadEnd) {
		this.threadEnd = threadEnd;
	}

}
