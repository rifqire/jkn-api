package com.ec.jkn.mock.service;

import com.ec.jkn.mock.entity.Puskesmas;

public interface PuskesmasService {
    Puskesmas create(Puskesmas puskesmas);

    Puskesmas getById(String id);

    Puskesmas update(Puskesmas puskesmas);

    void deleteById(String id);
}
