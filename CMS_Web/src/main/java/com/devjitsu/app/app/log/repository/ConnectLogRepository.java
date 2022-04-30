package com.devjitsu.app.app.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devjitsu.app.model.DConnectLog;

@Repository
public interface ConnectLogRepository extends JpaRepository<DConnectLog, Long> {
}
