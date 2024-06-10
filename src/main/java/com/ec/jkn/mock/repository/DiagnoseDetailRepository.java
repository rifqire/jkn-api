package com.ec.jkn.mock.repository;

import com.ec.jkn.mock.entity.DiagnoseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseDetailRepository extends JpaRepository<DiagnoseDetail, String> {
}
