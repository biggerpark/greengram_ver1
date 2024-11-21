package com.green.greengramver1.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "로그인")
// uid,upw를 받아와서 데이터베이스에 들어있는 값이랑 동일한지 비교할 객체
public class UserSignInReq {
    @Schema(title = "아이디",example = "yaho",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(title = "비밀번호",example = "1212",requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;

}
