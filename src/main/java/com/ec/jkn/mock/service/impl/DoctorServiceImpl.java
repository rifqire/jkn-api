package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.dto.request.NewDoctorRequest;
import com.ec.jkn.mock.dto.request.SearchDoctorRequest;
import com.ec.jkn.mock.entity.Doctor;
import com.ec.jkn.mock.repository.DoctorRepository;
import com.ec.jkn.mock.service.DoctorService;
import com.ec.jkn.mock.specification.DoctorSpecification;
import com.ec.jkn.mock.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Doctor create(NewDoctorRequest request) {
        validationUtil.validate(request);
        Doctor newDoctor = Doctor.builder()
                .name(request.getName())
                .strNumber(request.getStrNumber())
                .specialization(request.getSpecialization())
                .experienceYears(request.getExperienceYears())
                .build();
        return doctorRepository.saveAndFlush(newDoctor);
    }

    @Transactional(readOnly = true)
    @Override
    public Doctor getById(String id) {
        return findByIdOrThrow(id);
    }

    private Doctor findByIdOrThrow(String id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Doctor update(Doctor doctor) {
        findByIdOrThrow(doctor.getId());
        return doctorRepository.saveAndFlush(doctor);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Doctor doctor = findByIdOrThrow(id);
        doctorRepository.delete(doctor);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Doctor> getAll(SearchDoctorRequest request) {
        Specification<Doctor> doctorSpecification = DoctorSpecification.getSpecification(request);
        if (request.getName() == null && request.getStrNumber() == null && request.getSpecialization() == null && request.getExperienceYears() == null) {
            return doctorRepository.findAll();
        }
        return doctorRepository.findAll(doctorSpecification);
    }
}
