package csvparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class BIDataParser {

	private static final String CSV_IN_FILE_PATH =
//			"C:\\Users\\Areej Ahmed\\Documents\\country.csv";
	"C:\\Users\\Areej Ahmed\\OneDrive - Boutiqaat\\Documents\\BI Nada data"
	+ "\\areej- march.csv";
	
	private static final String CSV_OUT_FILE_PATH = 
			"C:\\Users\\Areej Ahmed\\OneDrive - Boutiqaat\\Documents\\"
			+ "BI Parsed data"
			+ "\\areej- march.txt";
	
	
	private static Map<Long, List<Long>> orderItems = new HashMap();
	public static void main(String[] args) {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(CSV_IN_FILE_PATH));
		} catch (IOException e) {
			System.out.println("Failed in "+e.getMessage());
			e.printStackTrace();
		}
		CSVParser csvParser = null;
		try {
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT
					.withHeader("order_year_month", "order_number", "child id")
					.withIgnoreHeaderCase()
					.withTrim());
		} catch (IOException e) {
			System.out.println("Failed in "+e.getMessage());
			e.printStackTrace();
		}
		int lineCount=0;
		for(CSVRecord csvRecord: csvParser) {
			lineCount++;
			if(lineCount>1) {
				Long orderNumber = Long.valueOf(csvRecord.get("order_number"));
				Long childId = Long.valueOf(csvRecord.get("child id"));

				if (orderItems.containsKey(orderNumber)) {
					orderItems.get(orderNumber).add(childId);
				} else {
					orderItems.put(orderNumber, new ArrayList<Long>());
					orderItems.get(orderNumber).add(childId);
				}

				System.out.println("Record No. " + csvRecord.getRecordNumber());
				System.out.println("---------------------------");
				System.out.println("orderNumber: " + orderNumber);
				System.out.println("childId: " + childId);
				System.out.println("---------------------------\n\n");
			}
		}
		try {
			csvParser.close();
		} catch (IOException e) {
			System.out.println("Failed in "+e.getMessage());
			e.printStackTrace();
		}

		File file = new File(CSV_OUT_FILE_PATH);
		BufferedWriter writer = null;
		CSVPrinter csvPrinter = null;
		if( ! file.exists() ) {
			try {
				writer = Files.newBufferedWriter(Paths.get(CSV_OUT_FILE_PATH));
			} catch (IOException e) {
				System.out.println("Failed in "+e.getMessage());
				e.printStackTrace();
			}
			try {
				csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withDelimiter(' '));
			} catch (IOException e) {
				System.out.println("Failed in "+e.getMessage());
				e.printStackTrace();
			}
		}else {
			try {
				writer = Files.newBufferedWriter(Paths.get(CSV_OUT_FILE_PATH),
						StandardOpenOption.APPEND,
						StandardOpenOption.CREATE);
			} catch (IOException e) {
				System.out.println("Failed in "+e.getMessage());
				e.printStackTrace();
			}
			try {
				csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			} catch (IOException e) {
				System.out.println("Failed in "+e.getMessage());
				e.printStackTrace();
			}
		}
		for (Long order: orderItems.keySet()) {
			try {
				csvPrinter.printRecord(orderItems.get(order));
			} catch (IOException e) {
				System.out.println("Failed in "+e.getMessage());
				e.printStackTrace();
			}
		}
		
		try {
			csvPrinter.flush();
		} catch (IOException e) {
			System.out.println("Failed in "+e.getMessage());
			e.printStackTrace();
		}
		
		try {
			csvPrinter.close();
		} catch (IOException e) {
			System.out.println("Failed in "+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
