package com.ec.jkn.mock.repository;

import com.ec.jkn.mock.entity.Illness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, String> {
}
