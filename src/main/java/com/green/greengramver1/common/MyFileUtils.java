package com.green.greengramver1.common;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component // 빈등록 에노테이션, controller 도 component 를 상속받는다.
//빈등록은 빈등록을 하게 되면 기본적으로 같은 주소값을 준다.(shallow copy)

public class MyFileUtils {
    private final String uploadPath;

    //uploadPath 에 무엇을 넣을지 yaml 에 입력해주자.
    //@Value("${file.directory}")를 사용하면, yaml 파일에 있는
    //file.directory 속성에 저장된 값을  생성자 호출할 때 값을 넣어주면서, MyFileUtils 가 빈등록이 된다.
    public MyFileUtils(@Value("${file.directory}") String uploadPath) { // 생성자 자동생성 에노테이션 안적어준 이유는 뭔가를 더 적어야해서이다.
        log.info("MyFileUtils- 생성자 : {}", uploadPath); // 실제로 빈등록이 되어있으면, 생성자가 자동 호출되므로, uploadPath 가  자동으로 호출되는지 확인하는 용
        this.uploadPath = uploadPath;
    }


    // ex) path="ddd/aaa"
    //File 생성자에 (uploadPath,path) 이렇게 넣어주면
    // uploadPath+path 경로가 합쳐져, D:/pjh/GreenGramVer1/greengram_ver1/ddd/aaa 를 가진 객체를 만들어준다.
    public String makeFolders(String path){
        File file = new File(uploadPath,path);
        file.mkdirs(); // 파일을 만들어라는 코드
        return file.getAbsolutePath(); //전체 경로를 리턴할것이다.
    }

    //파일명에서 확장자 추출
    public String getExt(String fileName){
        int lastIdx=fileName.lastIndexOf(".");
        return fileName.substring(lastIdx);
    }

    //랜덤 파일명 생성
    // 오리지날 파일의 확장자만 추출하고, 파일의 이름만 랜덤한 값으로 바꾼다. 확장자는 안바뀜
    public String makeRandomFileName(){
        return UUID.randomUUID().toString();
    }

    //랜덤파일명+확장자 생성
    //오버로딩 (메소드 파라미터가 달라지면 여러개 만들 수 있는 것)
    public String makeRandomFileName(String originalFileName){
        String ext=makeRandomFileName()+getExt(originalFileName);

        return ext;
    }

    //파일을 원하는 경로에 저장하기
    public void transferTo(MultipartFile mf, String path) throws IOException {
        File file = new File(uploadPath,path);
        mf.transferTo(file); // mf 객체가 file 객체안에 저장된 경로로 저장되라는 메소드
    }

    public String makeRandomFileName(MultipartFile file){
        return makeRandomFileName(file.getOriginalFilename()); // public String makeRandomFileName(String originalFileName) 이 메소드를 이용한 것.
    }

}

class Test{
    public static void main(String[] args) {
        MyFileUtils myFileUtils=new MyFileUtils("C:/temp");
        String randomFileName=myFileUtils.makeRandomFileName("707211-1532672215.png");
        System.out.println(randomFileName);
    }
}
