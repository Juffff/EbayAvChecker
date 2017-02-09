import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ExcelReader {
    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private ArrayList<ItemFromExcel> itemListFromExcel = new ArrayList<>();

    public ExcelReader(String path) throws IOException {
        this.fileInputStream = new FileInputStream(new File(path));
        this.workbook = new XSSFWorkbook(fileInputStream);
        this.sheet = workbook.getSheetAt(0);
    }

    public void read() {
        int rowCount = sheet.getLastRowNum();
        for (int k = 1; k <= rowCount; k++) {
            Row row = sheet.getRow(k);
            ItemFromExcel itemFromExcel = new ItemFromExcel(row.getCell(0).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString());
            itemListFromExcel.add(itemFromExcel);
        }
    }

    public ArrayList<ItemFromExcel> getItemListFromExcel() throws IOException {
        fileInputStream.close();
        return itemListFromExcel;
    }

}


