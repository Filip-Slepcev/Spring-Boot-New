package com.springboot.blog.springbootblogrestapi.payload;

import lombok.Getter;
import lombok.Setter;

public class JWTAuthResponse {
    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken){
        this.accessToken = accessToken;
    }
}
