package dev.hyns.article.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hyns.article.Entity.ArticleHashtag;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {
    
}
