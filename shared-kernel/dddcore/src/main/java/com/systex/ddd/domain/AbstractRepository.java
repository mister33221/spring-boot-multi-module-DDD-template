package com.systex.ddd.domain;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T extends AggregateRoot<ID>, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    void save(T data);

    void deleteById(ID id);
}
