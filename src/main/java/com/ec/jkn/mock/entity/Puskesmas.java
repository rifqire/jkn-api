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
@Table(name = TableConstants.PUSKESMAS)
public class Puskesmas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
