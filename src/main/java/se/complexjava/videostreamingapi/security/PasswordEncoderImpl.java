package se.complexjava.videostreamingapi.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordEncoderImpl implements PasswordEncoder {

  // “PBKDF2WithHmacSHA1“ is a Java implementation of “PBKDF2” algorithm
  private static String algoritm = "PBKDF2WithHmacSHA1";

  public static String getAlgoritm() {
    return algoritm;
  }

  private static byte[] getSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }

  private static String toHex(byte[] array) throws NoSuchAlgorithmException {
    BigInteger bi = new BigInteger(1, array);
    String hex = bi.toString(16);
    int paddingLength = (array.length * 2) - hex.length();
    if (paddingLength > 0) {
      return String.format("%0" + paddingLength + "d", 0) + hex;
    } else {
      return hex;
    }
  }

  private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
  }

  @Override
  public String encode(CharSequence password) {
    int iterations = 1000;
    char[] chars = password.toString().toCharArray();
    byte[] salt = new byte[0];
    try {
      salt = getSalt();
      PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(algoritm);
      byte[] hash = skf.generateSecret(spec).getEncoded();
      return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean matches(CharSequence originalPasswordCh, String storedPassword) {
    try {
      char[] originalPassword = originalPasswordCh.toString().toCharArray();
      String[] parts = storedPassword.split(":");
      int iterations = Integer.parseInt(parts[0]);
      byte[] salt = new byte[0];
      salt = fromHex(parts[1]);
      byte[] hash = fromHex(parts[2]);
      PBEKeySpec spec = new PBEKeySpec(originalPassword, salt, iterations, hash.length * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(algoritm);
      byte[] testHash = skf.generateSecret(spec).getEncoded();
      int diff = hash.length ^ testHash.length;
      for (int i = 0; i < hash.length && i < testHash.length; i++) {
        diff |= hash[i] ^ testHash[i];
      }
      return diff == 0;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean upgradeEncoding(String encodedPassword) {
    return false;
  }
}
