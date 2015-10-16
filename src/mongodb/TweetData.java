package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TweetData {
	
    private static final int NUM_TWEET= 100;
    
    private static final Log LOG = LogFactory.getLog(TweetData.class);

    double getRandomInRange(final int from, final int to, final int fixed) {
        return (Math.random() * (to - from) + from)/*.toFixed(fixed) * 1*/;
    }

    String getRandomString(final int len) {
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randomString = "";
        for (int i = 0; i < len; i++) {
            int randomPoz = getRandomInt(1, possible.length());
            randomString += possible.substring(randomPoz, randomPoz + 1);
        }
        return randomString;
    }

    Date randomDate(final Date start, final Date end) {
        return new Date(start.getTime() + ((long) (Math.random() * (end.getTime() - start.getTime()))));
    }

    <T> T choose(final List<T> choices) {
        return choices.get(getRandomInt(0, choices.size()));
    }

    int getRandomInt(final int min, final int max) {
        return (int) Math.floor(Math.random() * (max - min) + min);
    }

    public void run() throws UnknownHostException {
        final List<Integer> models = new ArrayList<Integer>();
        final List<String> owners = new ArrayList<String>();
        final MongoClient client = new MongoClient();
        
        // name databases
        DB db = client.getDB("GenerateData");
        // name collection
        DBCollection data = db.getCollection("data1");

        if ("true" .equals(System.getenv("DATA_DROP"))) {
            LOG.info("Dropping data");
            data.drop();
            data.createIndex(new BasicDBObject("data", 1));
        }
        db.getCollection("data_aggregate").createIndex(new BasicDBObject("data", 1));

        if (data.count() == 0) {
            for (int i = 0; i < 10; i++) {
                owners.add(getRandomString(10));
            }

            for (int i = 0; i < 10; i++) {
                models.add(getRandomInt(10, 20));
            }

            for (int i = 0; i < NUM_TWEET; i++) {
                @SuppressWarnings("deprecation")
				DBObject tweet = new BasicDBObject("_id", new ObjectId())
                                      .append("screen_name", getRandomString(5) + getRandomInt(3, 5))
                                      .append("follower", getRandomInt(10, 10000))
                                      .append("retweet", getRandomInt(3, 4450))
                                      .append("mention", getRandomInt(1, 500))
                                      .append("favorited", getRandomInt(0, 540))
                                      .append("created_at", randomDate(new Date(2000, 1, 1, 16, 49, 29), new Date()));
                data.insert(tweet);
            }
        }
    }
}