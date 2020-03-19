package org.grade.demo.filter;

import org.grade.core.persistence.filter.AbstractPageableFilter;
import org.grade.demo.entity.Girl;
import org.grade.demo.entity.Girl_;
import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GirlFilter extends AbstractPageableFilter<Girl>
{

    private String nom;

    @Override
    public void reset()
    {
        this.nom = null;
    }

    @Override
    public Specification<Girl> getSpecifications()
    {
        Specification<Girl> specByNom = null;
        if (nom != null && !nom.isEmpty())
        {
            specByNom = getSpecificationByNom();
        }
        return Specification.where(specByNom);
    }

    private Specification<Girl> getSpecificationByNom()
    {
        return (root, query, cb) -> cb.like(cb.lower(root.<String> get(Girl_.nom)), LIKE + nom.toLowerCase() + LIKE);
    }

}
