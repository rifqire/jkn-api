package com.ec.jkn.mock.controller;

import com.ec.jkn.mock.constant.APIUrl;
import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.entity.Puskesmas;
import com.ec.jkn.mock.service.PuskesmasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.PUSKESMAS_API)
public class PuskesmasController {
    private final PuskesmasService puskesmasService;

    @PostMapping
    public Puskesmas addPuskesmas(@RequestBody Puskesmas puskesmas) {
        return puskesmasService.create(puskesmas);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public Puskesmas getPuskesmasById(@PathVariable String id) {
        return puskesmasService.getById(id);
    }

    @PutMapping
    public Puskesmas updatePuskesmas(@RequestBody Puskesmas puskesmas) {
        return puskesmasService.update(puskesmas);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public String deleteById(@PathVariable String id) {
        puskesmasService.deleteById(id);
        return ResponseMessage.SUCCESS_DELETE_DATA;
    }
}
