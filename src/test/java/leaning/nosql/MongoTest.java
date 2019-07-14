package leaning.nosql;

// mongodb-driver-sync 필요
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
// mongodb-driver-sync 필요

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.Block;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.bson.Document;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MongoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("nodejs");
        assertNotNull(database);
        

        MongoCollection<Document> collection = database.getCollection("test");
        
        Document doc = new Document("writer", "test")
                .append("content", "Hello World")
                .append("hobby", Arrays.asList("game", "reading"))
                .append("citycode", new Document("x", 76).append("y", 63));
        
        collection.insertOne(doc);

        collection = database.getCollection("test");

        assertTrue((collection.count() >= 1L));

        Document getDoc = collection.find().first();
        assertEquals(getDoc.get("content").toString(), "Hello World");
        Document cityCode = (Document)getDoc.get("citycode");
        
        Integer xPosition = (Integer)cityCode.get("x");
        Integer yPosition = (Integer)cityCode.get("y");
        assertEquals(xPosition.intValue(), 76);
        assertEquals(yPosition.intValue(), 63);
    }
}