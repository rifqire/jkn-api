package com.ec.jkn.mock.repository;

import com.ec.jkn.mock.entity.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, String> {
}
