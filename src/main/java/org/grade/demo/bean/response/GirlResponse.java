package org.grade.demo.bean.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GirlResponse
{

    @ApiModelProperty("Identifiant de la structure")
    private Long id;

    @ApiModelProperty("Nom de la structure")
    private String nom;

}
