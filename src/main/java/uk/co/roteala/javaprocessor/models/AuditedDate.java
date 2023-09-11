package uk.co.roteala.javaprocessor.models;

import java.time.Instant;

public interface AuditedDate {
    Instant getDateAdded();

    Instant getDateUpdated();
}
