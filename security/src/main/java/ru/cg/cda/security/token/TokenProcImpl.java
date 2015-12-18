package ru.cg.cda.security.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

/**
 * @author Ildar
 */
public class TokenProcImpl implements TokenProc {

  public static final String MAGIC_KEY = "obfuscate";

  //1 hour
  protected long tokenTimeout = 1000 * 60 * 60;


  public void setTokenTimeout(long timeout) {
    this.tokenTimeout = timeout;
  }

  @Override
  public String createToken(UserDetails userDetails) {
    long expires = System.currentTimeMillis() + tokenTimeout;

    return userDetails.getUsername() + ":" + expires + ":" + computeSignature(userDetails, expires);
  }

  @Override
  public String getUserNameFromToken(String authToken) {
    if (null == authToken) {
      return null;
    }

    String[] parts = authToken.split(":");
    return parts[0];
  }

  @Override
  public boolean validateToken(String authToken, UserDetails userDetails) {
    String[] parts = authToken.split(":");
    long expires = Long.parseLong(parts[1]);
    String signature = parts[2];

    if (expires < System.currentTimeMillis()) {
      return false;
    }

    return signature.equals(computeSignature(userDetails, expires));
  }

  protected String computeSignature(UserDetails userDetails, long expires) {
    StringBuilder signatureBuilder = new StringBuilder();
    signatureBuilder.append(userDetails.getUsername());
    signatureBuilder.append(":");
    signatureBuilder.append(expires);
    signatureBuilder.append(":");
    signatureBuilder.append(userDetails.getPassword());
    signatureBuilder.append(":");
    signatureBuilder.append(MAGIC_KEY);

    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("No MD5 algorithm available!");
    }

    return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
  }

}
