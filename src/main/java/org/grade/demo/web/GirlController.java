package org.grade.demo.web;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.stream.Collectors;

import org.grade.demo.bean.response.GirlResponse;
import org.grade.demo.entity.Girl;
import org.grade.demo.filter.GirlFilter;
import org.grade.demo.mappers.BeanMappers;
import org.grade.demo.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Topad")
public class GirlController
{

    @Autowired
    private GirlService service;

    @RequestMapping(value = "/girls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List de girls par filtre", notes = "List de girls par filtre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Girl.class, responseContainer = "List")})
    public @ResponseBody ResponseEntity<List<GirlResponse>> findAll(
        @ApiParam(name = "nom", type = "String", value = "", required = true) @RequestParam(name = "nom") String nom)
    {

        GirlFilter filter = new GirlFilter();
        filter.setNom(nom);

        return new ResponseEntity<List<GirlResponse>>(service.findAll(filter).getContent().stream()
            .map(BeanMappers::girlToResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

}
