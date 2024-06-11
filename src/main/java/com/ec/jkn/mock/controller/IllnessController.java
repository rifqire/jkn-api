package com.ec.jkn.mock.controller;

import com.ec.jkn.mock.constant.APIUrl;
import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.entity.Illness;
import com.ec.jkn.mock.service.IllnessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.ILLNESS_API)
public class IllnessController {
    private final IllnessService illnessService;

    @PostMapping
    public Illness addIllness(@RequestBody Illness illness) {
        return illnessService.create(illness);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public Illness getIllnessById(@PathVariable String id) {
        return illnessService.getById(id);
    }

    @PutMapping
    public Illness updateIllness(@RequestBody Illness illness) {
        return illnessService.update(illness);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public String deleteById(@PathVariable String id) {
        illnessService.deleteById(id);
        return ResponseMessage.SUCCESS_DELETE_DATA;
    }
}
