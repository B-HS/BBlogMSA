package dev.hyns.article.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public class DateEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

}