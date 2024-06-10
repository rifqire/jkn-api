package com.ec.jkn.mock.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnoseRequest {
    private String customerId;
    private String puskesmasId;
    private List<DiagnoseDetailRequest> diagnoseDetails;
}
