package net.hmcts.refdata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ExcelProcessor implements MultipartProcessor {
    @Override
    public String[] process(MultipartFile f) {
        try {
            XSSFWorkbook wb  = new XSSFWorkbook(f.getInputStream());
            XSSFSheet ws = wb.getSheetAt(0);
            for (Row r: ws) {
                System.out.print("Row " + r.getRowNum()+": ");
                 for (Cell c: r) {
                     System.out.print(c.getStringCellValue() + " ");
                 }
                 System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
