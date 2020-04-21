package com.broker.broker.repository;

import com.broker.broker.domain.UserBroker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBroker, Long> {
    UserBroker findByUsername(String  username);
}
