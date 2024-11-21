package com.green.greengramver1.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
// 프론트에 응답해줄 객체
public class UserSingInRes {
    private long userId;
    private String nickName;
    private String pic;
    @JsonIgnore // upw 는 프론트에 안보내줄것이기 때문에 @JsonIgnore 를 사용
    private String upw;
    @JsonIgnore
    private String message;
}
