package com.ec.jkn.mock.service;


import com.ec.jkn.mock.dto.request.DiagnoseRequest;
import com.ec.jkn.mock.dto.response.DiagnoseResponse;

import java.util.List;

public interface DiagnoseService {
    DiagnoseResponse create(DiagnoseRequest request);

    List<DiagnoseResponse> getAllDiagnoses();
}
