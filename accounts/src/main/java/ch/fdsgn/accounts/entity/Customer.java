package ch.fdsgn.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;

    public static Customer create(String email, String name, String mobileNumber) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setName(name);
        customer.setMobileNumber(mobileNumber);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("admin");
        return customer;
    }
}
