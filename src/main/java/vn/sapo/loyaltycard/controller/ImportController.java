package vn.sapo.loyaltycard.controller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/import")
@RestController
public class ImportController {

    @PostMapping()
    public String importData(@RequestParam("file") MultipartFile data) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(data.getInputStream());
        XSSFSheet loyaltyCardType = workbook.getSheet("LoyaltyCardType");
        XSSFSheet loyaltyCard = workbook.getSheet("LoyaltyCard");
        XSSFSheet transaction = workbook.getSheet("Transaction");

        return "success";
    }
}
