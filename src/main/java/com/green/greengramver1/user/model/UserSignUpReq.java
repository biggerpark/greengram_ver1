package com.green.greengramver1.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// 회원가입 할때 프론트에서 받는 객체
@Getter
//@AllArgsConstructor // Setter를 빼고 생성자를 통해 멤버필드에 데이터를 넣는 에노테이션
@Setter
@ToString
public class UserSignUpReq {
    @Schema(description = "유저아이디",example = "mic",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(description = "유저 비밀번호",example = "1212",requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    // @Schema(description = "유저 프로필사진")
    @JsonIgnore
    private String pic; // 프론트에서 안받을것이다. 내부적으로 사용함, 파일명으로 저장될것이다
    @Schema(description = "유저 닉네임",example = "홍길동")
    private String nickName;
    @JsonIgnore
    private long userId;
}
