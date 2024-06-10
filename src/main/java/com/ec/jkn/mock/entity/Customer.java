package com.ec.jkn.mock.entity;

import com.ec.jkn.mock.constant.TableConstants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = TableConstants.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private String birthDate;
    @Column(name = "bpjs_number", nullable = false, unique = true)
    private String bpjsNumber;
    @Column(name = "faskes", nullable = false)
    private String faskes;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
