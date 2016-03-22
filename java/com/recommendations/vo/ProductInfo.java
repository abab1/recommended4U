package com.recommendations.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String asin;
	
	private String imURL;
	
	private String actualScore;
	
	private String finalScore;
	
	private String price;
	
	private String title;
	
	private String description;
	
	private String reviews;

	
	public ProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProductInfo(String imURL, String amazonScore, String finalScore, String price, String reviews) {
		super();
		this.imURL = imURL;
		this.setActualScore(amazonScore);
		this.finalScore = finalScore;
		this.price = price;
		this.reviews = reviews;
	}


	public String getImURL() {
		return imURL;
	}


	public void setImURL(String imURL) {
		this.imURL = imURL;
	}



	public String getFinalScore() {
		return finalScore;
	}


	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getReviews() {
		return reviews;
	}


	public void setReviews(String reviews) {
		this.reviews = reviews;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getActualScore() {
		return actualScore;
	}


	public void setActualScore(String actualScore) {
		this.actualScore = actualScore;
	}


	public String getAsin() {
		return asin;
	}


	public void setAsin(String asin) {
		this.asin = asin;
	}
	
	
	
	

}
