package com.kbhkn.jpademo.repository;

import com.kbhkn.jpademo.domain.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
public interface ActionRepository extends RevisionRepository<ActionEntity, Long, Long>, JpaRepository<ActionEntity, Long> {
}
