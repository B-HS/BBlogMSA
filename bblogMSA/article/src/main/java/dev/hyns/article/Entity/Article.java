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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column
    private String title;

    @Column
    private String context;

    @Column
    private Boolean isOpen;

    @Builder.Default
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ArticleHashtag> articleHashtag = new LinkedHashSet<>();
}
