package com.ec.jkn.mock.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnoseDetailRequest {
    private String diagnoseId;
    private String doctorId;
    private String illnessId;
    private String diagnoseResult;
    private Boolean isReferenced;
}
