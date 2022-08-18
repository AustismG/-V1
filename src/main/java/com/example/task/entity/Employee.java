package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.example.task.constant.UserRoleEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * 员工表
 * @TableName t_employee
 */
@Data
public class Employee implements Serializable, UserDetails {
    /**
     * 员工ID
     */
    private Long employeeId;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 所在部门名称
     */
    private String depName;

    /**
     * 用户角色
     */
    private Integer role;

    /**
     * 所在部门ID
     */
    private Long depId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Assert.notNull(this.role, "role不能为null！");
        for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
            if (roleEnum.getRole() == this.role) {
                return roleEnum.getAuthorities();
            }
        }
        return new ArrayList<>(0);
    }

    @Override
    public String getPassword() {
        return this.getPasswd();
    }

    @Override
    public String getUsername() {
        return this.getEmployeeName();
    }

    //必须为true！
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //必须为true！
    @Override
    public boolean isEnabled() {
        return true;
    }
}