package pdp.uz.lesson6.component;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import pdp.uz.lesson6.entity.Details;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class DetailExporter {
    public void exportPDF(HttpServletResponse response, List<Details> detailsList, String code) throws IOException {
        //code
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("Information by SimCard:" + code));

        PdfPTable table = new PdfPTable(3);//3 ustunlar soni
        table.setWidthPercentage(100);//table uzunligi, foizda
        table.setSpacingBefore(15);//bu jadval orasidagi joy, 15 px


        //tablening header qismini yozish
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("№", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("ActionType", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
        
        int i = 1;
        for (Details details : detailsList) {
            table.addCell(String.valueOf(i));
            table.addCell(details.getActionType().name());
            table.addCell(String.valueOf(details.getAmount()));
            i++;
        }

        document.add(table);
        document.close();
    }

    public void exportExcel(HttpServletResponse response, List<Details> detailsList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet =  workbook.createSheet("Details");

        //write header row
        XSSFRow row = sheet.createRow(0);


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        XSSFCell cell = row.createCell(0);
        cell.setCellValue("№");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("ActionType");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Amount");
        cell.setCellStyle(style);

        font.setBold(false);
        font.setFontHeight(14);
        style.setFont(font);

        int rowCount = 1;
        for (Details details : detailsList) {
            XSSFRow xssfRow = sheet.createRow(rowCount++);

            XSSFCell xssfCell = xssfRow.createCell(0);
            xssfCell.setCellValue(rowCount);
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            xssfCell = xssfRow.createCell(1);
            xssfCell.setCellValue(details.getActionType().name());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            xssfCell = xssfRow.createCell(2);
            xssfCell.setCellValue(details.getAmount());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
