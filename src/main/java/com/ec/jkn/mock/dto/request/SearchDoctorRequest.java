package com.ec.jkn.mock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDoctorRequest {
    private String name;
    private String strNumber;
    private String specialization;
    private Integer experienceYears;
}
