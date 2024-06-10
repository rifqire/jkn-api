package com.ec.jkn.mock.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Birth date is required")
    private String birthDate;
    @NotBlank(message = "BPJS number is required")
    private String bpjsNumber;
    @NotBlank(message = "Faskes is required")
    private String faskes;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotNull(message = "Choose true or false")
    private Boolean isActive;
}
