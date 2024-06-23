package org.taoding.service;

import org.taoding.dto.EmployeeDTO;
import org.taoding.dto.EmployeeLoginDTO;
import org.taoding.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
}
