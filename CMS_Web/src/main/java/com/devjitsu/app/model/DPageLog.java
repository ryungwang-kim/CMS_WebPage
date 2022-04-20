package com.devjitsu.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.devjitsu.app.app.log.dto.PageLogDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "D_WEB_PAGE_LOG", schema = "WEB")
public class DPageLog {

	@Builder
	public DPageLog(PageLogDTO dto) {
		this.ip = dto.getIp();
		this.memNo = dto.getMemNo();
		this.memId = dto.getMemId();
		this.requestType = dto.getRequestType();
		this.requestUrl = dto.getRequestUrl();
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_NO")
    private Long logNo;

    @Column(name = "MEM_NO")
    private Long memNo;
    
    @Column(name = "MEM_ID", length = 50)
    private String memId;
    
    @Column(name = "IP", length = 50, updatable = false, nullable = false)
    private String ip;

    @Column(name = "REQUEST_URL", columnDefinition = "TEXT", updatable = false, nullable = false)
    private String requestUrl;

    @Column(name = "REQUEST_TYPE", updatable = false, nullable = false)
    private String requestType;

    @Column(name = "LOG_TIME", updatable = false, nullable = false)
    @CreationTimestamp
    private Date logTime;
    
}
