package com.green.greengramver1.user;

import com.green.greengramver1.common.model.ResultResponse;
import com.green.greengramver1.user.model.UserInsReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Tag(name="유저",description = "회원가입,로그인")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입")
    public ResultResponse<Integer> insUser(@RequestBody UserInsReq p){
        int result=0;
        return ResultResponse.<Integer>builder().resultMessage("회원가입완료").resultData(result).build();


    }
}
