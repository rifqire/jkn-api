package com.ec.jkn.mock.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnoseDetailResponse {
    private String id;
    private String diagnoseId;
    private String doctorId;
    private String illnessId;
    private String diagnoseResult;
    private Boolean isReferenced;
}
