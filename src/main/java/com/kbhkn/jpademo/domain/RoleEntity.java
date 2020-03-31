package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Getter
@Setter
@Entity
@Audited // It uses _AUDIT_LOG tables via envers.
@EntityListeners(AuditingEntityListener.class)
@AuditOverride(forClass = BaseAuditEntity.class)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true) // Don't use inheritance and only include marked columns.
@Table(
        name = "ROLES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"CODE", "NAME"})
)
public class RoleEntity extends BaseAuditEntity implements GrantedAuthority {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String code;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "ROLE_ACTION_MAP",
            joinColumns =
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =  @JoinColumn(name = "ACTION_ID", referencedColumnName = "ID")
    )
    private Set<ActionEntity> actions = new HashSet<>();

    @Override
    public String getAuthority() {
        return code;
    }
}
