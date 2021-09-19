package csvparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {

	private static final String CSV_FILE_PATH = "C:\\Users\\Areej Ahmed\\Documents\\employees.csv";
	public static void main(String[] args) throws IOException {
		File file = new File(CSV_FILE_PATH);
		BufferedWriter writer;
		CSVPrinter csvPrinter;
		if( ! file.exists() ) {
			writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Id", "Name", "Designation", "Company")
					.withDelimiter(','));
		}else {
			writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH),
					StandardOpenOption.APPEND,
					StandardOpenOption.CREATE);
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
		}
		csvPrinter.printRecord(1, "Ahmad", "CEO", "Atlas");
		csvPrinter.printRecord(2, "Satya Nadella", "CEO", "Microsoft");
        csvPrinter.printRecord(3, "Tim cook", "CEO", "Apple");
        csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO",
        		"Facebook"));
		
		csvPrinter.flush();
		
		csvPrinter.close();

	}

}
/* 
 * Say, we want to send a CSV file to the user (via a Springboot endpoint)
 * and we don't want to create a local csv file then send it, no,
 * we have the data and we send it as OutputStream (Byte Array) 
 * we thus can do the following:
 * 
 * 
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

@RestController
public class ReportController {

    @GetMapping("/downloadReport")
    public ResponseEntity<byte[]> downloadUserReport() throws IOException {
        byte[] csvFileByteArray = getUserReportCSVAsByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "user-report" + ".csv" + "\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvFileByteArray);
    }

    private byte[] getUserReportCSVAsByteArray() throws IOException {
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Name", "Designation", "Company"));
        ) {
            csvPrinter.printRecord("1", "Sundar Pichai ", "CEO", "Google");
            csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
            csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

            csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

            csvPrinter.flush();
            return out.toByteArray();
        }
    }
}
 */
