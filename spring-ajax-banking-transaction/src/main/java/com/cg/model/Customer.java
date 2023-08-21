package com.cg.model;

import com.cg.model.dto.customer.CustomerCreResDTO;
import com.cg.model.dto.customer.CustomerResDTO;
import com.cg.model.dto.customer.CustomerUpdateResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String phone;
    @Column(precision = 10, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Deposit> deposits;

    public CustomerResDTO toCustomerResDTO(){
        return new CustomerResDTO()
                .setId(this.getId())
                .setFullName(this.fullName)
                .setEmail(this.email)
                .setPhone(this.phone)
                .setBalance(this.balance)
                .setLocationRegion(locationRegion.toLocationRegionResDTO());
    }
    public CustomerCreResDTO toCustomerCreResDTO() {
        return new CustomerCreResDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(balance)
                .setLocationRegion(locationRegion.toLocationRegionCreResDTO())
                ;
    }
    public CustomerUpdateResDTO toCustomerUpdateResDTO() {
        return new CustomerUpdateResDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(balance)
                .setLocationRegion(locationRegion.toLocationRegionCreResDTO())
                ;
    }

}
