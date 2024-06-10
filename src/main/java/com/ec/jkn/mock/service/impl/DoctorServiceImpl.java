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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<Doctor> getAll(SearchDoctorRequest request) {
        if (request.getPage() <= 0) {
            request.setPage(1);
        }

        String validSortBy;
        if ("name".equalsIgnoreCase(request.getSortBy()) ||
                "strNumber".equalsIgnoreCase(request.getSortBy()) ||
                "specialization".equalsIgnoreCase(request.getSortBy()) ||
                "experienceYears".equalsIgnoreCase(request.getSortBy())) {
            validSortBy = request.getSortBy();
        } else {
            validSortBy = "name";
        }

        // Sorting dengan param yg ditentukan di atas
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);

        // Rumus pagination, kalau di frontend mulai dari 1 di backend mulai dari 0
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);

        // Jika semua request param null maka find all, kalau ada param nya pakai specification
        Specification<Doctor> doctorSpecification = DoctorSpecification.getSpecification(request);
        if (request.getName() == null && request.getStrNumber() == null && request.getSpecialization() == null && request.getExperienceYears() == null) {
            return doctorRepository.findAll(pageable);
        }
        return doctorRepository.findAll(doctorSpecification, pageable);
    }
}
