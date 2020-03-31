package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * Also if needs it can use process-action structure.
 *
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Getter
@Setter
@Entity
@Audited // It uses _AUDIT_LOG tables via envers.
@EntityListeners(AuditingEntityListener.class)
@AuditOverride(forClass = BaseAuditEntity.class) // Do not use default conf.
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true) // Don't use inheritance and only include marked columns.
@Table(
        name = "ACTIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"URL"})
)
public class ActionEntity extends BaseAuditEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String url;
}
