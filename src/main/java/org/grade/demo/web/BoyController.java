package org.grade.demo.web;

import java.awt.PageAttributes.MediaType;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.grade.core.bean.ErrorResponse;
import org.grade.core.exception.BadRequestException;
import org.grade.demo.bean.response.BoyDetailedResponse;
import org.grade.demo.bean.response.BoyResponse;
import org.grade.demo.entity.Boy;
import org.grade.demo.filter.BoyFilter;
import org.grade.demo.mappers.BeanMappers;
import org.grade.demo.service.BoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
public class BoyController
{

    @Autowired
    private BoyService service;

    @RequestMapping(value = "/boys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List de boy par filtre", notes = "List de boy par filtre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boy.class, responseContainer = "List")})
    public @ResponseBody ResponseEntity<List<BoyResponse>> findAll(
        @ApiParam(name = "nom", type = "String", value = "", required = false) @RequestParam(name = "nom", required = false) String nom,
        @ApiParam(name = "description", type = "String", value = "", required = false) @RequestParam(name = "description", required = false) String description,
        @ApiParam(name = "girl", type = "Long", value = "", required = false, allowMultiple = true) @RequestParam(name = "girl", required = false) List<Long> girl,
        @ApiParam(name = "createdAtFrom", type = "Long", value = "", required = false, example = "0") @RequestParam(name = "createdAtFrom", required = false) Long createdAtFrom,
        @ApiParam(name = "createdAtTo", type = "Long", value = "", required = false, example = "0") @RequestParam(name = "createdAtTo", required = false) Long createdAtTo,
        @ApiParam(name = "updatedAtFrom", type = "Long", value = "", required = false, example = "0") @RequestParam(name = "updatedAtFrom", required = false) Long updatedAtFrom,
        @ApiParam(name = "updatedAtTo", type = "Long", value = "", required = false, example = "0") @RequestParam(name = "updatedAtTo", required = false) Long updatedAtTo)
    {

        BoyFilter filter = new BoyFilter();
        filter.setNom(nom);
        filter.setDescription(description);
        filter.setGirls(girl);
        try
        {
            if (createdAtFrom != null)
            {
                filter.setCreatedAtFrom(new Date(createdAtFrom));
            }
            if (createdAtTo != null)
            {
                filter.setCreatedAtTo(new Date(createdAtTo));
            }
            if (updatedAtFrom != null)
            {
                filter.setUpdatedAtFrom(new Date(updatedAtFrom));
            }
            if (updatedAtTo != null)
            {
                filter.setUpdatedAtTo(new Date(updatedAtTo));
            }
        }
        catch (Exception e)
        {
            throw new BadRequestException(e);
        }

        return new ResponseEntity<List<BoyResponse>>(service.findAll(filter).getContent().stream()
            .map(BeanMappers::boyToResponse).collect(Collectors.toList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/boys/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obtention d'une boy", notes = "Obtention d'une boy par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boy.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Server Error", response = ErrorResponse.class)})
    public @ResponseBody ResponseEntity<BoyDetailedResponse> findById(
        @PathVariable(name = "id", required = true) Long id)
    {

        return new ResponseEntity<BoyDetailedResponse>(BeanMappers.boyToResponseDetail(service.findById(id)),
            HttpStatus.OK);

    }

}
