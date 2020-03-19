package org.grade.demo.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.grade.core.persistence.AbstractDynamicEntity_;

@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Boy.class)
public class Boy_ extends AbstractDynamicEntity_
{

    public static volatile SingularAttribute<Boy, String> nom;

    public static volatile SingularAttribute<Boy, String> description;

    public static volatile SingularAttribute<Boy, Girl> girl;

}
