package com.green.greengramver1.user;

import com.green.greengramver1.common.MyFileUtils;
import com.green.greengramver1.user.model.UserInsReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    public int postSignUp( UserInsReq p,MultipartFile pic){

        //프로필 이미지 파일 처리
//        String savedPicName=myFileUtils.makeRandomFileName(pic.getOriginalFilename());
       String savedPicName=pic!=null?myFileUtils.makeRandomFileName(pic):null; //위에 처럼 실행되기 위해 메소드를 만들어보자.

        String hashedPassword= BCrypt.hashpw(p.getUpw(),BCrypt.gensalt()); // 암호화된 비밀번호 만들기
        log.info("hashedPassword:{}",hashedPassword); //확인용
        p.setUpw(hashedPassword);
        p.setPic(savedPicName);

        int result=mapper.insUser(p);



        if(pic==null){
            return result;
        }



        long userId=p.getUserId(); //userId를 insert 후에 얻을 수 있다.
        //  User/${userId}/${savedPicName}
        String middlePath=String.format("user/%d",userId);
        myFileUtils.makeFolders(String.format(middlePath));


        log.info("middlePath:{}",middlePath);
        String filePath=String.format("%s/%s",middlePath,savedPicName);


        try{
            myFileUtils.transferTo(pic,filePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

}
