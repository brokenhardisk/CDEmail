package com.bhd.cd;

/**
 * POJO class for Email Queue db table
 * @author bhd
 *
 */
public class Email {

	private int id; //Unique Id for each row of Email in the db
	private String fromEmailAddr;
	private String toEmailAddr;
	private String subject;
	private String body;
	
	//Getters/Setters follow 
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the fromEmailAddr
	 */
	public String getFromEmailAddr() {
		return fromEmailAddr;
	}
	/**
	 * @param fromEmailAddr the fromEmailAddr to set
	 */
	public void setFromEmailAddr(String fromEmailAddr) {
		this.fromEmailAddr = fromEmailAddr;
	}
	/**
	 * @return the toEmailAddr
	 */
	public String getToEmailAddr() {
		return toEmailAddr;
	}
	/**
	 * @param toEmailAddr the toEmailAddr to set
	 */
	public void setToEmailAddr(String toEmailAddr) {
		this.toEmailAddr = toEmailAddr;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
