package org.grade.demo.service;

import org.grade.demo.entity.Girl;
import org.grade.demo.filter.GirlFilter;
import org.grade.demo.repository.GirlRepository;
import org.springframework.stereotype.Service;

import fr.gouv.impots.appli.topad.core.persistence.service.GenericPageableService;

@Service("girlService")
public class GirlService extends GenericPageableService<Girl, GirlFilter, GirlRepository>
{

}
