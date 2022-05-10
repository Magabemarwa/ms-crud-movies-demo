package com.safaricom.microservices.mscrudmoviesdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dxl.ms")
public class ConfigProperties {

    private String tibcoBillingUrl;
    private String secretKey;
    private String appVersion;
    @Value("${spring.profiles.active}")
    private String appProfile;
    @Value("${spring.r2dbc.username}")
    private String dbUsername;
    @Value("${spring.r2dbc.password}")
    private String dbPassword;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppProfile() {
        return appProfile;
    }

    public void setAppProfile(String appProfile) {
        this.appProfile = appProfile;
    }

    public String getTibcoBillingUrl() {
        return tibcoBillingUrl;
    }

    public void setTibcoBillingUrl(String tibcoBillingUrl) {
        this.tibcoBillingUrl = tibcoBillingUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
