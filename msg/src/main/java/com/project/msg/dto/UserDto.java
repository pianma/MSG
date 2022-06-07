package com.project.msg.dto;

import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

@Data
@ApiIgnore
public class UserDto {  //예시 : com_user 테이블 Dto

    private Integer userNo;

    private String userId;

    private String userNm;

    private String userPw;

    private String userRole;

    private String photo;

    private Integer deptNo;

    private String deleteFlag;




}


