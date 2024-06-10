package com.ec.jkn.mock.service;

import com.ec.jkn.mock.dto.request.NewCustomerRequest;
import com.ec.jkn.mock.dto.request.SearchCustomerRequest;
import com.ec.jkn.mock.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer create(NewCustomerRequest request);

    Customer getById(String id);

    Customer update(Customer customer);

    void deleteById(String id);

    Page<Customer> getAll(SearchCustomerRequest request);
}
