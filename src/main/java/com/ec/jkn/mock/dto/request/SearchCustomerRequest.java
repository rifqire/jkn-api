package com.ec.jkn.mock.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String birthDate;
    private String bpjsNumber;
    private String faskes;
    private String phoneNumber;
    private Boolean isActive;
}
