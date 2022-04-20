package com.devjitsu.app.app.log.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devjitsu.app.model.DPageLog;

@Configuration
@Repository
public interface PageLogRepository extends JpaRepository<DPageLog, Long> {
}
