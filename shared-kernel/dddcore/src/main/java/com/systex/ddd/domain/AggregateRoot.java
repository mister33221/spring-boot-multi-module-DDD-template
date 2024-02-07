package com.systex.ddd.domain;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AggregateRoot<ID> extends Entity<ID> {

    private final List<DomainEvent> domainEvents = new CopyOnWriteArrayList<>();
    protected boolean isDeleted;

    public AggregateRoot(ID id) {
        super(id);
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
