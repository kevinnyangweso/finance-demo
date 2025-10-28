package com.kevin.financedemo.repository;

import com.kevin.financedemo.model.AuditLog;
import com.kevin.financedemo.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {

    // Find logs related to a particular user
    List<AuditLog> findByUserId(UUID userId);



    // Find logs by action e.g. find only delete logs
    List<AuditLog>findByAction(String action);

    // Find logs by entity name (which table was affected) e.g. users, transactions
    List<AuditLog>findByEntityName(String entityName);

    // Find logs created within a given timespan
    List<AuditLog>findByCreatedAtBetween(OffsetDateTime start, OffsetDateTime end);

    // Find logs filtered by userId and action
    List<AuditLog>findByUserIdAndAction(UUID userId, String action);

    // Find logs grouped by entityName and entityId
    List<AuditLog>findByEntityNameAndEntityId(String entityName, UUID entityId);
}
