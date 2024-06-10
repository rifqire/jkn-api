package com.ec.jkn.mock.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnoseResponse {
    private String id;
    private String customerId;
    private String puskesmasId;
    private Date visitDate;
    private List<DiagnoseDetailResponse> diagnoseDetails;
}
