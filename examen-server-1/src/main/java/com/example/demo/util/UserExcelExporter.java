package com.example.demo.util;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entity.Persona;
 
public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Persona> listPersona;
     
    public UserExcelExporter(List<Persona> listPersonas) {
        this.listPersona = listPersonas;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Personas");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Tipo Doc.", style);      
        createCell(row, 1, "Numero Doc.", style);       
        createCell(row, 2, "Nombres", style);    
        createCell(row, 3, "Apellidos", style);
        createCell(row, 4, "Fecha de Nacimiento", style);
        createCell(row, 5, "Genero", style);      
        createCell(row, 6, "Edad", style);       
        createCell(row, 7, "Departamento", style);    
        createCell(row, 8, "Provincia", style);
        createCell(row, 9, "Distrito", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Persona persona : listPersona) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, persona.getDocumentType(), style);
            createCell(row, columnCount++, persona.getDocumentId(), style);
            createCell(row, columnCount++, persona.getName(), style);
            createCell(row, columnCount++, persona.getLastName(), style);
            createCell(row, columnCount++, persona.getBirthdayDate(), style);
            createCell(row, columnCount++, persona.getGender(), style);
            createCell(row, columnCount++, persona.getAge(), style);
            createCell(row, columnCount++, persona.getDepartamento(), style);
            createCell(row, columnCount++, persona.getProvincia(), style);
            createCell(row, columnCount++, persona.getDistrito(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}