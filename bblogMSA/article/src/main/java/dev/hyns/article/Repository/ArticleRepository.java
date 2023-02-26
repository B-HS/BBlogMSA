package dev.hyns.article.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hyns.article.Entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
