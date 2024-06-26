package org.taoding.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.taoding.constant.JwtClaimsConstant;
import org.taoding.dto.EmployeeDTO;
import org.taoding.dto.EmployeeLoginDTO;
import org.taoding.dto.EmployeePageQueryDTO;
import org.taoding.entity.Employee;
import org.taoding.properties.JwtProperties;
import org.taoding.result.PageResult;
import org.taoding.result.Result;
import org.taoding.service.EmployeeService;
import org.taoding.utils.JwtUtil;
import org.taoding.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.taoding.vo.EmployeeSearchVO;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Tag(name = "员工接口")

public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return Result
     */
    @PostMapping("/login")
    @Operation(summary = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return Result
     */
    @PostMapping("/logout")
    @Operation(summary = "员工登出")
    public Result logout() {
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增员工")
    public <T> Result<T> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Result<PageResult> page(@Valid EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "启用和禁用员工账号")
    public Result startOrStop(@PathVariable @Parameter(description = "当前状态") Integer status,
                              @Parameter(description = "员工id") Long id ) {
        log.info("启用和禁用员工账号:{},{}", status, id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询")
    public Result<EmployeeSearchVO> getById(@Parameter(description = "员工id") @PathVariable Long id) {
        log.info("查询员工id:{}", id);
        EmployeeSearchVO employeeSearchVO = employeeService.getById(id);
        return Result.success(employeeSearchVO);
    }

    @PutMapping
    @Operation(summary = "根据id修改")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("修改员工id:{}", employeeDTO.getId());
        employeeService.update(employeeDTO);
        return Result.success();
    }


}
