package miu.cs545.auctionsystem.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {

    private String email;
    private String password;
}
