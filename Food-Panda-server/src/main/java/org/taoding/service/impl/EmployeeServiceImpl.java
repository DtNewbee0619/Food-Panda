package org.taoding.service.impl;

import jakarta.annotation.Resource;
import org.springframework.util.DigestUtils;
import org.taoding.constant.MessageConstant;
import org.taoding.constant.StatusConstant;
import org.taoding.dto.EmployeeLoginDTO;
import org.taoding.entity.Employee;
import org.taoding.exception.AccountLockedException;
import org.taoding.exception.AccountNotFoundException;
import org.taoding.exception.PasswordErrorException;
import org.taoding.mapper.EmployeeMapper;
import org.taoding.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes());

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端传过来的明文密码进行md5加密处理
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus().equals(StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

}
