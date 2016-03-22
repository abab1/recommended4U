package com.recommendations.rest.controller;

import static com.mongodb.client.model.Filters.eq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.recommendations.vo.InputWrapper;

@Controller
public class RecommendationController {
	
	
	@RequestMapping("/home")
	public String displayHome(@RequestParam("id") String param, HttpServletRequest htRequest) {
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		InputWrapper iw = null;
		try {
			iw = om.readValue(param, InputWrapper.class);
			insertUserIfDoesntExist(iw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = htRequest.getSession();
		session.setAttribute("userEmailId", iw.getEmailId());
		session.setAttribute("userName", iw.getUserName());
		System.out.println("userEmailId"+ iw.getEmailId());
		System.out.println("*****value recieved*****"+ param);
		return "widget";
	}
	
	@RequestMapping("/search")
	public String displayHome() {
		return "search";
	}
	
	private void insertUserIfDoesntExist(InputWrapper wrapper) {
		
		MongoClient mongo;
		mongo = new MongoClient();
		MongoDatabase db =  mongo.getDatabase("recommended4U");
		MongoCollection<Document> collection = db.getCollection("Users");
		
		try{
		FindIterable<Document> iterable = collection.find(eq("userId", wrapper.getEmailId()));
		Document user = iterable.first();
		if (user != null) {			
		} else {
			collection.insertOne(new Document("userId" , wrapper.getEmailId()).append("userName", wrapper.getUserName())
							.append("recentHistory", "").append("recentlySeen", "").append("likes", wrapper.getLikes()).append("gender", wrapper.getGender()));
		}
		} catch(Exception e) {
			mongo.close();
			e.printStackTrace();
		}
		mongo.close();
	}

}
