package com.kbhkn.jpademo.domain.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Hakan KABASAKAL, 30-Mar-20
 */
@Getter
@Setter
@MappedSuperclass
public class BaseAuditEntity {
    @Column(nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
