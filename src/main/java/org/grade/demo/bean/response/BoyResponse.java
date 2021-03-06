package org.grade.demo.bean.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BoyResponse
{

    @ApiModelProperty("Identifiant de la mission")
    private Long id;

    @ApiModelProperty("Nom de la mission")
    private String nom;

    @ApiModelProperty("Description de la mission")
    private String description;

}
