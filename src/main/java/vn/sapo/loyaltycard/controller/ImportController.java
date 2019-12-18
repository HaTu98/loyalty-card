package vn.sapo.loyaltycard.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.model.LoyaltyCardType;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RequestMapping("/import")
@RestController
public class ImportController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    @PostMapping()
    public String importData(@RequestParam("file") MultipartFile data) throws IOException, ParseException {
        XSSFWorkbook workbook = new XSSFWorkbook(data.getInputStream());
        XSSFSheet loyaltyCardType = workbook.getSheet("LoyaltyCardType");
        XSSFSheet loyaltyCard = workbook.getSheet("LoyaltyCard");
        XSSFSheet transaction = workbook.getSheet("Transaction");

        List<String> headerCardType = Arrays.asList(
                "Id","Name","SpentThreshold","Duration","DiscountPercent","CreatedOn", "ModifiedOn"
        );

        List<String> headerCard = Arrays.asList(
                "Id","Code","Phone","LoyaltyCartTypeId","Point","TotalSpent", "StartDate","EndDate","CreatedOn", "ModifiedOn"
        );

        List<String> headerTransaction = Arrays.asList(
                "Id","","LoyaltyCardId","PointAdjust","SpentAdjust","CreatedOn"
        );

        List<LoyaltyCard> loyaltyCards = this.getLoyaltyCard(loyaltyCard);


        return "success";
    }


    private List<LoyaltyCard> getLoyaltyCard(XSSFSheet sheet) throws ParseException {
        List<LoyaltyCard> loyaltyCards = new ArrayList<>();
        for (int i = 1 ; i <= sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            LoyaltyCard loyaltyCard = new LoyaltyCard();

            Long id = Long.getLong(getCell(row.getCell(0)));
            String code = getCell(row.getCell(1));
            String phone = getCell(row.getCell(2));
            Long  loyaltyCartTypeId = Long.getLong(getCell(row.getCell(3)));
            Float  point = Float.parseFloat(getCell(row.getCell(4)).replace(",","."));
            Float  totalSpent = Float.parseFloat(getCell(row.getCell(5)).replace(".",""));
            LocalDate startDate = LocalDate.parse(getCell(row.getCell(6)),formatter);
            LocalDate  endDate = LocalDate.parse(getCell(row.getCell(7)),formatter);
            LocalDate  createdOn = LocalDate.parse(getCell(row.getCell(8)),formatter);
            LocalDate modifiedOn = LocalDate.parse(getCell(row.getCell(9)),formatter);

            loyaltyCard.setId(id);
            loyaltyCard.setCode(code);
            loyaltyCard.setPhone(phone);
            loyaltyCard.setLoyaltyCartTypeId(loyaltyCartTypeId);
            loyaltyCard.setPoint(point);
            loyaltyCard.setTotalSpent(totalSpent);
            loyaltyCard.setStartDate(startDate);
            loyaltyCard.setEndDate(endDate);
            loyaltyCard.setCreatedOn(createdOn);
            loyaltyCard.setModifiedOn(modifiedOn);
            loyaltyCards.add(loyaltyCard);
        }
        return loyaltyCards;
    }

    private String getCell(Cell cell){
        if(cell == null ) return null;
        if(cell.getCellTypeEnum() == CellType.STRING){
            return cell.getStringCellValue();
        }else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
            Double  cellData = cell.getNumericCellValue();
            return cellData.toString();
        } else {
            return null;
        }
    }
}
