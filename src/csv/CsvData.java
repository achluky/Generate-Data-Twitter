package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

public class CsvData {
		
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final long NUM_TWEET = 700000L;
	
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
	
	public void run(String fileName) throws UnknownHostException {
	    FileWriter fileWriter = null;
	    try {
	    	fileWriter = new FileWriter(fileName);
	    	for (int i = 0; i < NUM_TWEET; i++) {
	    		// index, x, y
	    		fileWriter.append( String.valueOf(getRandomString(5) + getRandomInt(3, 5)) ); // user
	    		fileWriter.append(COMMA_DELIMITER);
	    		fileWriter.append( String.valueOf(getRandomInt(10,10000))); // x
	    		fileWriter.append(COMMA_DELIMITER);
	    		fileWriter.append( String.valueOf(getRandomInt(10,10000))); // y
	    		fileWriter.append(NEW_LINE_SEPARATOR);
	        }
		} catch (Exception e) {
	        System.out.println("Error in CsvFileWriter !!!");
	        e.printStackTrace();
	    } finally {
	        try {
	            fileWriter.flush();
	            fileWriter.close();
	        } catch (IOException e) {
	            System.out.println("Error while flushing/closing fileWriter !!!");
	            e.printStackTrace();
	        }
	    }   
	}
}
