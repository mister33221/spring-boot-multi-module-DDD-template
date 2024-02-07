package com.systex.ddd.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class DomainEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UUID id;
    private final Date occurredOn;

    public DomainEvent() {
        this.id = null;
        this.occurredOn = null;
    }

    public DomainEvent(Date occurredOn) {
        id = UUID.randomUUID();
        this.occurredOn = occurredOn;
    }

    public String detail() {
        return String.format("%s[id='%s'][occurredOn='%s'] ", this.getClass()
                                                                  .getSimpleName(), this.id,
            String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM", getOccurredOn())
        );
    }
}
