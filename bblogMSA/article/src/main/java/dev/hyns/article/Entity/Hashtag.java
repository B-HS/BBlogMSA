package dev.hyns.article.Entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hashtag extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;

    @Column(unique = true)
    private String tag;

    @Builder.Default
    @OneToMany(mappedBy = "hashtag", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ArticleHashtag> hashtagArticle = new LinkedHashSet<>();
}
