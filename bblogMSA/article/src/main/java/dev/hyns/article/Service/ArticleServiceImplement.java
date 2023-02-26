package dev.hyns.article.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.hyns.article.DTO.ArticleDTO;
import dev.hyns.article.Entity.Article;
import dev.hyns.article.Entity.ArticleHashtag;
import dev.hyns.article.Entity.Hashtag;
import dev.hyns.article.Repository.ArticleHashtagRepository;
import dev.hyns.article.Repository.ArticleRepository;
import dev.hyns.article.Repository.HashtagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImplement implements ArticleService {
    private final ArticleRepository arepo;
    private final ArticleHashtagRepository ahrepo;
    private final HashtagRepository hrepo;

    @Override
    @Transactional
    public Long articleCreate(ArticleDTO dto) {
        Article article = arepo.save(toEntity(dto));
        List<Hashtag> savedList = dto.getHashtagList().stream().map(v -> Hashtag.builder().tag(v).build()).toList()
                .stream().map(v -> {
                    Optional<Hashtag> tag = hrepo.findByTag(v.getTag());
                    if (tag.isPresent()) {
                        return tag.get();
                    }
                    return hrepo.save(v);
                }).toList();
        ahrepo.saveAll(
                savedList.stream().map(v -> ArticleHashtag.builder().article(article).hashtag(v).build()).toList());
        return article.getAid();
    }

    @Override
    @Transactional
    public Boolean articleDelete(Long aid) {
        Article target = arepo.findById(aid).orElseGet(() -> Article.builder().aid(-1L).build());
        if (target.getAid() != -1L) {
            arepo.delete(target);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<ArticleDTO> articleList(Pageable pageable) {
        Page<Article> result = arepo.findAll(pageable);

        return result.stream().map(v -> {
            return toDTO(v);
        }).toList();
    }

    @Override
    @Transactional
    public ArticleDTO articleRead(Long aid) {
        Article result = arepo.findById(aid).orElseGet(() -> Article.builder().aid(-1L).build());
        return toDTO(result);
    }

    @Override
    @Transactional
    public Long articleUpdate(ArticleDTO dto) {
        Article article = arepo.findById(dto.getAid()).orElseThrow();
        ahrepo.deleteAll(article.getArticleHashtag());
        

        return articleCreate(dto);
    }

}
