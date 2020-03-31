package com.kbhkn.jpademo.configurations.audit;

import com.kbhkn.jpademo.domain.audit.AuditRevisionEntity;
import lombok.AllArgsConstructor;
import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Component
@AllArgsConstructor
public class AuditingRevisionListener implements RevisionListener {
    private final SecurityAuditorAware securityAuditorAware;

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity entity = (AuditRevisionEntity) revisionEntity;

        entity.setUsername(securityAuditorAware.getCurrentAuditor().get());
    }
}