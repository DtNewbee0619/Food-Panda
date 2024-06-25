package org.taoding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author taoding
 */
@Data
public class CategoryDTO implements Serializable {

    //主键
    @Schema(description = "主键")
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    @Schema(description = "分类类型")
    @NotNull
    private Integer type;

    //分类名称
    @Schema(description = "分类名称")
    @NotBlank
    private String name;

    //排序
    @Schema(description = "排序")
    @NotNull
    private Integer sort;

}
