package com.ec.jkn.mock.service;

import com.ec.jkn.mock.dto.request.NewCustomerRequest;
import com.ec.jkn.mock.dto.request.SearchCustomerRequest;
import com.ec.jkn.mock.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(NewCustomerRequest request);

    Customer getById(String id);

    Customer update(Customer customer);

    void deleteById(String id);

    List<Customer> getAll(SearchCustomerRequest request);
}
