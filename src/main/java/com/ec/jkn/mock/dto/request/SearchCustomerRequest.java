package com.ec.jkn.mock.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    // Ini untuk pagination
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    // Ini untuk datanya
    private String name;
    private String birthDate;
    private String bpjsNumber;
    private String faskes;
    private String phoneNumber;
    private Boolean isActive;
}
