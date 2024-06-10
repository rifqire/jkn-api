package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.dto.request.DiagnoseRequest;
import com.ec.jkn.mock.dto.response.DiagnoseDetailResponse;
import com.ec.jkn.mock.dto.response.DiagnoseResponse;
import com.ec.jkn.mock.entity.Customer;
import com.ec.jkn.mock.entity.Diagnose;
import com.ec.jkn.mock.entity.DiagnoseDetail;
import com.ec.jkn.mock.entity.Puskesmas;
import com.ec.jkn.mock.repository.DiagnoseRepository;
import com.ec.jkn.mock.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiagnoseServiceImpl implements DiagnoseService {
    private final DiagnoseRepository diagnoseRepository;
    private final CustomerService customerService;
    private final PuskesmasService puskesmasService;
    private final DoctorService doctorService;
    private final IllnessService illnessService;
    private final DiagnoseDetailService diagnoseDetailService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DiagnoseResponse create(DiagnoseRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        Puskesmas puskesmas = puskesmasService.getById(request.getPuskesmasId());
        Diagnose diagnose = Diagnose.builder()
                .customer(customer)
                .puskesmas(puskesmas)
                .visitDate(new Date())
                .build();
        diagnoseRepository.saveAndFlush(diagnose);
        log.info("Check detail from diagnose detail: {}", diagnose.getCustomer());

        List<DiagnoseDetail> diagnoseDetails = request.getDiagnoseDetails().stream().map(diagnoseDetailRequest -> {
            log.info("Details from diagnose detail request: {}", diagnoseDetailRequest.getDiagnoseId());
            return DiagnoseDetail.builder()
                    .diagnose(diagnose)
                    .doctor(doctorService.getById(diagnoseDetailRequest.getDoctorId()))
                    .illness(illnessService.getById(diagnoseDetailRequest.getIllnessId()))
                    .diagnoseResult(diagnoseDetailRequest.getDiagnoseResult())
                    .isReferenced(diagnoseDetailRequest.getIsReferenced())
                    .build();
        }).toList();
        diagnoseDetailService.createBulk(diagnoseDetails);
        diagnose.setDiagnoseDetails(diagnoseDetails);

        List<DiagnoseDetailResponse> diagnoseDetailResponses = diagnoseDetails.stream().map(diagnoseDetail -> {
            return DiagnoseDetailResponse.builder()
                    .id(diagnoseDetail.getId())
                    .doctorId(diagnoseDetail.getDoctor().getId())
                    .illnessId(diagnoseDetail.getIllness().getId())
                    .diagnoseResult(diagnoseDetail.getDiagnoseResult())
                    .isReferenced(diagnoseDetail.getIsReferenced())
                    .build();
        }).toList();

        return DiagnoseResponse.builder()
                .id(diagnose.getId())
                .customerId(diagnose.getCustomer().getId())
                .puskesmasId(diagnose.getPuskesmas().getId())
                .visitDate(diagnose.getVisitDate())
                .diagnoseDetails(diagnoseDetailResponses)
                .build();
    }

    @Override
    public List<DiagnoseResponse> getAllBills() {
        return List.of();
    }
}
