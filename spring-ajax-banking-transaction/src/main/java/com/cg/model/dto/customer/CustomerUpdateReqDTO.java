package com.cg.model.dto.customer;

import com.cg.model.Customer;
import com.cg.model.dto.locationRegion.LocationRegionCreReqDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerUpdateReqDTO {
    private String fullName;
    private String email;
    private String phone;
    private LocationRegionCreReqDTO locationRegion;

    public Customer toCustomer(Customer customer) {
        return new Customer()
                .setId(customer.getId())
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(BigDecimal.ZERO)
                .setLocationRegion(locationRegion.toLocationRegion());
    }

}
