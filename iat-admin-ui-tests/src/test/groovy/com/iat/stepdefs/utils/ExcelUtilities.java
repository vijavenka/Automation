package com.iat.stepdefs.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilities {
    private XSSFWorkbook workbook;
    private FileInputStream input_document;
    private FileOutputStream output_file;
    private String fullFileAccessName;

    public void openHeadless(String fileName, String path) {
        try {
//            fullFileAccessName = path.replace("//", "\\") + fileName;
            fullFileAccessName = path + fileName;
            input_document = new FileInputStream(new File(fullFileAccessName));
            workbook = new XSSFWorkbook(input_document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRowsAmount() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 0; ; ++i) {
            if (sheet.getRow(0).getCell(i) == null || sheet.getRow(0).getCell(i).toString().isEmpty())
                return i;
        }
    }

    public int getColumnNrByName(String column) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 0; ; ++i) {
            if (sheet.getRow(0).getCell(i) == null || sheet.getRow(0).getCell(i).toString().isEmpty())
                return -1;
            if (sheet.getRow(0).getCell(i).toString().equals(column))
                return i;
        }
    }

    public void modifyCellsForColumn(String column, String columnData) {
        String[] columnDataArray = columnData.split(",");
        XSSFSheet sheet = workbook.getSheetAt(0);
        int colNr = getColumnNrByName(column);
        for (int i = 0; i < columnDataArray.length; ++i) {
            sheet.getRow(i + 1).getCell(colNr).setCellValue(columnDataArray[i]);
        }
        writeToFile();
    }

    private void writeToFile() {
        try {
            input_document.close();
            output_file = new FileOutputStream(new File(fullFileAccessName));
            workbook.write(output_file);
            output_file.close();
            input_document = new FileInputStream(new File(fullFileAccessName));
            workbook = new XSSFWorkbook(input_document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            input_document.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
