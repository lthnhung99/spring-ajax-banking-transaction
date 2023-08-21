package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.dto.DepositReqDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/deposits")
public class DepositApi {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private AppUtils appUtils;
    @PostMapping("/{customerId}")
    public ResponseEntity<?> deposit(@RequestBody DepositReqDTO depositReqDTO, BindingResult bindingResult) {
        new DepositReqDTO().validate(depositReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (depositReqDTO.getCustomerId() == null || depositReqDTO.getCustomerId().length() == 0) {
            throw new DataInputException("Vui lòng nhập mã khách hàng");
        }

//        if (depositReqDTO.getTransactionAmount() == null || depositReqDTO.getTransactionAmount().length() == 0) {
//            throw new DataInputException("Vui lòng nhập số tiền giao dịch");
//        }

        if (!ValidateUtils.isNumberValid(depositReqDTO.getCustomerId())) {
            throw new DataInputException("Vui lòng nhập mã khách hàng bằng ký tự số");
        }

//        if (!ValidateUtils.isNumberValid(depositReqDTO.getTransactionAmount())) {
//            throw new DataInputException("Vui lòng nhập số tiền giao dịch bằng ký tự số");
//        }

        Long customerId = Long.parseLong(depositReqDTO.getCustomerId());

        Customer customer = customerService.findById(customerId).orElseThrow(() -> {
            throw new DataInputException("Mã khách hàng không tồn tại");
        });

        BigDecimal transactionAmount = BigDecimal.valueOf(Long.parseLong(depositReqDTO.getTransactionAmount()));

        Deposit deposit = new Deposit();
        deposit.setCustomer(customer);
        deposit.setTransactionAmount(transactionAmount);

        customerService.deposit(deposit);
        Optional<Customer> updateCustomer = customerService.findById(deposit.getCustomer().getId());

        return new ResponseEntity<>(updateCustomer.get().toCustomerResDTO(), HttpStatus.OK);
    }
}
