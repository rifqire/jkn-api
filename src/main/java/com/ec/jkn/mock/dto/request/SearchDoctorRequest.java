package com.ec.jkn.mock.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDoctorRequest {
    // Ini untuk pagination
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    // Ini untuk datanya
    private String name;
    private String strNumber;
    private String specialization;
    private Integer experienceYears;
}
