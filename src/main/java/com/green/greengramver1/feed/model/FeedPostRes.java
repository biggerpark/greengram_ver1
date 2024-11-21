package com.green.greengramver1.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedPostRes {
    //feed pk 값과 파일 이름 여러개 리턴할 수 있어야함.
    private long feedId;
    private List<String> pics;

}
