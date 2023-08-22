import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;

//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcel {
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("C://Users//User//IdeaProjects//Trial//LoginData1.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet("Sheet2");

        // returns the row count
        int rowCount = sheet.getLastRowNum();

        // returns the column/cell count
        int colCount = sheet.getRow(0).getLastCellNum();

        for (int i = 0; i < rowCount; i++) {
            // focused on current row
            XSSFRow currentRow = sheet.getRow(i);

            for (int j = 0; j < colCount; j++) {
                // read value from a cell
                String value = currentRow.getCell(j).toString();
                System.out.print("    " + value);
            }
            System.out.println();
        }
    }
}