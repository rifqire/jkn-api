package com.ec.jkn.mock.repository;

import com.ec.jkn.mock.entity.Puskesmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuskesmasRepository extends JpaRepository<Puskesmas, String> {
}
