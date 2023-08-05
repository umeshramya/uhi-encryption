package com.nicehms.learnspringboot;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UhiControler {

	@RequestMapping("uhi/generateKeyspair")
	public List<UhiKeys> getKeyPair() {
		var crypto=new CryptUHI();
		var keyPair = crypto.generateKeyPair(EdDSAParameterSpec.Ed25519, 256);
		
		return Arrays.asList(
				new UhiKeys(crypto.getBase64Encoded(keyPair.getPrivate()), crypto.getBase64Encoded(keyPair.getPublic()))
			);
		
		
	}
	
	@RequestMapping("uhi/generateAuthorizationHeader")
	public Map<String, String> generateAuthorizationParams
	(String subscriberId, String pubKeyId, String payload, String privateKey) {
		var cryto = new CryptUHI();
		PrivateKey curKey = cryto.getPrivateKey(EdDSAParameterSpec.Ed25519, privateKey);		
		var header = cryto.generateAuthorizationParams(subscriberId, pubKeyId, payload, curKey);
		return header;
			
		
	}
	
	
	@RequestMapping("uhi/verifysignature")
	public boolean verifySignature(long created, long expires, String signature, String publicKey, String keyId, String data) {
		
		try {
			var crypt = new CryptUHI();
//			Verify date
			String hashedSigningString = crypt
					.generateBlakeHash(crypt.getSigningString(Long.valueOf(created), Long.valueOf(expires), data));
			boolean ret = crypt.verifySignature1(signature, hashedSigningString, publicKey);
			
			return ret;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

}
