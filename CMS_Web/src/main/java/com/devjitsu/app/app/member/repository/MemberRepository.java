package com.devjitsu.app.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devjitsu.app.model.DMemInfo;

@Repository
public interface MemberRepository extends JpaRepository<DMemInfo, Long> {

    boolean existsByMemId(String memId);

    DMemInfo findByMemId(String memId);
}
