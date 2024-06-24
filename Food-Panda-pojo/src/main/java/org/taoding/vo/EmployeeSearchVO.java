package org.taoding.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Date 6/24/24 17:06
 * @Author Tao Ding
 * @Description: TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchVO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

}
