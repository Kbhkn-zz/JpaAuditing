package com.kbhkn.jpademo.configurations.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @CreatedBy and @LastModifiedBy fetches active user from security context.
 *
 * @CreatedDate and @LastModifiedDate use current time.
 *
 * More documents: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/index.html#auditing.auditor-aware
 *
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            // TODO: Use Exception Handler design in kbhkn repository.
            // throw new RuntimeException("You're not authorized to access this transaction!");

            // dummy value for h2 tests. Otherwise throw exception.
            // Remove excludes of @SpringBootApplication
            return Optional.of("H2 Database Test");
        }

        return Optional.of(authentication.getName());
    }
}
