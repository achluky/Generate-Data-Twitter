package csv;
import java.net.UnknownHostException;
import java.util.Date;
import csv.CsvData;
public class CsvDataMain {

	public static void main(String args[]) throws UnknownHostException
	{
		// path data
        String fileName = System.getProperty("user.home")+"/workspace_2/hadoop-example/resrc/datauser-influence.csv";
		// callculation running time and generate data
	    long start = new Date().getTime();
	    CsvData call = new CsvData();
		call.run(fileName);
        long end = new Date().getTime();
        System.out.println("Job took "+(end-start) + " milliseconds");

	}
}
