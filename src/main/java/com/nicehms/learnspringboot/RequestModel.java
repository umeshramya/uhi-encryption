package com.nicehms.learnspringboot;

public class RequestModel {

    private String payload;
    private String subscriberId;
    private String pubKeyId;
    private String privateKey;

    // Getters and Setters

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getPubKeyId() {
        return pubKeyId;
    }

    public void setPubKeyId(String pubKeyId) {
        this.pubKeyId = pubKeyId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
