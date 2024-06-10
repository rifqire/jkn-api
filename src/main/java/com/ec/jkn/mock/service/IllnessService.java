package com.ec.jkn.mock.service;

import com.ec.jkn.mock.entity.Illness;

public interface IllnessService {
    Illness create(Illness illness);

    Illness getById(String id);

    Illness update(Illness illness);

    void deleteById(String id);
}
