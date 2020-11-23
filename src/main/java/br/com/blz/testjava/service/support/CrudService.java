package br.com.blz.testjava.service.support;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<I, D> {

    D create(D dto);

    D update(I id, D dto);

    Optional<D> findById(I id);

    Page<D> findAll(Pageable pageable);

    Optional<Boolean> deleteById(I id);

}
