package com.devjitsu.app.app.log.dto;

import lombok.Data;

@Data
public class PageLogDTO {

    private Long logNo;

    private Long memNo;
    
    private String memId;
    
    private String ip;

    private String requestUrl;

    private String requestType;
    
    private String logTime;
}
