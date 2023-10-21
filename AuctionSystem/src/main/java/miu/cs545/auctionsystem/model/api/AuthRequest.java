package miu.cs545.auctionsystem.model.api;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;
}
