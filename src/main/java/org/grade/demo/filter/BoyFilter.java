package org.grade.demo.filter;

import java.util.Date;
import java.util.List;

import org.grade.core.persistence.filter.AbstractPageableFilter;
import org.grade.demo.entity.Boy;
import org.grade.demo.entity.Boy_;
import org.grade.demo.entity.Girl;
import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoyFilter extends AbstractPageableFilter<Boy>
{

    private String nom;

    private String description;

    private List<Long> structures;

    private Date createdAtFrom;

    private Date createdAtTo;

    private Date updatedAtFrom;

    private Date updatedAtTo;

    @Override
    public void reset()
    {
        this.nom = null;
        this.description = null;
        this.structures = null;
        this.createdAtFrom = null;
        this.createdAtTo = null;
        this.updatedAtFrom = null;
        this.updatedAtTo = null;
    }

    @Override
    public Specification<Boy> getSpecifications()
    {
        Specification<Boy> specByNom = null;
        Specification<Boy> specByDescription = null;
        Specification<Boy> specByStructure = null;
        Specification<Boy> specByCreatedAt = null;
        Specification<Boy> specByUpdatedAt = null;

        if (nom != null && !nom.isEmpty())
        {
            specByNom = getSpecificationByNom();
        }

        if (description != null && !description.isEmpty())
        {
            specByDescription = getSpecificationByDescription();
        }

        if (structures != null && !structures.isEmpty())
        {
            specByStructure = getSpecificationByStructure();
        }

        if (createdAtFrom != null || createdAtTo != null)
        {
            specByCreatedAt = getSpecificationByCreatedAt();
        }

        if (updatedAtFrom != null || updatedAtTo != null)
        {
            specByUpdatedAt = getSpecificationByUpdatedAt();
        }

        return Specification.where(specByNom).and(specByDescription).and(specByStructure).and(specByCreatedAt)
            .and(specByUpdatedAt);
    }

    private Specification<Boy> getSpecificationByNom()
    {
        return (root, query, cb) -> cb.like(cb.lower(root.<String> get(Boy_.nom)), LIKE + nom.toLowerCase() + LIKE);
    }

    private Specification<Boy> getSpecificationByDescription()
    {
        return (root, query, cb) -> cb.like(cb.lower(root.<String> get(Boy_.description)),
            LIKE + description.toLowerCase() + LIKE);
    }

    private Specification<Boy> getSpecificationByStructure()
    {
        return (root, query, cb) -> root.<Girl> get(Boy_.structure).<Long> get("id").in(structures);
    }

    private Specification<Boy> getSpecificationByCreatedAt()
    {
        return (root, query, cb) -> {
            Predicate p = null;
            if ((createdAtFrom != null) && (createdAtTo != null))
            {
                p = cb.between(root.<Date> get(Boy_.createdAt), createdAtFrom, createdAtTo);
            }
            if ((createdAtFrom != null) && (createdAtTo == null))
            {
                p = cb.greaterThanOrEqualTo(root.<Date> get(Boy_.createdAt), createdAtFrom);
            }
            if ((createdAtFrom == null) && (createdAtTo != null))
            {
                p = cb.lessThanOrEqualTo(root.<Date> get(Boy_.createdAt), createdAtTo);
            }
            // return cb.or(p,
            // cb.and(p, cb.or(cb.and(p))));
            return p;
        };
    }

    private Specification<Boy> getSpecificationByUpdatedAt()
    {
        return (root, query, cb) -> {
            Predicate p = null;
            if ((updatedAtFrom != null) && (updatedAtTo != null))
            {
                p = cb.between(root.<Date> get(Boy_.updatedAt), updatedAtFrom, updatedAtTo);
            }
            if ((updatedAtFrom != null) && (updatedAtTo == null))
            {
                p = cb.greaterThanOrEqualTo(root.<Date> get(Boy_.updatedAt), updatedAtFrom);
            }
            if ((updatedAtFrom == null) && (updatedAtTo != null))
            {
                p = cb.lessThanOrEqualTo(root.<Date> get(Boy_.updatedAt), updatedAtTo);
            }
            return p;
        };
    }

}
