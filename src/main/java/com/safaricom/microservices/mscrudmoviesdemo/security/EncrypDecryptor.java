package com.safaricom.microservices.mscrudmoviesdemo.security;

import com.safaricom.microservices.libs.encryption.AESUtility;
import com.safaricom.microservices.mscrudmoviesdemo.config.ConfigProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EncrypDecryptor extends AESUtility {

    private final ConfigProperties properties;

    public EncrypDecryptor(ConfigProperties properties) {
        this.properties = properties;
    }

    public String encryptText(String payload){

        return this.encryptPayload(properties.getAppProfile(), properties.getAppVersion(),
                properties.getSecretKey(), payload);
    }

   public String decryptText(String payload){
        return this.decryptPayload(properties.getAppProfile(), properties.getAppVersion(),
                properties.getSecretKey(), payload);
   }
}
