package com.green.greengramver1.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Setter를 빼고 생성자를 통해 멤버필드에 데이터를 넣는 에노테이션
public class UserInsReq {
    private String uid;
    private String upw;
    private String pic;
    private String nickName;
}
