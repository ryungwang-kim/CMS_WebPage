package com.devjitsu.app.app.member.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemInfoDTO {

    private Long memNo;

    private String memId;

    private String memNm;

    private int authNo;

    private String memPw;

    private String ssn;

    private String birthDt;

    private String maleFlg;

    private String tellNo;

    private String cellNo;

    private String zipCode;
    
    private String addr;
    
    private String addrDetail;

    private String email;

    private String emailRcvFlg;

    private String memStatus;
    
    private int cpNo;
}
