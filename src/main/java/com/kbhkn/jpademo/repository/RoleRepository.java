package com.kbhkn.jpademo.repository;

import com.kbhkn.jpademo.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * Use RevisionRepository for auditing.
 *
 * @author Hakan KABASAKAL, 31-Mar-20
 */
public interface RoleRepository extends RevisionRepository<RoleEntity, Long, Long>, JpaRepository<RoleEntity, Long> {
}
