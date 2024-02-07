package com.systex.ddd.application;

import com.systex.ddd.domain.AggregateRoot;
import com.systex.ddd.domain.DomainEvent;

public interface DomainEventBus {

    void post(DomainEvent domainEvent);

    void postAll(AggregateRoot aggregateRoot);
}
