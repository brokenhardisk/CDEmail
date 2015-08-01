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

/**
 * The class that spawns the threads which send emails as per the records in db
 * @author bhd
 *
 */
public class SendMail extends Thread {

	public static int threadOffset = 0; //static variable to determine for each thread the starting Id of the database row to be fetched 
	private int threadStart=0,threadEnd=0; //Place holders for start/end of ID creating a range of Rows to be selected from the db for each thread
	private List<Email> emailList = new ArrayList<Email>(); //List to hold the retrieved rows of Email objects from the db

	//SMTP Properties setup
	static Properties properties = new Properties();
	static {
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "25");
	}
	
	/**Method to create the Email message from the Email POJO and send it via the SMTP server
	 * @param currentEmail
	 * @return
	 */
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

	/**Constructor to set the start/end IDs for each thread
	 * @param last
	 */
	SendMail(int last) {
		this.threadStart = threadOffset;
		threadOffset+=(last-1);
		this.threadEnd=threadOffset++;
	}

	public void run(){
		this.emailList = HibernateUtils.retrievePendingMails(this); //Retrieving List of Emails to be sent 
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
		InputStream inputStream = SendMail.class.getResourceAsStream("/config.properties"); //Reading the properties file
		Properties props = new Properties();
		props.load(inputStream);
		int num_process = Integer.parseInt((String) props.get("numberOfProcess")); // Number of processes of program that will be run
		int num_threads = Integer.parseInt((String)props.get("numberOfThreads")); //Number of threads to be spawned for this process
		int pId = Integer.parseInt((String) props.get("processID")); //The Id of the current process
		int count = HibernateUtils.retrieveRowCount(); //Total number of Emails to be sent
		int curr_process_count = (count/num_process); // Total number of emails to be sent by the current process
		int process_offset = curr_process_count;
		if(num_process != 1 && pId == num_process){
			curr_process_count = count - (num_process-1)*curr_process_count; //In case the division amongst processes is not equal,residual mails to be sent by the last process
		}
		int per_thread_count = (curr_process_count/num_threads); // Total number of emails to be sent by each thread
		//To demarcate starting ID of the threads based on the process.
		//For 1000 mails and 4 processes, Process 1 would send 1-250, Process2->251-500, Process-3->501-750, Process-4->751-1000
		threadOffset = ((pId-1)*process_offset)+1;		
		SendMail[] mailSendingThreads = new SendMail[num_threads]; 
		for(int i=0;i<num_threads;i++){
			if(i==(num_threads-1)){
				per_thread_count = curr_process_count - (num_threads-1)*per_thread_count; //In case the division amongst threads is not equal,residual mails to be sent by the last thread
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
