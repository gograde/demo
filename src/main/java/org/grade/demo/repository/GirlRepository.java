package org.grade.demo.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.grade.core.persistence.JpaTopadRepository;
import org.grade.demo.entity.Girl;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository("girlRepository")
public interface GirlRepository extends JpaTopadRepository<Girl, Long>
{

    @EntityGraph(value = "graph.girl.row")
    public Page<Girl> findAll(Specification<Girl> specifications, Pageable pageRequest);

    @EntityGraph(value = "graph.girl.all")
    public Optional<Girl> findById(Long id);
}
