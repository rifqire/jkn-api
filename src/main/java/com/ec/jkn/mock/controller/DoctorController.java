package com.ec.jkn.mock.controller;

import com.ec.jkn.mock.constant.APIUrl;
import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.dto.request.NewDoctorRequest;
import com.ec.jkn.mock.dto.request.SearchDoctorRequest;
import com.ec.jkn.mock.dto.response.CommonResponse;
import com.ec.jkn.mock.entity.Doctor;
import com.ec.jkn.mock.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.DOCTOR_API)
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<CommonResponse<Doctor>> addDoctor(@RequestBody NewDoctorRequest request) {
        Doctor newDoctor = doctorService.create(request);
        CommonResponse<Doctor> response = CommonResponse.<Doctor>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newDoctor)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Doctor>> getDoctorById(@PathVariable String id) {
        Doctor doctor = doctorService.getById(id);
        CommonResponse<Doctor> response = CommonResponse.<Doctor>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_SELECTED_DATA)
                .data(doctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Doctor>> updateDoctor(@RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.update(doctor);
        CommonResponse<Doctor> response = CommonResponse.<Doctor>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(updatedDoctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteById(@PathVariable String id) {
        doctorService.deleteById(id);
        CommonResponse<Doctor> response = CommonResponse.<Doctor>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Doctor>>> getAllDoctor(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "strNumber", required = false) String strNumber,
            @RequestParam(name = "specialization", required = false) String specialization,
            @RequestParam(name = "experienceYears", required = false) Integer experienceYears
    ) {
        SearchDoctorRequest request = SearchDoctorRequest.builder()
                .name(name)
                .strNumber(strNumber)
                .specialization(specialization)
                .experienceYears(experienceYears)
                .build();
        List<Doctor> allDoctors = doctorService.getAll(request);

        CommonResponse<List<Doctor>> response = CommonResponse.<List<Doctor>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_ALL_DATA)
                .data(allDoctors)
                .build();
        return ResponseEntity.ok(response);
    }
}
