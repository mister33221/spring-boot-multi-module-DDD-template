package com.systex.ddd.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entity<ID> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ID id;
}
