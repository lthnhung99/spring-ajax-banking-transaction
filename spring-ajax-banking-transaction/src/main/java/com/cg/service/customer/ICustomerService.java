package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Withdraw;
import com.cg.model.dto.TransferCreReqDTO;
import com.cg.model.dto.customer.*;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer,Long> {
    CustomerCreResDTO create(CustomerCreReqDTO customerCreReqDTO);
    CustomerUpdateResDTO update(CustomerUpdateReqDTO customerUpdateReqDTO, Customer customer);
    void softDeleteById(Long id);
    Customer deposit(Deposit deposit);
    List<CustomerResDTO> findAllCustomerResDTO(Boolean deleted);
    Customer withdraw(Withdraw withdraw);
    void transfer(TransferCreReqDTO transferCreReqDTO);
    List<CustomerResDTO> findAllRecipientsWithoutSenderId(Long senderId);
}
