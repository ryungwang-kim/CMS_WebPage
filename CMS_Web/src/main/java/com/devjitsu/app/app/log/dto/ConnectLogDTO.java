package com.devjitsu.app.app.log.dto;

import lombok.Data;

@Data
public class ConnectLogDTO {

    private Long logNo;

    private Long memNo;

    private String memId;

    private String ip;

    private String logType;

}
