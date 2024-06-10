package com.ec.jkn.mock.entity;

import com.ec.jkn.mock.constant.TableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = TableConstants.DIAGNOSE_DETAIL)
public class DiagnoseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "diagnose_id", nullable = false)
    @JsonBackReference
    private Diagnose diagnose;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "illness_id", nullable = false)
    private Illness illness;
    @Column(name = "diagnose_result", nullable = false, updatable = false)
    private String diagnoseResult;
    @Column(name = "is_referenced", nullable = false, updatable = false)
    private Boolean isReferenced;
}
