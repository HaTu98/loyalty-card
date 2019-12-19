package vn.sapo.loyaltycard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.loyaltycard.dto.transaction.TransactionRequest;
import vn.sapo.loyaltycard.model.LoyaltyCard;
import vn.sapo.loyaltycard.service.TransactionService;

@RequestMapping("/transactions")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public LoyaltyCard transaction(@RequestBody TransactionRequest request) {
        return transactionService.payment(request);
    }
}
