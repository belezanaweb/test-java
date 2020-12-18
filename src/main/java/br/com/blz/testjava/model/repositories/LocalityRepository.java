package br.com.blz.testjava.model.repositories;

import br.com.blz.testjava.model.entities.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocalityRepository extends JpaRepository<Locality, Long> {

    Optional<Locality> findByName(@Param("name") String name);
}
