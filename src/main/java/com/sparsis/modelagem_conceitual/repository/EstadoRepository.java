package com.sparsis.modelagem_conceitual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparsis.modelagem_conceitual.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
