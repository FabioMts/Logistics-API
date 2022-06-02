package com.fabio.logistics.logisticsapi.repository;

import com.fabio.logistics.logisticsapi.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
