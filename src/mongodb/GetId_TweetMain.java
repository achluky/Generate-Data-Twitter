package mongodb;

import java.net.UnknownHostException;
/**
 * 
 * Conde For selecting id tweet // Code untuk mendapatkan id tweet yang akan digunakan dlam proses pengumpulan data reply tweet.
 * @author ahmadluky
 *
 */
public class GetId_TweetMain {
	public static void main(String args[]) throws UnknownHostException{    
		GetId_Tweet call = new GetId_Tweet();
		call.run();
	}
}
