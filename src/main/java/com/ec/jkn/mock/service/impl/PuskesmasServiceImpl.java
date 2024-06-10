package com.ec.jkn.mock.service.impl;

import com.ec.jkn.mock.constant.ResponseMessage;
import com.ec.jkn.mock.entity.Puskesmas;
import com.ec.jkn.mock.repository.PuskesmasRepository;
import com.ec.jkn.mock.service.PuskesmasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PuskesmasServiceImpl implements PuskesmasService {
    private final PuskesmasRepository puskesmasRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Puskesmas create(Puskesmas puskesmas) {
        return puskesmasRepository.saveAndFlush(puskesmas);
    }

    @Transactional(readOnly = true)
    @Override
    public Puskesmas getById(String id) {
        return findByIdOrThrow(id);
    }

    private Puskesmas findByIdOrThrow(String id) {
        return puskesmasRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Puskesmas update(Puskesmas puskesmas) {
        findByIdOrThrow(puskesmas.getId());
        return puskesmasRepository.saveAndFlush(puskesmas);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Puskesmas puskesmas = findByIdOrThrow(id);
        puskesmasRepository.delete(puskesmas);
    }
}
