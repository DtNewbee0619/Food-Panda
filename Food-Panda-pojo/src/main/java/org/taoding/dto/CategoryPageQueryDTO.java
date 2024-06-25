package org.taoding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    //页码
    @Schema(description = "页码")
    private int page;

    //每页记录数
    @NotNull
    @Schema(description = "每页记录数")
    private int pageSize;

    //分类名称
    @NotBlank
    @Schema(description = "分类名称")
    private String name;

    //分类类型 1菜品分类  2套餐分类
    @Schema(description = "分类类型")
    private Integer type;

}
