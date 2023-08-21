package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Customer;
import com.cg.model.dto.customer.*;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerApi {
    @Autowired
    private ICustomerService customerService;
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerResDTO> customerResDTOS = customerService.findAllCustomerResDTO(false);
        if (customerResDTOS.size() == 0) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Không có khách hàng trong danh sách");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(customerResDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResDTO> getById(@PathVariable Long id) {

        Optional<Customer> customerOptional = customerService.findById(id);

        if (customerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = customerOptional.get();

        CustomerResDTO customerResDTO = customer.toCustomerResDTO();

        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerCreReqDTO customerCreReqDTO) {
        CustomerCreResDTO customerCreResDTO = customerService.create(customerCreReqDTO);

        return new ResponseEntity<>(customerCreResDTO, HttpStatus.CREATED);
    }
    @PatchMapping("/{customerId}")
    public ResponseEntity<?> update(@PathVariable String customerId, @RequestBody CustomerUpdateReqDTO customerUpdateReqDTO) {

        Customer customer = customerService.findById(Long.valueOf(customerId)).orElseThrow(() ->
                new DataInputException("Mã khách hàng không tồn tại"));

        CustomerUpdateResDTO customerUpdateResDTO = customerService.update(customerUpdateReqDTO, customer);

        return new ResponseEntity<>(customerUpdateResDTO, HttpStatus.OK);

    }

    @PatchMapping("/delete/{customerId}")
    public ResponseEntity<?> delete(@PathVariable String customerId) {
        Customer customer = customerService.findById(Long.valueOf(customerId)).orElseThrow(() ->
                new DataInputException("Mã khách hàng không tồn tại"));

        customerService.softDeleteById(Long.valueOf(customerId));


        return new ResponseEntity<>(customer.toCustomerResDTO(), HttpStatus.OK);

    }
    @GetMapping("/recipients-without-sender/{senderId}")
    public ResponseEntity<?> getAllRecipientWithoutSender(@PathVariable Long senderId) {
        List<CustomerResDTO> recipients = customerService.findAllRecipientsWithoutSenderId(senderId);
        return new ResponseEntity<>(recipients, HttpStatus.OK);
    }
}
