package com.broker.broker.repository;

import com.broker.broker.domain.Provajder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvajderRepository extends JpaRepository<Provajder,String> {

    Provajder findByUsername(String username);

}
