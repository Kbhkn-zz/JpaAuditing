package com.kbhkn.jpademo.repository;

import com.kbhkn.jpademo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * Use RevisionRepository for auditing.
 *
 * @author Hakan KABASAKAL, 31-Mar-20
 */
public interface UserRepository extends RevisionRepository<UserEntity, Long, Long>, JpaRepository<UserEntity, Long> {
}
