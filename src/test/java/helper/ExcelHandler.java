package helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandler {

    public static int getColumnIndexByTitle(Sheet sheet, String title) {
        Row headerRow = sheet.getRow(0); // first row is header
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && cell.getStringCellValue().trim().equalsIgnoreCase(title)) {
                return i; // return matching column index
            }
        }
        return -1; // not found
    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Handle Date
                    return cell.getDateCellValue().toString();
                    // or format with SimpleDateFormat if needed
                } else {
                    // Handle Numeric
                    return String.valueOf(cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                // Evaluate formula result
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                return getCellValue(evaluator.evaluateInCell(cell));

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    public static String getCellValueByTitle(Row row, Sheet sheet, String title) {
        int colIndex = getColumnIndexByTitle(sheet, title);
        if (colIndex == -1) {
            throw new RuntimeException("Column '" + title + "' not found!");
        }
        Cell cell = row.getCell(colIndex);
        return getCellValue(cell); // reuse the safe getCellValue() we wrote earlier
    }

    public static List<String> readExcelFile() throws IOException {
        List<String> output = new ArrayList<>();
        // Path to Excel file
        String filePath = "src/test/resources/data/TestData.xlsx";

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

        Row row = sheet.getRow(avalableRow);
        String titleList = "firstname,lastname,mobile,phone,email --> nomail.com,street,zip,city";
        for (String title : titleList.split(",")) {
            output.add(getCellValueByTitle(row,sheet,title));
        }

//        Cell statusCell = row.getCell(0);
//        if (statusCell == null) {
//            statusCell = row.createCell(0);
//        }
//        statusCell.setCellValue("x");

        // Write changes back
//        FileOutputStream fos = new FileOutputStream(filePath);
//        workbook.write(fos);
//        fos.close();
//        workbook.close();

        System.out.println("Excel updated successfully!");

        return output;
    }
}
