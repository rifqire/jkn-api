package com.ec.jkn.mock.service;

import com.ec.jkn.mock.dto.request.NewDoctorRequest;
import com.ec.jkn.mock.dto.request.SearchDoctorRequest;
import com.ec.jkn.mock.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor create(NewDoctorRequest request);

    Doctor getById(String id);

    Doctor update(Doctor doctor);

    void deleteById(String id);

    List<Doctor> getAll(SearchDoctorRequest request);
}
