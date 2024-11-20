package com.green.greengramver1.user;

import com.green.greengramver1.common.model.ResultResponse;
import com.green.greengramver1.user.model.UserInsReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
@Tag(name="유저",description = "회원가입,로그인")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입")

    /*

    파일도 넘어오고 data도 넘어오니깐, RequestPart 를 써야함.

    파일 업로드시에는 RequestBody 를 사용할 수 없음
    RequestPart 에노테이션을 사용해야 한다.

     */

    // MultipartFile pic 으로 사진 파일을 받을 것이다.
    public ResultResponse<Integer> insUser(@RequestPart UserInsReq p,@RequestPart(required = false) MultipartFile pic){

        log.info("UserInsReq: {}, file: {}",p,pic!=null ? pic.getOriginalFilename():null);
        int result= service.postSignUp(p,pic);
        return ResultResponse.<Integer>builder().resultMessage("회원가입완료").
                resultData(result)
                .build();
    }

}
