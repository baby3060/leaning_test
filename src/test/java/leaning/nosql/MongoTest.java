package leaning.nosql;

// mongodb-driver-sync 필요
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
// mongodb-driver-sync 필요

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
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
import org.bson.types.ObjectId;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

// 조회 할 때 사용할 정적 함수
import static com.mongodb.client.model.Filters.*;
// 수정할 때 사용할 정적 함수
import static com.mongodb.client.model.Updates.*;

public class MongoTest {
    private final Logger LOGGER = LoggerFactory.getLogger(MongoTest.class);

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @BeforeClass
    public void setUp() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");

        if(mongoClient != null) {
            database = mongoClient.getDatabase("nodejs");
        }

        if( database != null ) {
            collection = database.getCollection("test");
        }
    }

    @AfterClass
    public void completeClose() {
        LOGGER.info("Test Suite End");
    }

    private void deleteAll() {
        collection.deleteMany(gte("idx", 0));
    }

    @BeforeMethod
    public void startTest() {
        LOGGER.info("Test Start");

        Document doc = new Document("writer", "test")
                .append("content", "Hello World")
                .append("hobby", Arrays.asList("game", "reading"))
                .append("citycode", new Document("x", 76).append("y", 63))
                .append("idx", 1L);

        // 단일 Document 저장
        collection.insertOne(doc);

        List<Document> documentLists = new ArrayList<Document>();

        for( long idx = 2L; idx < 10L; idx++ ) {
            doc = new Document("writer", "test")
                .append("content", "Hello World")
                .append("hobby", Arrays.asList("game", "reading"))
                .append("citycode", new Document("x", 76).append("y", 63))
                .append("idx", idx);
            documentLists.add(doc);
        }

        collection.insertMany(documentLists);

        LOGGER.info("Test Suite Start : " + collection.count());
    }
    
    @AfterMethod
    public void closeTest() {
        LOGGER.info("Test End Clear All");
        deleteAll();
    }

    @Test
    public void selectTest() {
        assertSame(collection.count(), 9L);

        Document getDoc = collection.find().first();
        assertEquals(getDoc.get("content").toString(), "Hello World");
        assertEquals(((Long)getDoc.get("idx")).longValue(), 1L);
        
        Document cityCode = (Document)getDoc.get("citycode");
        
        Integer xPosition = (Integer)cityCode.get("x");
        Integer yPosition = (Integer)cityCode.get("y");
        assertEquals(xPosition.intValue(), 76);
        assertEquals(yPosition.intValue(), 63);

        for (Document cur : collection.find()) {
            assertTrue((cur.get("content").toString().indexOf("Hello World") >= 0));
        }
        
    }

    @Test
    public void selectOneAndUpdate() {
        MongoCollection<Document> collection = database.getCollection("test");

        long count = collection.count();

        assertTrue((count == 9L));

        Document thirdDoc = collection.find(eq("idx", 3L)).first();
        String oId = thirdDoc.get("_id").toString();

        collection.updateOne(
            eq("_id", new ObjectId(oId)),
            combine(
                set("content", "Hello World 2"), 
                set("hobby", Arrays.asList("game", "reading", "exercise")),
                currentDate("lastModified")
            ),
            new UpdateOptions().upsert(true)
        );

        Document reThird = collection.find(eq("idx", 3L)).first();
        assertEquals(reThird.get("content"), "Hello World 2");
    }



}