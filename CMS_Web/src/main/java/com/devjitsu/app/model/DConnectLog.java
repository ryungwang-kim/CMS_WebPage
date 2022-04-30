package com.devjitsu.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.devjitsu.app.app.log.dto.ConnectLogDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "D_WEB_CONNECT_LOG", schema = "WEB")
public class DConnectLog {

	@Builder
    public DConnectLog(ConnectLogDTO dto){
        this.memNo = dto.getMemNo();
        this.memId = dto.getMemId();
        this.ip = dto.getIp();
        this.logType = dto.getLogType();
    }
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONNECT_LOG_NO")
    private Long logNo;
	
	@Column(name = "MEM_NO", updatable = false, nullable = false)
    private Long memNo;

    @Column(name = "MEM_ID", length = 50, updatable = false, nullable = false)
    private String memId;
    
    @Column(name = "IP", length = 50, updatable = false, nullable = false)
    private String ip;

    @Column(name = "LOG_TIME", updatable = false, nullable = false)
    @CreationTimestamp
    private Date logTime;
    
    @Column(name = "LOG_TYPE", length = 50, updatable = false, nullable = false)
    private String logType;
}
