package com.iat.dataProvider

import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

class dataProvider {
    int i, j = 0
    Object[][] data = new Object[5][6]

    Object[][] returndata(String filename) {
        String fileAbsolutePath = new File(filename).getAbsolutePath().replace('\\', '\\\\')
        File myFile = new File(fileAbsolutePath)
        FileInputStream fis = new FileInputStream(myFile)

        // Finds the workbook instance for XLSX file
        HSSFWorkbook myWorkBook = new HSSFWorkbook(fis)

        // Return first sheet from the XLSX workbook
        HSSFSheet mySheet = myWorkBook.getSheetAt(0)

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator()
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            j = 0
            Row row = rowIterator.next()

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator()

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next()
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        //System.out.print(cell.getStringCellValue() + "\t");
                        data[i][j] = cell.getStringCellValue()
                        break
                    case Cell.CELL_TYPE_NUMERIC:
                        //System.out.print(cell.getNumericCellValue() + "\t");
                        data[i][j] = cell.getNumericCellValue()
                        break
                    case Cell.CELL_TYPE_BOOLEAN:
                        //System.out.print(cell.getBooleanCellValue() + "\t");
                        data[i][j] = cell.getBooleanCellValue()
                        break
                }
                j++
            }
            i++
        }
        return data
    }
}