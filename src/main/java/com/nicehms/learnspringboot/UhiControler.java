package com.nicehms.learnspringboot;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UhiControler {

	@RequestMapping("uhi/generateKeyspair")
	public Map<String, String> getKeyPair() throws NoSuchAlgorithmException {
		var crypto = new Crypt();
		return crypto.generateDerKeyPairs();

	}

	@RequestMapping("uhi/generateAuthorizationHeader")
	public String generateAuthorizationParams(String subscriberId, String pubKeyId, String payload, String privateKey)
			throws Exception {
		var cryto = new Crypt();
		PrivateKey curKey = cryto.getPrivateKey("Ed25519", Base64.getDecoder().decode(privateKey));
		var header = cryto.generateAuthorizationParams(subscriberId, pubKeyId, payload, curKey);
		return header;

	}

	@RequestMapping("uhi/verifysignature")
	public boolean verifySignature(String created, String expires, String signature, String publicKey, String keyId, String data) {
		
		try {
			var crypt = new Crypt();
//			Verify date
			String hashedSigningString = crypt.generateBlakeHash(
				crypt.getSigningString(Long.parseLong(created), Long.parseLong(expires), data));

			PublicKey pub_key = Crypt.getPublicKey("Ed25519", Base64.getDecoder().decode(publicKey));
			boolean ret = crypt.verifySignature(hashedSigningString, signature, "Ed25519",pub_key);
			return ret;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

}
