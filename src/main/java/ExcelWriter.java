import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by VladimirV on 08.02.2017.
 */
public class ExcelWriter {
    private String path;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelWriter(String path) {
        this.path = path;
    }

    private void writeCaption(){
        Row row = sheet.getRow(0);
        Cell cell = row.createCell(8);
        cell.setCellValue("Name");
        cell = row.createCell(9);
        cell.setCellValue("Price");
        cell = row.createCell(10);
        cell.setCellValue("Availability");
        cell = row.createCell(11);
        cell.setCellValue("Purchased");
    }

    public void writeResults(String[] resultsArray, int rowNumber) throws IOException {
        fileInputStream = new FileInputStream(new File(path));
        this.workbook = new XSSFWorkbook(fileInputStream);
        this.sheet = workbook.getSheetAt(0);
        writeCaption();
        Row row = sheet.getRow(rowNumber);

            Cell cell = row.createCell(8);
            cell.setCellValue(resultsArray[0]);
            cell = row.createCell(9);
            cell.setCellValue(resultsArray[1]);
            cell = row.createCell(10);
            cell.setCellValue(resultsArray[2]);
            cell = row.createCell(11);
            cell.setCellValue(resultsArray[3]);




        fileInputStream.close();
        try {
            fileOutputStream = new FileOutputStream(new File(path));
        } catch (FileNotFoundException e){
            System.out.println("Close the excel file!");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.exit(1);
        }

        workbook.write(fileOutputStream);
        fileOutputStream.close();


    }
}
