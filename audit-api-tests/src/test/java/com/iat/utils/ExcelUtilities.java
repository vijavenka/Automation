package com.iat.utils;

import com.iat.domain.Answer;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilities {
    private XSSFWorkbook workbook;
    private FileInputStream input_document;
    private FileOutputStream output_file;
    private String fullFileAccessName;

    public void openHeadless(String fileName, String path) {
        try {
            fullFileAccessName = path.replace("//", "\\") + fileName;
            input_document = new FileInputStream(new File(fullFileAccessName));
            workbook = new XSSFWorkbook(input_document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRowColumnsAmount() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 0; ; ++i) {
            if (sheet.getRow(0).getCell(i) == null || sheet.getRow(0).getCell(i).toString().isEmpty())
                return i;
        }
    }

    public int getRowsAmount(int sheetNo) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        for (int i = 0; ; ++i) {
            if (sheet.getLastRowNum() == i || sheet.getRow(i + 1).getCell(0) == null || sheet.getRow(i + 1).getCell(0).toString().isEmpty()) {
//                System.out.println("\nRows: " + i);
                return i;
            }
        }
    }

    public int getColumnNrByName(String column, int sheetNo) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        for (int i = 0; ; ++i) {
            if (sheet.getRow(0).getCell(i) == null || sheet.getRow(0).getCell(i).toString().isEmpty())
                return -1;
            if (sheet.getRow(0).getCell(i).toString().equals(column))
                return i;
        }
    }

    public String getCellRawValue(int sheetNo, int rowNo, String columnName) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);

        if (sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).toString().isEmpty())
            return "is empty!!!";
        else
            return String.valueOf(sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).getRawValue());

    }

    public String getCellStringValue(int sheetNo, int rowNo, String columnName) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);

        if (sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).toString().isEmpty())
            return "is empty!!!";
        else
            return sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).getStringCellValue();

    }

    public String getCellNumericValueAsString(int sheetNo, int rowNo, String columnName) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);

        if (sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).toString().isEmpty())
            return "is empty!!!";
        else
            return String.valueOf((int) sheet.getRow(rowNo).getCell(getColumnNrByName(columnName, sheetNo)).getNumericCellValue());

    }

    public void modifyCellsForColumn(String column, String columnData) {
        String[] columnDataArray = columnData.split(",");
        XSSFSheet sheet = workbook.getSheetAt(0);
        int colNr = getColumnNrByName(column, 0);
        for (int i = 0; i < columnDataArray.length; ++i) {
            sheet.getRow(i + 1).getCell(colNr).setCellValue(columnDataArray[i]);
        }
        writeToFile();
    }

    public void modifyCellsForColumnAtSheet(String column, String columnData, int sheetNo) {
        String[] columnDataArray = columnData.split(",");
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        int colNr = getColumnNrByName(column, sheetNo);
        for (int i = 0; i < columnDataArray.length; i++) {
//            System.out.println("row: " +(i+1) + " cell: " + colNr + " value2: " + columnDataArray[i]);
            sheet.getRow(i + 1).getCell(colNr).setCellValue(columnDataArray[i]);
        }
        writeToFile();
    }

    public void modifyCellsForColumnAtSheet(String columns, List<String> columnData, int sheetNo) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        for (int i = 0; i < columnData.size(); i++) {
            System.out.println("row: " + (i + 1) + " cell: " + getColumnNrByName(columns, sheetNo) + " value: " + columnData.get(i));
            sheet.getRow(i + 1).getCell(getColumnNrByName(columns, sheetNo)).setCellValue(columnData.get(i));
        }
        writeToFile();
    }

    public void modifyCellsForColumnAtSheet(List<String> columns, List<String> columnData, int sheetNo) {
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        for (int i = 0; i < columnData.size(); ++i) {
            sheet.getRow(i + 1).getCell(getColumnNrByName(columns.get(i), sheetNo)).setCellValue(columnData.get(i));
        }
        writeToFile();
    }

    public void modifyCellsForColumnAtSheetAsDouble(String column, String columnData, int sheetNo) {
        String[] columnDataArray = columnData.split(",");
        XSSFSheet sheet = workbook.getSheetAt(sheetNo);
        int colNr = getColumnNrByName(column, sheetNo);
        for (int i = 0; i < columnDataArray.length; ++i) {
//            System.out.println("row: " +(i+1) + " cell: " + colNr + " value2: " + columnDataArray[i]);
            sheet.getRow(i + 1).getCell(colNr).setCellValue(Double.parseDouble(columnDataArray[i]));
        }
        writeToFile();
    }

    public List<Answer> getAnswersCallsValuesForTodays() {
        XSSFSheet sheetHeader = workbook.getSheetAt(0);
        int col_CallID_No = getColumnNrByName("CallID", 0);
        int col_c_store_id_No = getColumnNrByName("c_Store_ID", 0);

        List<Answer> answers = new ArrayList<>();
        int rowsAmount = getRowsAmount(0);

        for (int i = 0; i < rowsAmount; i++) {
            Answer answer;

            String CallID = String.valueOf(sheetHeader.getRow(i + 1).getCell(col_CallID_No).getRawValue());
            String c_Store_ID = String.valueOf((int) sheetHeader.getRow(i + 1).getCell(col_c_store_id_No).getNumericCellValue());

            System.out.println("CallID: " + CallID);
            System.out.println("c_Store_ID: " + c_Store_ID);

            XSSFSheet sheetDetail = workbook.getSheetAt(1);
            int col_c_call_id_No = getColumnNrByName("c_call_id", 1);

            for (int j = 0; j < getRowsAmount(1); j++) {
//                System.out.println("c_call_id: " + String.valueOf(sheetDetail.getRow(j+1).getCell(col_c_call_id_No).getRawValue()));
                if (CallID.equals(String.valueOf(sheetDetail.getRow(j + 1).getCell(col_c_call_id_No).getRawValue()))) {
                    answer = new Answer();
                    answer.setResults_CallID(CallID);
                    answer.setResults_c_Store_ID(c_Store_ID);

                    int colNo = getColumnNrByName("question_type", 1);
                    answer.setResults_question_type(String.valueOf(sheetDetail.getRow(j + 1).getCell(colNo).getRawValue()));
                    colNo = getColumnNrByName("question_id", 1);
                    answer.setResults_question_id(String.valueOf(sheetDetail.getRow(j + 1).getCell(colNo).getStringCellValue()));
                    colNo = getColumnNrByName("response", 1);
                    answer.setResults_response(String.valueOf(sheetDetail.getRow(j + 1).getCell(colNo).getStringCellValue()));
                    colNo = getColumnNrByName("c_product_id", 1);
                    answer.setResults_c_product_id(String.valueOf(sheetDetail.getRow(j + 1).getCell(colNo).getStringCellValue()));
                    answers.add(answer);

//                    System.out.println("response: " + answer.getResults_response() + " qt " + answer.getResults_question_type() + " qid " + answer.getResults_question_id() + " pid " + answer.getResults_c_product_id() + " stExtRelId " + answer.getResults_c_Store_ID());
                }
            }
        }
        return answers;
    }

    public List<Answer> getAnswersCallsValuesForPremier() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        int col_c_store_id_No = getColumnNrByName("Location ID", 0);

        List<Answer> answers = new ArrayList<>();
        int rowsAmount = getRowsAmount(0);

        for (int i = 0; i < rowsAmount; i++) {
            Answer answer = new Answer();

            String c_Store_ID = String.valueOf(sheet.getRow(i + 1).getCell(col_c_store_id_No).getStringCellValue());

//            System.out.println("c_Store_ID: " + c_Store_ID);

            answer.setResults_c_Store_ID(c_Store_ID);

            int colNo = getColumnNrByName("Question ID", 0);
            answer.setResults_question_id(String.valueOf(sheet.getRow(i + 1).getCell(colNo).getStringCellValue()));
            colNo = getColumnNrByName("Response", 0);
            answer.setResults_response(String.valueOf(sheet.getRow(i + 1).getCell(colNo).getStringCellValue()));
            answers.add(answer);

//          System.out.println("response: " + answer.getResults_response() + " qid " + answer.getResults_question_id() + " stId " + answer.getResults_c_Store_ID());
        }
        return answers;
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
