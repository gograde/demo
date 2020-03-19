package org.grade.demo.bean.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GirlDetailedResponse
{

    @ApiModelProperty("Identifiant de la structure")
    private Long id;

    @ApiModelProperty("Nom de la structure")
    private String nom;

    @ApiModelProperty("Missions de la structure")
    private List<BoyResponse> missions;

}
