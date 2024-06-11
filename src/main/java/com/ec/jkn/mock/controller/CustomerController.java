package com.ec.jkn.mock.controller;

import com.ec.jkn.mock.constant.APIUrl;
import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.dto.request.NewCustomerRequest;
import com.ec.jkn.mock.dto.request.SearchCustomerRequest;
import com.ec.jkn.mock.dto.response.CommonResponse;
import com.ec.jkn.mock.entity.Customer;
import com.ec.jkn.mock.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> addCustomer(@RequestBody NewCustomerRequest request) {
        Customer newCustomer = customerService.create(request);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newCustomer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_SELECTED_DATA)
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(updatedCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "birthDate", required = false) String birthDate,
            @RequestParam(name = "bpjsNumber", required = false) String bpjsNumber,
            @RequestParam(name = "faskes", required = false) String faskes,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "isActive", required = false) Boolean isActive
    ) {
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .birthDate(birthDate)
                .bpjsNumber(bpjsNumber)
                .faskes(faskes)
                .phoneNumber(phoneNumber)
                .isActive(isActive)
                .build();
        Page<Customer> allCustomers = customerService.getAll(request);

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_ALL_DATA)
                .data(allCustomers.getContent())
                .build();
        return ResponseEntity.ok(response);
    }
}
