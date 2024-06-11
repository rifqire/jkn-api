package com.ec.jkn.mock.controller;

import com.ec.jkn.mock.constant.APIUrl;
import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.dto.request.DiagnoseRequest;
import com.ec.jkn.mock.dto.response.CommonResponse;
import com.ec.jkn.mock.dto.response.DiagnoseResponse;
import com.ec.jkn.mock.service.DiagnoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.DIAGNOSE_API)
public class DiagnoseController {
    private final DiagnoseService diagnoseService;

    @PostMapping
    public ResponseEntity<CommonResponse<DiagnoseResponse>> createDiagnose(@RequestBody DiagnoseRequest request) {
        DiagnoseResponse newDiagnose = diagnoseService.create(request);
        // Builder dari common response
        CommonResponse<DiagnoseResponse> response = CommonResponse.<DiagnoseResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newDiagnose)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Method create all
    @GetMapping
    public List<DiagnoseResponse> getAllDiagnoses() {
        return diagnoseService.getAllDiagnoses();
    }
}
