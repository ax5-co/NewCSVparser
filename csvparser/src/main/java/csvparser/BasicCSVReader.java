package csvparser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class BasicCSVReader {
	
	private static final String CSV_FILE_PATH = "C:\\Users\\Areej Ahmed\\Documents\\country.csv";
	
	public static void main(String[] args) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			
			for(CSVRecord csvRecord: csvParser) {
				String ip = csvRecord.get(0);
				String ip2 = csvRecord.get(1);
				String countryCode = csvRecord.get(2);
				String countryName = csvRecord.get(3);
				
				System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("IP : " + ip);
                System.out.println("IP2 : " + ip2);
                System.out.println("Country Code : " + countryCode);
                System.out.println("Country Name : " + countryName);
                System.out.println("---------------\n\n");
			}
			 csvParser.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
