package miu.cs545.auctionsystem.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String email;
    private String name;
    private boolean seller;
    private String accessToken;

}
