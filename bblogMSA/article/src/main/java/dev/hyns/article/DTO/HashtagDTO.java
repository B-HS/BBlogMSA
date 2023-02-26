package dev.hyns.article.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HashtagDTO {
    private Long hid;
    private String tag;
}
