package csvparser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReaderWithManualHeader {

	private static final String CSV_FILE_PATH = "C:\\Users\\Areej Ahmed\\Documents\\country.csv";
	public static void main(String[] args) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				.withHeader("ip", "complete ip", "code","name")
				.withIgnoreHeaderCase()
				.withTrim());
		
		for(CSVRecord csvRecord: csvParser) {
			String ip = csvRecord.get("ip");
			String ip2 = csvRecord.get("complete ip");
			String code = csvRecord.get("code");
			String name = csvRecord.get("name");
			
			System.out.println("Record No. "+csvRecord.getRecordNumber());
			System.out.println("---------------------------");
			System.out.println("IP: "+ ip);
			System.out.println("IP2: "+ip2);
			System.out.println("Country Code: "+code);
			System.out.println("Country Name: "+name);
			System.out.println("---------------------------\n\n");
		}
		csvParser.close();
	}
}
