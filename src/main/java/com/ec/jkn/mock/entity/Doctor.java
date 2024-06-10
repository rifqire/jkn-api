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
@Table(name = TableConstants.DOCTOR)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "str_number", nullable = false, unique = true)
    private String strNumber;
    @Column(name = "specialization", nullable = false)
    private String specialization;
    @Column(name = "experience_years", nullable = false)
    private Integer experienceYears;
}
