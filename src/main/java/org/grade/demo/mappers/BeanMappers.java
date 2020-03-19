package org.grade.demo.mappers;

import java.util.stream.Collectors;

import org.grade.demo.bean.response.BoyDetailedResponse;
import org.grade.demo.bean.response.BoyResponse;
import org.grade.demo.bean.response.GirlDetailedResponse;
import org.grade.demo.bean.response.GirlResponse;
import org.grade.demo.entity.Boy;
import org.grade.demo.entity.Girl;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

public class BeanMappers
{

    public static BoyDetailedResponse boyToResponseDetail(@NonNull Boy boy)
    {
        BoyDetailedResponse response = new BoyDetailedResponse();
        BeanUtils.copyProperties(boy, response);
        response.setGirl(BeanMappers.girlToResponse(boy.getGirl()));
        return response;
    }

    public static BoyResponse boyToResponse(@NonNull Boy boy)
    {
        BoyResponse response = new BoyResponse();
        BeanUtils.copyProperties(boy, response);
        return response;
    }

    public static GirlDetailedResponse girlToResponseDetail(@NonNull Girl girl)
    {
        GirlDetailedResponse response = new GirlDetailedResponse();
        BeanUtils.copyProperties(girl, response);
        response.setBoys(
            girl.getBoys().stream().map(BeanMappers::boyToResponse).collect(Collectors.toList()));
        return response;
    }

    public static GirlResponse girlToResponse(@NonNull Girl girl)
    {
        GirlResponse response = new GirlResponse();
        BeanUtils.copyProperties(girl, response);
        return response;
    }

}
