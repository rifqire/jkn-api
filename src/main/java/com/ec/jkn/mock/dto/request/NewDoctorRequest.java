package com.ec.jkn.mock.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewDoctorRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "STR number is required")
    private String strNumber;
    @NotBlank(message = "Specialization is required")
    private String specialization;
    @NotNull(message = "Experience years is required")
    private Integer experienceYears;
}
