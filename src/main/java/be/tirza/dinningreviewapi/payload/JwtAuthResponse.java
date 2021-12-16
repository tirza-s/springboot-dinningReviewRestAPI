package be.tirza.dinningreviewapi.payload;

public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public JwtAuthResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public JwtAuthResponse setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }
}