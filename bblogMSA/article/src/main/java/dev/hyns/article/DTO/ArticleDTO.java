package dev.hyns.article.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTO {
    private Long aid;
    private String title;
    private String context;
    private Boolean isOpen;
    private List<String> hashtagList;
}
