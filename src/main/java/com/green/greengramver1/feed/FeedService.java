package com.green.greengramver1.feed;

import com.green.greengramver1.common.MyFileUtils;
import com.green.greengramver1.feed.model.FeedPicDto;
import com.green.greengramver1.feed.model.FeedPostReq;
import com.green.greengramver1.feed.model.FeedPostRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final MyFileUtils myFileUtils;


    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = mapper.insFeed(p);
    FeedPostRes feedPostRes = new FeedPostRes();

        //파일 저장
        //위치:feed/${feedId}/


        long feedId=p.getFeedId();
        String middlePath = String.format("feed/%d", feedId);

        //폴더 만들기
        myFileUtils.makeFolders(middlePath);


        //여러개의 사진파일 저장 하기
        FeedPicDto feedPicDto = new FeedPicDto();
        //feedPicDto 에 feedId 값(위에서 int result = mapper.insFeed(p); 을 통해 pk인 feedID를 얻었고, 그 값을 FeedPicDto 멤버필드에 값을 넣어줌)
        feedPicDto.setFeedId(feedId);
        feedPostRes.setFeedId(feedId);
        feedPostRes.setPics(new ArrayList<>());

        for (MultipartFile pic : pics) {
            String savedPicName =  myFileUtils.makeRandomFileName(pic);

            String filePath = String.format("%s/%s", middlePath, savedPicName); // 전체 경로 선언 및 초기화

            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }


            feedPostRes.getPics().add(savedPicName); // 멤버필드 List<String> 에  값 주입


            feedPicDto.setPic(savedPicName);
            mapper.insFeedPic(feedPicDto); // 데이테베이스에 값만 넣기 위한 용도


        }


        return feedPostRes;
    }
}
