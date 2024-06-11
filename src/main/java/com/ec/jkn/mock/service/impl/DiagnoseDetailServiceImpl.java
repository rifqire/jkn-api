package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.entity.DiagnoseDetail;
import com.ec.jkn.mock.repository.DiagnoseDetailRepository;
import com.ec.jkn.mock.service.DiagnoseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnoseDetailServiceImpl implements DiagnoseDetailService {
    private final DiagnoseDetailRepository diagnoseDetailRepository;

    @Override
    public List<DiagnoseDetail> createBulk(List<DiagnoseDetail> diagnoseDetails) {
        return diagnoseDetailRepository.saveAllAndFlush(diagnoseDetails);
    }
}
