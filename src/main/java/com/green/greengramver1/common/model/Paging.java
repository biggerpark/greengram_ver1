package com.green.greengramver1.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//페이징 처리를 따로 객체를 만들어서 진행할것이다.
public class Paging {
    @Schema(example = "1",description = "Selected Page")
    private int page;
    @Schema(example = "30",description = "item count per page")
    private int size;
    @JsonIgnore
    private int startIdx;
}
