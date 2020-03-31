package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import java.security.Principal;
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
        name = "USERS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"})
)
public class UserEntity extends BaseAuditEntity implements Principal {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    @Column
    private String token;

    @Column(nullable = false)
    private Character isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "USER_ROLE_MAP",
            joinColumns =
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public Boolean isActive() {
        return isActive == 'Y';
    }

    public Set<ActionEntity> getAllActionsOfUser() {
        Set<ActionEntity> actions = new HashSet<>();

        roles.stream()
                .map(RoleEntity::getActions)
                .forEach(actions::addAll);

        return actions;
    }

    @Override
    public String getName() {
        return username;
    }

}
