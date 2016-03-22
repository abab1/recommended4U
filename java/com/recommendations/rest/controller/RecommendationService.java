package com.recommendations.rest.controller;

import static com.mongodb.client.model.Filters.eq;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.recommendations.vo.OutputWrapper;
import com.recommendations.vo.ProductInfo;

@Component
@Path("/recommendations")
public class RecommendationService {
	
	final String dbName = "recommended4U";
	final String collName1 = "Ratings";
	final String collName2 = "products1";
	final String collName3 = "TopReviews";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	//@ResponseBody
	public Response getRecommendation(String str) {
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		List<OutputWrapper> lstOfOWs = getRecommendations(str);
		String res = null;
		try {
			res = om.writeValueAsString(lstOfOWs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(res).build();
		//return wrapper;
	}
	
	
	@Path("/search/{queryString}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	//@ResponseBody
	public Response searchProducts(@PathParam("queryString") String queryString) {
		System.out.println("##############"+queryString+"@@@@@@@@@@@@@@");
		String str[] = queryString.split(",");
		queryString = str[0];
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		String userEmailId = str[1];
		MongoClient mongo = new MongoClient();
		MongoDatabase db =  mongo.getDatabase(dbName);
		//MongoCollection<Document> collection = db.getCollection(collName2);
		Document user = getUser(db, userEmailId);
		List<OutputWrapper> lstOfOWs = createQuery(db, queryString, user, 7);
		updateUser(user, db, queryString);
		String res = null;
		try {
			res = om.writeValueAsString(lstOfOWs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mongo.close();
		return Response.status(200).entity(res).build();
		//return wrapper;
	}
	
	@Path("/item/{asin}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	//@ResponseBody
	public Response fetchItem(@PathParam("asin") String asin) {
		DecimalFormat df = new DecimalFormat("#.#");
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		MongoClient mongo = new MongoClient();
		MongoDatabase db =  mongo.getDatabase(dbName);
		MongoCollection<Document> collection1 = db.getCollection(collName1);
		MongoCollection<Document> collection2 = db.getCollection(collName2);
		MongoCollection<Document> collection3 = db.getCollection(collName3);
		FindIterable<Document> iterable1 = collection1.find(Filters.eq("asin", asin));
		FindIterable<Document> iterable2 = collection2.find(Filters.eq("asin", asin));
		FindIterable<Document> iterable3 = collection3.find(Filters.eq("asin", asin));
		Document document1 = iterable1.first();
		Document document2 = iterable2.first();
		Document document3 = iterable3.first();
		ProductInfo info = new ProductInfo();
		info.setActualScore(df.format(document1.get("score_1")));
		info.setFinalScore(df.format(document1.get("score_2")));
		info.setDescription(document2.getString("description"));
		info.setImURL(document2.getString("imUrl"));
		if (document2.get("price") != null) {
			info.setPrice(df.format(document2.get("price")));
		}
		info.setTitle(document2.getString("title"));
		info.setAsin(asin);
		info.setReviews(document3.getString("topReview"));
		String res = null;
		try {
			res = om.writeValueAsString(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mongo.close();
		return Response.status(200).entity(res).build();
		//return wrapper;
	}
	
	private void updateUser(Document user, MongoDatabase db, String queryString) {
		MongoCollection<Document> collection = db.getCollection("Users");
		collection.updateOne(user, new Document("$set", new Document("recentHistory", queryString)));
		System.out.println("Not Null");
		
	}
	
	private Document getUser(MongoDatabase db, String emailId) {
			MongoCollection<Document> collection = db.getCollection("Users");
			FindIterable<Document> iterable = collection.find(eq("userId", emailId.trim()));
			Document user = iterable.first();
			return user;
	}
	
	private List<OutputWrapper> getRecommendations(String emailId) {
		MongoClient mongo = new MongoClient();
		MongoDatabase db =  mongo.getDatabase(dbName);
		//MongoCollection<Document> collection = db.getCollection(collName2);
		Document user = getUser(db, emailId);
		String recentHistory = (String) user.get("recentHistory");
		final List<OutputWrapper> oWrappers = createQuery(db, recentHistory, user, 28);
		mongo.close();
		return oWrappers;
	}
	
	private List<OutputWrapper> createQuery(MongoDatabase db, String queryString, Document user, int limit) {
		final List<OutputWrapper> oWrappers = new ArrayList<OutputWrapper>();
		MongoCollection<Document> collection1 = db.getCollection(collName1);
		MongoCollection<Document> collection2 = db.getCollection(collName2);
		int i = 0;
		FindIterable<Document> iterable;
		if (queryString != null && !queryString.equals("")) {
			String historyTags[] = queryString.split(" ");
			String regex = "";
			for (String tags : historyTags) {
				regex += "[\\w,\\d, ]*" + tags+ "[\\w,\\d, ]*";
			}
			iterable = collection1.find(Filters.regex("title", regex, "ix")).sort(new BasicDBObject("score_2", -1)).limit(limit);
			for (Document document : iterable) {
				String asin = (String) document.getString("asin");
				FindIterable<Document> iterable2 = collection2.find(Filters.eq("asin", asin));
				Document prod = iterable2.first();
				OutputWrapper ow = createOutputWrapper(prod);
				oWrappers.add(ow);
			}
		} else 
		{
			iterable = collection2.find();
			String gender = (String) user.get("gender");
			String query = "";
			if (gender!= null && gender.equalsIgnoreCase("male")) {
				query = "Sports";
			} else {
				query = "beauty";
			}
			System.out.println("******"+query);
			for (Document document : iterable) {
				ArrayList<String> lst = (ArrayList<String>) document.get("categories");
		    	String str = lst.toString();
		    	if (str.contains(query)) {
		    		OutputWrapper ow = createOutputWrapper(document);
		    		oWrappers.add(ow);
		    		i++;
		    		if (i == limit) {
		    			break;
		    		}
		    	}
			}
		}
		
		return oWrappers;
	}
	
	private OutputWrapper createOutputWrapper(Document document) {
		String asin = (String) document.get("asin");
		OutputWrapper ow = new OutputWrapper();
		StringBuilder sb = new StringBuilder("'<div class='slide_inner'><a class='photo_link' href='#' ");
		sb.append("onclick='fetchReviews(\""+asin);
		sb.append("\")'><img class='photo' src='");
		sb.append(document.get("imUrl"));
		sb.append("' alt='Bike'></a><a class='caption' href='#' ");
		sb.append("onclick='fetchReviews(\""+asin);
		sb.append("\")'>Buy it Now ");
		Double price = (Double) document.get("price");
		if (price != null) {
			sb.append("for $ "+price+" ");
		}
		sb.append("at amazon.com</a></div>'");
		ow.setContent(sb.toString());
		ow.setContent_button("'<div class='thumb'><img src='../images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>'");
		return ow;
		
	}

}
