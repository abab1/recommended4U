package com.recommendations.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InputWrapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userId;
	
	private String accessToken;
	
	private String userName;
	
	private String emailId;
	
	private String likes;
	
	private String source;
	
	private String metaTags;
	
	private String gender;

	public InputWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputWrapper(Long userId, String userName, String emailId, String source, String metaTags) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.source = source;
		this.metaTags = metaTags;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMetaTags() {
		return metaTags;
	}

	public void setMetaTags(String metaTags) {
		this.metaTags = metaTags;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
