package vn.sapo.loyaltycard.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.sapo.loyaltycard.dao.LoyaltyCardDao;
import vn.sapo.loyaltycard.dao.LoyaltyCardTypeDao;
import vn.sapo.loyaltycard.dao.TransactionDao;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.model.LoyaltyCardType;
import vn.sapo.loyaltycard.model.Transaction;
import vn.sapo.loyaltycard.service.TransactionService;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/import")
@RestController
public class ImportController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    @Autowired
    private LoyaltyCardDao loyaltyCardDao;

    @Autowired
    private LoyaltyCardTypeDao loyaltyCardTypeDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public String importData(@RequestParam("file") MultipartFile data) throws IOException, ParseException {
        XSSFWorkbook workbook = new XSSFWorkbook(data.getInputStream());
        XSSFSheet loyaltyCardType = workbook.getSheet("LoyaltyCardType");
        XSSFSheet loyaltyCard = workbook.getSheet("LoyaltyCard");
        XSSFSheet transaction = workbook.getSheet("Transaction");

        List<LoyaltyCard> loyaltyCards = this.getLoyaltyCard(loyaltyCard);
        List<LoyaltyCardType> loyaltyCardTypes = this.getLoyaltyCardType(loyaltyCardType);
        List<Transaction> transactions = this.getTransaction(transaction);

        loyaltyCardDao.save(loyaltyCards);
        loyaltyCardTypeDao.save(loyaltyCardTypes);
        //transactionDao.save(transactions);

        transactionService.importAccumulate(transactions);
        return "success";
    }

    private List<Transaction> getTransaction(XSSFSheet sheet) throws ParseException {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            Transaction transaction = new Transaction();
            String idValue = getCell(row.getCell(0));
            if (idValue != null) {
                Float idFloat = Float.parseFloat(idValue);
                Long id = idFloat.longValue();

                Float loyaltyCardIdFloat = Float.parseFloat(getCell(row.getCell(1)));
                Long loyaltyCardId = loyaltyCardIdFloat.longValue();

                Float pointAdjust = Float.parseFloat(getCell(row.getCell(2)).replace(",","."));
                Float spentAdjust = Float.parseFloat(getCell(row.getCell(3)).replace(".", ""));
                LocalDate createdOn = LocalDate.parse(getCell(row.getCell(4)), formatter);

                //transaction.setId(id);
                transaction.setLoyaltyCardId(loyaltyCardId);
                transaction.setPointAdjust(pointAdjust);
                transaction.setSpentAdjust(spentAdjust);
                transaction.setCreatedOn(createdOn);

                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private List<LoyaltyCardType> getLoyaltyCardType(XSSFSheet sheet) throws ParseException {
        List<LoyaltyCardType> loyaltyCardTypes = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            LoyaltyCardType loyaltyCardType = new LoyaltyCardType();

            String idValue = getCell(row.getCell(0));
            if (idValue != null) {
                Float idFloat = Float.parseFloat(idValue);
                Long id = idFloat.longValue();
                String name = getCell(row.getCell(1));
                Float spentThreshold = Float.parseFloat(getCell(row.getCell(2)).replace(".", ""));
                Integer duration = Integer.getInteger(getCell(row.getCell(3)));
                Float discountPercent = Float.parseFloat(getCell(row.getCell(4)).replace("%", ""));
                LocalDate createdOn = LocalDate.parse(getCell(row.getCell(5)), formatter);
                LocalDate modifiedOn = LocalDate.parse(getCell(row.getCell(6)), formatter);

                loyaltyCardType.setId(id);
                loyaltyCardType.setName(name);
                loyaltyCardType.setSpentThreshold(spentThreshold);
                loyaltyCardType.setDuration(duration);
                loyaltyCardType.setDiscountPercent(discountPercent);
                loyaltyCardType.setCreatedOn(createdOn);
                loyaltyCardType.setModifiedOn(modifiedOn);

                loyaltyCardTypes.add(loyaltyCardType);
            }


        }
        return loyaltyCardTypes;
    }


    private List<LoyaltyCard> getLoyaltyCard(XSSFSheet sheet) throws ParseException {
        List<LoyaltyCard> loyaltyCards = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            LoyaltyCard loyaltyCard = new LoyaltyCard();

            String idValue = getCell(row.getCell(0));
            if (idValue != null) {
                Float idFloat = Float.parseFloat(idValue);
                Long id = idFloat.longValue();
                String code = getCell(row.getCell(1));
                String phone = getCell(row.getCell(2));

                Float loyaltyCartTypeIdFloat = Float.parseFloat(getCell(row.getCell(3)));
                Long loyaltyCartTypeId = loyaltyCartTypeIdFloat.longValue();

                Float point = Float.parseFloat(getCell(row.getCell(4)).replace(",", "."));
                Float totalSpent = Float.parseFloat(getCell(row.getCell(5)).replace(".", ""));
                LocalDate startDate = LocalDate.parse(getCell(row.getCell(6)), formatter);
                LocalDate endDate = LocalDate.parse(getCell(row.getCell(7)), formatter);
                LocalDate createdOn = LocalDate.parse(getCell(row.getCell(8)), formatter);
                LocalDate modifiedOn = LocalDate.parse(getCell(row.getCell(9)), formatter);

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

        }
        return loyaltyCards;
    }

    private String getCell(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            Double cellData = cell.getNumericCellValue();
            return cellData.toString();
        } else {
            return null;
        }
    }
}
