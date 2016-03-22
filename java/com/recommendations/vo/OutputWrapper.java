package com.recommendations.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OutputWrapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;
	
	private String content_button;
	

	public OutputWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutputWrapper(String content, String content_button) {
		super();
		this.content = content;
		this.content_button = content_button;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_button() {
		return content_button;
	}

	public void setContent_button(String content_button) {
		this.content_button = content_button;
	}

}
