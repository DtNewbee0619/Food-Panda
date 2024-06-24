package org.taoding.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeLoginDTO implements Serializable {

    @Schema(description = "用户名")
    @NotBlank
    private String username;

    @NotBlank
    @Schema(description = "密码")
    private String password;

}
