package com.green.greengramver1.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "피드등록")
public class FeedPostReq {
    @JsonIgnore
    private long feedId; // 이 값은 insert 된 후에 받아서 사용할 것이다. 프론트에서 받는 값은 아니다.
    @Schema(title="로그인 유저 PK")
    private long writerUserId;
    @Schema(title = "내용")
    private String contents;
    @Schema(title = "위치")
    private String location;

}
