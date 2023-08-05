package com.nicehms.learnspringboot;

public class UhiKeys {
	private String privateKey;
	private String publicKey;
	public UhiKeys(String privateKey, String publicKey) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	@Override
	public String toString() {
		return "UhiKeys [privateKey=" + privateKey + ", publicKey=" + publicKey + "]";
	}
	
	
	

}
