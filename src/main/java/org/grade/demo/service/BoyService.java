package org.grade.demo.service;

import org.grade.core.persistence.service.GenericPageableService;
import org.grade.demo.entity.Boy;
import org.grade.demo.filter.BoyFilter;
import org.grade.demo.repository.BoyRepository;
import org.springframework.stereotype.Service;

@Service("boyService")
public class BoyService extends GenericPageableService<Boy, BoyFilter, BoyRepository>
{

}
