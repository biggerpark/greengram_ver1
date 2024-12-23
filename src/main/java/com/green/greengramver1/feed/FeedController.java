package com.green.greengramver1.feed;

import com.green.greengramver1.common.model.ResultResponse;
import com.green.greengramver1.feed.model.FeedPostReq;
import com.green.greengramver1.feed.model.FeedPostRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("feed")
@Tag(name="2",description = "피드관리")
public class FeedController {
    private final FeedService service;


    @PostMapping
    public ResultResponse<FeedPostRes> postFeed(@RequestPart List<MultipartFile> pics,
                                                @RequestPart FeedPostReq p){

        FeedPostRes res = service.postFeed(pics,p);

        return ResultResponse.<FeedPostRes>builder().resultMessage("피드등록완료").resultData(res).build();


    }

}
