package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.entity.Illness;
import com.ec.jkn.mock.repository.IllnessRepository;
import com.ec.jkn.mock.service.IllnessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class IllnessServiceImpl implements IllnessService {
    private final IllnessRepository illnessRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Illness create(Illness illness) {
        return illnessRepository.saveAndFlush(illness);
    }

    @Transactional(readOnly = true)
    @Override
    public Illness getById(String id) {
        return findByIdOrThrow(id);
    }

    private Illness findByIdOrThrow(String id) {
        return illnessRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Illness update(Illness illness) {
        findByIdOrThrow(illness.getId());
        return illnessRepository.saveAndFlush(illness);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Illness illness = findByIdOrThrow(id);
        illnessRepository.delete(illness);
    }
}
