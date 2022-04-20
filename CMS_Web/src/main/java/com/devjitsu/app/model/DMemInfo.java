package com.devjitsu.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devjitsu.app.app.member.dto.MemInfoDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "D_MEM_INFO")
@ToString
public class DMemInfo {

    @Builder
    public DMemInfo(MemInfoDTO dto){
        this.memNo = dto.getMemNo();
        this.memId = dto.getMemId();
        this.memNm = dto.getMemNm();
        this.cpNo = dto.getCpNo();
        this.authNo = dto.getAuthNo();
        this.memPw = dto.getMemPw();
        this.ssn = dto.getSsn();
        this.maleFlg = dto.getMaleFlg();
        this.tellNo = dto.getTellNo();
        this.cellNo = dto.getCellNo();
        this.zipCode = dto.getZipCode();
        this.addr = dto.getAddr();
        this.addrDetail = dto.getAddrDetail();
        this.email = dto.getEmail();
        this.emailRcvFlg = dto.getEmailRcvFlg();
        this.memStatus = dto.getMemStatus();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_NO")
    private Long memNo;

    @Column(name = "CP_NO")
    private Integer cpNo;
    
    @Column(name = "MEM_ID", length = 20)
    private String memId;

    @Column(name = "MEM_NM", length = 50)
    private String memNm;

    @Column(name = "AUTH_NO")
    private int authNo;

    @Column(name = "MEM_PW", length = 150)
    private String memPw;

    @Column(name = "SSN", length = 13)
    private String ssn;

    @Column(name = "MALE_FLG", length = 1)
    private String maleFlg;

    @Column(name = "TELL_NO", length = 14)
    private String tellNo;

    @Column(name = "CELL_NO", length = 14)
    private String cellNo;

    @Column(name = "ZIP_CODE", length = 100)
    private String zipCode;
    
    @Column(name = "ADDR", length = 100)
    private String addr;
    
    @Column(name = "ADDR_DETAIL", length = 100)
    private String addrDetail;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "EMAIL_RCV_FLG", length = 1)
    private String emailRcvFlg;

    @Column(name = "REG_DT", updatable = false)
    @CreationTimestamp
    private Date regDt;

    @Column(name = "MEM_STATUS", length = 1)
    private String memStatus;

    @Column(name = "UPD_DT", insertable = false)
    @UpdateTimestamp
    private Date updDt;
    
}
