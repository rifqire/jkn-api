package com.ec.jkn.mock.service;

import com.ec.jkn.mock.dto.request.NewDoctorRequest;
import com.ec.jkn.mock.dto.request.SearchDoctorRequest;
import com.ec.jkn.mock.entity.Doctor;
import org.springframework.data.domain.Page;

public interface DoctorService {
    Doctor create(NewDoctorRequest request);

    Doctor getById(String id);

    Doctor update(Doctor doctor);

    void deleteById(String id);

    Page<Doctor> getAll(SearchDoctorRequest request);
}
