package com.ec.jkn.mock.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDoctorRequest {
    private String name;
    private String strNumber;
    private String specialization;
    private Integer experienceYears;
}
