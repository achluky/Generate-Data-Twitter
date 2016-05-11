package mongodb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.UnknownHostException;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class GetId_Tweet {
	public GetId_Tweet(){}
    public void run() throws UnknownHostException {
        File file = new File("resource/data_ahok_20150806_id.txt");
    	MongoClient mongo = new MongoClient( "localhost" , 27017 ); // connection in java
        DB db = mongo.getDB("research"); //name databases
        DBCollection collection = db.getCollection("data_ahok_20150806"); //name collection
        BasicDBObject query = new BasicDBObject(); // query
        BasicDBObject fields = new BasicDBObject("id", "1"); // select once "id"
        DBCursor cursor = collection.find(query, fields); // run query
    	while(cursor.hasNext()) {
        	DBObject dbObject = cursor.next();
			String jsonString = JSON.serialize(dbObject);       
            JSONObject jsonObject = new JSONObject(jsonString);
            try {
                Long id = (Long) jsonObject.get("id"); // id
                System.out.println(id);
    			// if file doesnt exists, then create it
    			if (!file.exists())
    				file.createNewFile();
    			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
    			BufferedWriter bw = new BufferedWriter(fw);
    			bw.write(String.valueOf(id));
    			bw.newLine();
    			bw.close();
			} catch (Exception e) {
				continue;
			}
    	}
    }
}
