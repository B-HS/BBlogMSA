package dev.hyns.article.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hyns.article.Entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
    Optional<Hashtag> findByTag(String tag);
}
