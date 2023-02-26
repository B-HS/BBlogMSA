package dev.hyns.article.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.hyns.article.DTO.ArticleDTO;
import dev.hyns.article.Entity.Article;

public interface ArticleService {
    Long articleCreate(ArticleDTO dto);

    ArticleDTO articleRead(Long aid);

    Long articleUpdate(ArticleDTO dto);

    Boolean articleDelete(Long aid);

    List<ArticleDTO> articleList(Pageable pageable);

    default Article toEntity(ArticleDTO dto) {
        return Article.builder()
                .aid(dto.getAid())
                .title(dto.getTitle())
                .context(dto.getContext())
                .isOpen(dto.getIsOpen())
                .build();
    }

    default ArticleDTO toDTO(Article entity) {
        return ArticleDTO.builder()
                .aid(entity.getAid())
                .title(entity.getTitle())
                .context(entity.getContext())
                .isOpen(entity.getIsOpen())
                .hashtagList(entity.getArticleHashtag().stream().map(v -> v.getHashtag().getTag()).toList())
                .build();
    }
}
