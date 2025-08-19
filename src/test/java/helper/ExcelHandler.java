package helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandler {
    
    public static List<String> readExcelFile() throws IOException {
        List<String> output = new ArrayList<>();
        // Path to Excel file
        String filePath = "data/TestData.xlsx";

        // Open the Excel file
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // first sheet
        fis.close();

        int avalableRow = 0;

        // Loop through rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // skip header row
            Row row1 = sheet.getRow(i);
            if (row1 == null) continue;

            Cell used = row1.getCell(0);
            if (!used.getStringCellValue().equals("x")) {
                avalableRow = i;
                break;
            }
        }
        System.out.println(output);

        // ✅ Update third column with status


        Row row = sheet.getRow(avalableRow);
        for (int j = 2; j < 11; j++) {
            output.add(row.getCell(avalableRow).getStringCellValue());
        }

        Cell statusCell = row.getCell(0);
        if (statusCell == null) {
            statusCell = row.createCell(0);
        }
        statusCell.setCellValue("x");

        // Write changes back
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("Excel updated successfully!");

        return output;
    }
}
