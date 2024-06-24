package org.taoding.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //员工姓名
    @Parameter(description = "员工姓名")
    @Nullable
    private String name;

    //页码
    @Parameter(description = "页码")
    @NotNull
    private int page;

    //每页显示记录数
    @Parameter(description = "每页显示记录数")
    @NotNull
    private int pageSize;

}
