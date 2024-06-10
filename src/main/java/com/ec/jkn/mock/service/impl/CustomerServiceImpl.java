package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.dto.request.NewCustomerRequest;
import com.ec.jkn.mock.dto.request.SearchCustomerRequest;
import com.ec.jkn.mock.entity.Customer;
import com.ec.jkn.mock.repository.CustomerRepository;
import com.ec.jkn.mock.service.CustomerService;
import com.ec.jkn.mock.specification.CustomerSpecification;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(NewCustomerRequest request) {
        validationUtil.validate(request);
        Customer newCustomer = Customer.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .bpjsNumber(request.getBpjsNumber())
                .faskes(request.getFaskes())
                .phoneNumber(request.getPhoneNumber())
                .isActive(request.getIsActive())
                .build();
        return customerRepository.saveAndFlush(newCustomer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return findByIdOrThrow(id);
    }

    private Customer findByIdOrThrow(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer update(Customer customer) {
        findByIdOrThrow(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrow(id);
        customerRepository.delete(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> getAll(SearchCustomerRequest request) {
        if (request.getPage() <= 0) {
            request.setPage(1);
        }

        String validSortBy;
        if ("name".equalsIgnoreCase(request.getSortBy()) ||
                "birthDate".equalsIgnoreCase(request.getSortBy()) ||
                "bpjsNumber".equalsIgnoreCase(request.getSortBy()) ||
                "faskes".equalsIgnoreCase(request.getSortBy()) ||
                "phoneNumber".equalsIgnoreCase(request.getSortBy()) ||
                "isActive".equalsIgnoreCase(request.getSortBy())) {
            validSortBy = request.getSortBy();
        } else {
            validSortBy = "name";
        }

        // Sorting dengan param yg ditentukan di atas
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);

        // Rumus pagination, kalau di frontend mulai dari 1 di backend mulai dari 0
        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);

        // Jika semua request param null maka find all, kalau ada param nya pakai specification
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(request);
        if (request.getName() == null && request.getBirthDate() == null && request.getBpjsNumber() == null && request.getFaskes() == null && request.getPhoneNumber() == null && request.getIsActive() == null) {
            return customerRepository.findAll(pageable);
        }
        return customerRepository.findAll(customerSpecification, pageable);
    }
}
