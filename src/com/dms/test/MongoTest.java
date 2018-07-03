package com.dms.test;

import java.io.IOException;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 

import org.bson.Document;  

public class MongoTest { 
	
   private static MongoDatabase database;
   
   public static void test() {
	   
	   getConnection();
	   
	   //retrieveAll();
	   insertBlob();
	   
   }
	
   public static void getConnection() {
	   	 
	     // Creating a Mongo client 
	     MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
	   
	      // Creating Credentials 
	      MongoCredential credential; 
	      credential = MongoCredential.createCredential("accountUser", "db", 
	         "password".toCharArray()); 
	      System.out.println("Connected to the database successfully");  
	      
	      // Accessing the database 
	       database = mongo.getDatabase("db"); 
	      
	      
	   
   }
   
   public static void CreateCollection() {
	   //Creating a collection 
	      database.createCollection("sampleCollection"); 
	      System.out.println("Collection created successfully"); 
	      
   }
   
   public static void insertData() {  
      
      
        
   // Retrieving a collection
      MongoCollection<Document> collection = database.getCollection("sampleCollection"); 
      System.out.println("Collection sampleCollection selected successfully");

      Document document = new Document("title", "MongoDB") 
      .append("id", 1)
      .append("description", "database") 
      .append("likes", 100) 
      .append("url", "http://www.tutorialspoint.com/mongodb/") 
      .append("by", "tutorials point");  
      collection.insertOne(document); 
      System.out.println("Document inserted successfully");  
      
   } 
   
   public static void retrieveAll() {
	   		
	      // Retrieving a collection 
	      MongoCollection<Document> collection = database.getCollection("sampleCollection");
	      System.out.println("Collection sampleCollection selected successfully"); 

	      // Getting the iterable object 
	      FindIterable<Document> iterDoc = collection.find(); 
	      int i = 1; 

	      // Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	      i++; 
	      }
   
   
   }
   
   public static void insertBlob(){
	   
	   String filePath = "dvc.pdf";
	   try {
		byte[] bytes = TestMain.readfile(filePath);
		MongoCollection<Document> collection = database.getCollection("sampleCollection");
		Document doc = new Document("blob", bytes);
		collection.insertOne(doc);
	     
		// Find and print the inserted byte array as String
		for (Document doc1 : collection.find()) {
		    Binary bin = getBlob(doc1);
;		    TestMain.writefile(bin.getData());
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   
   public static Binary getBlob(Document doc) {
	   
	  
	   Binary bin = doc.get("blob", org.bson.types.Binary.class);
	   return bin;
   }
}

