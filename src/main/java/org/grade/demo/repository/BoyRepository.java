package org.grade.demo.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.grade.core.persistence.JpaTopadRepository;
import org.grade.demo.entity.Boy;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository("boyRepository")
public interface BoyRepository extends JpaTopadRepository<Boy, Long>
{

    @EntityGraph(value = "graph.boy.row")
    public Page<Boy> findAll(Specification<Boy> specifications, Pageable pageRequest);

    @EntityGraph(value = "graph.boy.all")
    public Optional<Boy> findById(Long id);

}
