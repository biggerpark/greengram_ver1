package com.green.greengramver1.user;

import com.green.greengramver1.common.MyFileUtils;
import com.green.greengramver1.user.model.UserSignInReq;
import com.green.greengramver1.user.model.UserSignUpReq;
import com.green.greengramver1.user.model.UserSingInRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;  //MyFileUtils 객체가 빈등록 되어있으므로, 불러올 수 있다.


    public int postSignUp(UserSignUpReq p, MultipartFile pic) {

        //프로필 이미지 파일 처리
//        String savedPicName=myFileUtils.makeRandomFileName(pic.getOriginalFilename());
        String savedPicName = pic != null ? myFileUtils.makeRandomFileName(pic) : null; //위에 처럼 실행되기 위해 메소드를 만들어보자, savedPicName 변수에는 프론트에서 보낸 pic jpg 파일의 랜덤한 이름과 확장자인 jpg가 붙어서 변수에 저장된다.

        String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt()); // 암호화된 비밀번호 만들기
        log.info("hashedPassword:{}", hashedPassword); //확인용
        p.setUpw(hashedPassword);
        p.setPic(savedPicName); // 랜덤한 이름.jpg 으로 저장된 변수 savedPicName 을 UserInsReq 객체의 pic 멤버필드에 set 해줌.


        int result = mapper.insUser(p);


        if (pic == null) {
            return result;
        }


        //저장 위치 만든다. user/${userId}/${savedPicName}
        // middle path=user/${userId}

        long userId = p.getUserId(); //userId를 insert 후에 얻을 수 있다. 그래서 mapper 객체를 호출하고나서, 이를 써준다.
        String middlePath = String.format("user/%d", userId);
        myFileUtils.makeFolders(middlePath);
        log.info("middlePath:{}", middlePath); //확인용


        String filePath = String.format("%s/%s", middlePath, savedPicName);


        try {
            myFileUtils.transferTo(pic, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    //로그인 하는 과정을 service 에서 만들어보자.
    public UserSingInRes postSignIn(UserSignInReq p) {


        UserSingInRes res=mapper.selUserForSignIn(p);

        //res==null 이면
        if(res==null) {
            res=new UserSingInRes();
            res.setMessage("아이디를 확인해주세요");
            return res;
        } else if(!BCrypt.checkpw(p.getUpw(), res.getUpw())) {
            res=new UserSingInRes();
            res.setMessage("비밀번호를 확인해주세요");
            return res;
        }
        // 비밀번호가 다를때를 체크하자. 프론트에서 받은 비밀번호와 데이터베이스에 저장된 암호화된 비밀번호 비교

        // 위에 분기문을 다 지나쳤으면 로그인 성공한것.
        res.setMessage("로그인 성공");
        return res;
    }

}
