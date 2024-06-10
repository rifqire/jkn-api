package com.ec.jkn.mock.service;

import com.ec.jkn.mock.entity.DiagnoseDetail;

import java.util.List;

public interface DiagnoseDetailService {
    List<DiagnoseDetail> createBulk(List<DiagnoseDetail> diagnoseDetails);
}
