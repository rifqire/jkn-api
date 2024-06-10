package com.ec.jkn.mock.entity;

import com.ec.jkn.mock.constant.TableConstants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = TableConstants.DIAGNOSE)
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visit_date", nullable = false, updatable = false)
    private Date visitDate;
    @OneToMany(mappedBy = "diagnose")
    @JsonManagedReference
    private List<DiagnoseDetail> diagnoseDetails;
}
