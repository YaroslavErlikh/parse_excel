package hackaton.crazy_devs.animals_without_owners.configuration.initalizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public HashMap<Integer, List<Object>> read(String filename) throws IOException {
        Workbook workbook = loadWorkbook(filename);
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            return processSheet(sheet);
        }
        return null;
    }

    private Workbook loadWorkbook(String filename) throws IOException {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        FileInputStream file = new FileInputStream(new ClassPathResource(filename).getFile());
        switch (extension) {
            case "xls":
                // old format
                return new HSSFWorkbook(file);
            case "xlsx":
                // new format
                return new XSSFWorkbook(file);
            default:
                throw new RuntimeException("Unknown Excel file extension: " + extension);
        }
    }

    private HashMap<Integer, List<Object>> processSheet(Sheet sheet) {
        System.out.println("Sheet: " + sheet.getSheetName());
        HashMap<Integer, List<Object>> data = new HashMap<Integer, List<Object>>();
        Iterator<Row> iterator = sheet.rowIterator();
        for (int rowIndex = 0; iterator.hasNext(); rowIndex++) {
            Row row = iterator.next();
            processRow(data, rowIndex, row);
        }
        System.out.println("Sheet data:");
        return data;
    }

    private void processRow(HashMap<Integer, List<Object>> data, int rowIndex, Row row) {
        data.put(rowIndex, new ArrayList<>());
        for (Cell cell : row) {
            processCell(cell, data.get(rowIndex));
        }
    }

    private void processCell(Cell cell, List<Object> dataRow) {
        switch (cell.getCellType()) {
            case STRING:
                dataRow.add(cell.getStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    dataRow.add(cell.getLocalDateTimeCellValue());
                } else {
                    dataRow.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
                break;
            case BOOLEAN:
                dataRow.add(cell.getBooleanCellValue());
                break;
            case FORMULA:
                dataRow.add(cell.getCellFormula());
                break;
            default:
                dataRow.add(" ");
        }
    }
}
