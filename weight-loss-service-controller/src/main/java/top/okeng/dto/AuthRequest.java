package top.okeng.dto;

import lombok.Data;

/**
 * @author ray
 * @description
 * @since 2026/1/28
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}
