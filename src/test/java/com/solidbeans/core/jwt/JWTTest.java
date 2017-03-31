/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.jwt;

import com.google.common.collect.Lists;
import com.solidbeans.core.security.jwt.Signer;
import com.solidbeans.core.security.jwt.Verifier;
import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.claims.Claims;
import com.solidbeans.core.util.Security;
import com.solidbeans.core.util.SolidUtil;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class JWTTest {

    @Test
    public void testHs256Normal() throws Exception {
        System.out.println("testHs256Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");
        String secret = "blablasdfsdfsdfsdfsasasdssadfsdf";
        
        Signer signer = Signer.fromAlg(Algorithm.HS256);
        String jwt = signer.sign(claims, secret);
        
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJidDQ5MDIifQ.2sqIAw4U66elX6k4BLu_sX5ecdY2VuLdVmKoA-vmd8k";
        assertEquals(expJwt, jwt);
        
        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, secret);
    }

    @Test
    public void testHs384Normal() throws Exception {
        System.out.println("testHs384Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");
        String secret = "blablasdfsdfsdfsdfsasasdssadfsdfkehdsfghjkqwansd";

        Signer signer = Signer.fromAlg(Algorithm.HS384);
        String jwt = signer.sign(claims, secret);
        
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJidDQ5MDIifQ.v29ZxRYUfLmO8HLlK1D8yqQvH70v3oXc0o7_ytIkHSewM1WK64vOOGj7A-n0-_gm";
        assertEquals(expJwt, jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, secret);
    }

    @Test
    public void testHs512Normal() throws Exception {
        System.out.println("testHs512Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");
        String secret = "blablasdfsdfsdfsdfsasasdssadfsdfblablasdfsdfsdfsdfsasasdssadfsdf";

        Signer signer = Signer.fromAlg(Algorithm.HS512);
        String jwt = signer.sign(claims, secret);
        
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJidDQ5MDIifQ.6R3Os1j5Tqb1F_rg115QtLqqhCVCAg0eqjns3hRZ9EztL7AIr_r1KgQ7PZoV1sEFqr_GMn5O06gEAPqU71cp3A";
        assertEquals(expJwt, jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, secret);
    }

    @Test
    public void testRs256Normal() throws Exception {
        System.out.println("testRs256Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");
        
        String privateKeySpec = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeWsMtPGR7C2SFGmQC73dnvLtXacA0RVS5SWj6VHlfZOo2cC/Fmrp+Y6qCFTPMHmMMonbn4w8kMTslQJkc3E9GQSJKr8CHLB7QfZ3u2zcQjI9+SASt8sD4GzEGHfSttBkGf2PW6gMkO8+zCtVe9glYl1tktQTROUOe9FQmyzNXhAgMBAAECgYAZPV3yfBkSph7BC6AuHxmxT8HcmaJOirREIjIkeW+z8Ndu/KyAZThuGmf2kjtdu8YTuI+Kh4ER2JrFqjK1aNZDq6f8ESjCwifnJ4uMcTyR2BmH9VEV9bfyzuOR1Ea5DpAF3sUx7soOCWXxrnR+0C7ZzCsQkYsRXJGK/NdGbFZigQJBAO0QHq+wCC4i86w4FeFyiDXEtd2+uItsaTQ88kGkMviHxnk+aa7zbWB00ZZ15I4IKhw+/0LLka6l0cB2ExAY8O8CQQCjsqQxcG4wibHS1xfVJRvpWegKwc8fe++wQHdAxGNPfsCfNk7EU6BK7lSAL1+Of/GY3n8oHzuYF8PRxqz6nQYvAkB/eaCWppjvfjn7zLjvXzAhgaKuF8WSq3wy6+b1Jz+FZzVxsv8PZbTWHlsphkGdooRKZhHLMD4pZN9Sl+uOR2sfAkBfOnQUIrCnYeLloaVGpIDZPikj4N1KFoI4SThWoCiZvUX3GNO3mGJ+VKkprOVs83crk/vq7khv0RtkdKx6WlCXAkAk8klGYVOy3nbJsJm6peNZVmTq/HeotUzOsHy6xv8k4OMMYeCtWjjzZjcyn6Zlu4Z6f5XhaextxR0KXpNB7o0D";
        String publicKeySpec = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXlrDLTxkewtkhRpkAu93Z7y7V2nANEVUuUlo+lR5X2TqNnAvxZq6fmOqghUzzB5jDKJ25+MPJDE7JUCZHNxPRkEiSq/Ahywe0H2d7ts3EIyPfkgErfLA+BsxBh30rbQZBn9j1uoDJDvPswrVXvYJWJdbZLUE0TlDnvRUJsszV4QIDAQAB";                
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJidDQ5MDIifQ.jxeJeDwJNHwVOMIB-JGsoSmWvHNMGI2quXCDIAkDAsEvP0Q8HRx9OPCbSj3Z55qFcRKihJT8cXGareW_CZ9-Y0idcwW2AodBysezxWzEX1uOU3sjDIuF7wEyQlgm557Abn8sDeAiz7sN-yzbZi0oKEJd1JDZx2rmaK_Jg3yPjL4";

        Signer signer = Signer.fromAlg(Algorithm.RS256);
        String jwt = signer.sign(claims, privateKeySpec);
        assertEquals(expJwt, jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKeySpec);
    }

    @Test
    public void testRs256Dynamic() throws Exception {
        System.out.println("testRs256Dynamic");

        KeyPair keyPair = SolidUtil.Security.randomRsaKeyPair(Security.RsaKeySize.KS1024);
        
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");

        Signer signer = Signer.fromAlg(Algorithm.RS256);
        String jwt = signer.sign(claims, privateKey);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKey);
    }

    @Test
    public void testRs384Normal() throws Exception {
        System.out.println("testRs384Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");        
        
        String privateKeySpec = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeWsMtPGR7C2SFGmQC73dnvLtXacA0RVS5SWj6VHlfZOo2cC/Fmrp+Y6qCFTPMHmMMonbn4w8kMTslQJkc3E9GQSJKr8CHLB7QfZ3u2zcQjI9+SASt8sD4GzEGHfSttBkGf2PW6gMkO8+zCtVe9glYl1tktQTROUOe9FQmyzNXhAgMBAAECgYAZPV3yfBkSph7BC6AuHxmxT8HcmaJOirREIjIkeW+z8Ndu/KyAZThuGmf2kjtdu8YTuI+Kh4ER2JrFqjK1aNZDq6f8ESjCwifnJ4uMcTyR2BmH9VEV9bfyzuOR1Ea5DpAF3sUx7soOCWXxrnR+0C7ZzCsQkYsRXJGK/NdGbFZigQJBAO0QHq+wCC4i86w4FeFyiDXEtd2+uItsaTQ88kGkMviHxnk+aa7zbWB00ZZ15I4IKhw+/0LLka6l0cB2ExAY8O8CQQCjsqQxcG4wibHS1xfVJRvpWegKwc8fe++wQHdAxGNPfsCfNk7EU6BK7lSAL1+Of/GY3n8oHzuYF8PRxqz6nQYvAkB/eaCWppjvfjn7zLjvXzAhgaKuF8WSq3wy6+b1Jz+FZzVxsv8PZbTWHlsphkGdooRKZhHLMD4pZN9Sl+uOR2sfAkBfOnQUIrCnYeLloaVGpIDZPikj4N1KFoI4SThWoCiZvUX3GNO3mGJ+VKkprOVs83crk/vq7khv0RtkdKx6WlCXAkAk8klGYVOy3nbJsJm6peNZVmTq/HeotUzOsHy6xv8k4OMMYeCtWjjzZjcyn6Zlu4Z6f5XhaextxR0KXpNB7o0D";
        String publicKeySpec = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXlrDLTxkewtkhRpkAu93Z7y7V2nANEVUuUlo+lR5X2TqNnAvxZq6fmOqghUzzB5jDKJ25+MPJDE7JUCZHNxPRkEiSq/Ahywe0H2d7ts3EIyPfkgErfLA+BsxBh30rbQZBn9j1uoDJDvPswrVXvYJWJdbZLUE0TlDnvRUJsszV4QIDAQAB";                
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzM4NCJ9.eyJpc3MiOiJidDQ5MDIifQ.P_kK5UN3HA26LGbMKdCdI0JX3mlz71ZaunuTtWwv7x95qlrq1ue0tcC76MFAPoxnIgSuejX-Sb9W1HwFNB-DWJuMZmh5a7h28_J89fZa1-PR81lcNqPiIZEooPgStYeXJl6q78WfeH5xqfXwvHMu5EQsiFA_Wh476xeABoOisSE";

        Signer signer = Signer.fromAlg(Algorithm.RS384);
        String jwt = signer.sign(claims, privateKeySpec);
        assertEquals(expJwt, jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKeySpec);
    }

    @Test
    public void testRs384Dynamic() throws Exception {
        System.out.println("testRs384Dynamic");

        KeyPair keyPair = SolidUtil.Security.randomRsaKeyPair(Security.RsaKeySize.KS2048);

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");

        Signer signer = Signer.fromAlg(Algorithm.RS384);
        String jwt = signer.sign(claims, privateKey);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKey);
    }

    @Test
    public void testRs512Normal() throws Exception {
        System.out.println("testRs512Normal");

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");        
        
        String privateKeySpec = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeWsMtPGR7C2SFGmQC73dnvLtXacA0RVS5SWj6VHlfZOo2cC/Fmrp+Y6qCFTPMHmMMonbn4w8kMTslQJkc3E9GQSJKr8CHLB7QfZ3u2zcQjI9+SASt8sD4GzEGHfSttBkGf2PW6gMkO8+zCtVe9glYl1tktQTROUOe9FQmyzNXhAgMBAAECgYAZPV3yfBkSph7BC6AuHxmxT8HcmaJOirREIjIkeW+z8Ndu/KyAZThuGmf2kjtdu8YTuI+Kh4ER2JrFqjK1aNZDq6f8ESjCwifnJ4uMcTyR2BmH9VEV9bfyzuOR1Ea5DpAF3sUx7soOCWXxrnR+0C7ZzCsQkYsRXJGK/NdGbFZigQJBAO0QHq+wCC4i86w4FeFyiDXEtd2+uItsaTQ88kGkMviHxnk+aa7zbWB00ZZ15I4IKhw+/0LLka6l0cB2ExAY8O8CQQCjsqQxcG4wibHS1xfVJRvpWegKwc8fe++wQHdAxGNPfsCfNk7EU6BK7lSAL1+Of/GY3n8oHzuYF8PRxqz6nQYvAkB/eaCWppjvfjn7zLjvXzAhgaKuF8WSq3wy6+b1Jz+FZzVxsv8PZbTWHlsphkGdooRKZhHLMD4pZN9Sl+uOR2sfAkBfOnQUIrCnYeLloaVGpIDZPikj4N1KFoI4SThWoCiZvUX3GNO3mGJ+VKkprOVs83crk/vq7khv0RtkdKx6WlCXAkAk8klGYVOy3nbJsJm6peNZVmTq/HeotUzOsHy6xv8k4OMMYeCtWjjzZjcyn6Zlu4Z6f5XhaextxR0KXpNB7o0D";
        String publicKeySpec = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXlrDLTxkewtkhRpkAu93Z7y7V2nANEVUuUlo+lR5X2TqNnAvxZq6fmOqghUzzB5jDKJ25+MPJDE7JUCZHNxPRkEiSq/Ahywe0H2d7ts3EIyPfkgErfLA+BsxBh30rbQZBn9j1uoDJDvPswrVXvYJWJdbZLUE0TlDnvRUJsszV4QIDAQAB";                
        String expJwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJpc3MiOiJidDQ5MDIifQ.j-1ro_HBgVuw7-jhHkC5_PFUQxydQ3UP2IXkYDKIkfOmnit6oi2nIN9HEtOzIcXo3D4HlE4eYGitRNWGWNZsuKe33rkm0z2TuTot-QiVgUmNsLREBYdkHgfe5Fn09-NXqvxSvIauJTYqDZDI2vQWps9KJL-NLfrMp3ZGkb31pLc";

        Signer signer = Signer.fromAlg(Algorithm.RS512);
        String jwt = signer.sign(claims, privateKeySpec);
        assertEquals(expJwt, jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKeySpec);
    }

    @Test
    public void testRs512Dynamic() throws Exception {
        System.out.println("testRs512Dynamic");

        KeyPair keyPair = SolidUtil.Security.randomRsaKeyPair(Security.RsaKeySize.KS2048);
        
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("bt4902");

        Signer signer = Signer.fromAlg(Algorithm.RS512);
        String jwt = signer.sign(claims, privateKey);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKey);
    }

    @Test
    public void testChatinaRegisterAndLoginRSAAuthorization() throws Exception {
        System.out.println("testChatinaRegisterAndLoginRSAAuthorization");

        long time = System.currentTimeMillis();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("chatina");        
        claims.setSub("chatina");        
        claims.setExp(time + 3000000);
        claims.setNbf(time);
        claims.setIat(time);
        claims.setJti(UUID.randomUUID().toString());
        claims.setOwn(new OwnClaims());        
        
        String privateKeySpec = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeWsMtPGR7C2SFGmQC73dnvLtXacA0RVS5SWj6VHlfZOo2cC/Fmrp+Y6qCFTPMHmMMonbn4w8kMTslQJkc3E9GQSJKr8CHLB7QfZ3u2zcQjI9+SASt8sD4GzEGHfSttBkGf2PW6gMkO8+zCtVe9glYl1tktQTROUOe9FQmyzNXhAgMBAAECgYAZPV3yfBkSph7BC6AuHxmxT8HcmaJOirREIjIkeW+z8Ndu/KyAZThuGmf2kjtdu8YTuI+Kh4ER2JrFqjK1aNZDq6f8ESjCwifnJ4uMcTyR2BmH9VEV9bfyzuOR1Ea5DpAF3sUx7soOCWXxrnR+0C7ZzCsQkYsRXJGK/NdGbFZigQJBAO0QHq+wCC4i86w4FeFyiDXEtd2+uItsaTQ88kGkMviHxnk+aa7zbWB00ZZ15I4IKhw+/0LLka6l0cB2ExAY8O8CQQCjsqQxcG4wibHS1xfVJRvpWegKwc8fe++wQHdAxGNPfsCfNk7EU6BK7lSAL1+Of/GY3n8oHzuYF8PRxqz6nQYvAkB/eaCWppjvfjn7zLjvXzAhgaKuF8WSq3wy6+b1Jz+FZzVxsv8PZbTWHlsphkGdooRKZhHLMD4pZN9Sl+uOR2sfAkBfOnQUIrCnYeLloaVGpIDZPikj4N1KFoI4SThWoCiZvUX3GNO3mGJ+VKkprOVs83crk/vq7khv0RtkdKx6WlCXAkAk8klGYVOy3nbJsJm6peNZVmTq/HeotUzOsHy6xv8k4OMMYeCtWjjzZjcyn6Zlu4Z6f5XhaextxR0KXpNB7o0D";
        String publicKeySpec = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXlrDLTxkewtkhRpkAu93Z7y7V2nANEVUuUlo+lR5X2TqNnAvxZq6fmOqghUzzB5jDKJ25+MPJDE7JUCZHNxPRkEiSq/Ahywe0H2d7ts3EIyPfkgErfLA+BsxBh30rbQZBn9j1uoDJDvPswrVXvYJWJdbZLUE0TlDnvRUJsszV4QIDAQAB";

        Signer signer = Signer.fromAlg(Algorithm.RS256);
        String jwt = signer.sign(claims, privateKeySpec);
        
        System.out.println("JWT: " + jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKeySpec);
    }

    @Test
    public void testChatinaRegisterAndLoginHMACAuthorization() throws Exception {
        System.out.println("testChatinaRegisterAndLoginHMACAuthorization");

        long time = System.currentTimeMillis();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("chatina-admin");
        claims.setSub("chatina");        
        claims.setExp(time + 3000000);
        claims.setNbf(time);
        claims.setIat(time);
        //claims.setJti("0:0:0:0:0:0:0:1");
        claims.setJti("127.0.0.1");
        claims.setJti(UUID.randomUUID().toString());
        claims.setOwn(new OwnClaims());
        claims.getOwn().getRoles().add("NORMAL");
        claims.getOwn().getRoles().add("PRIVILEGED");
                
        String secret = "e0ef24ac45f8456b9819e2a5b7532c9e";

        Signer signer = Signer.fromAlg(Algorithm.HS256);
        String jwt = signer.sign(claims, secret);
        
        System.out.println("JWT: " + jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, secret);
    }

    @Test
    public void testChatinaWithSessionRSAAuthorization() throws Exception {
        System.out.println("testChatinaWithSessionRSAAuthorization");

        long time = System.currentTimeMillis();

        Claims<OwnClaims> claims = new Claims<>();
        claims.setIss("chatina");        
        claims.setSub("chatina");        
        claims.setExp(time + 3000000);
        claims.setNbf(time);
        claims.setIat(time);
        claims.setJti(UUID.randomUUID().toString());
        claims.setOwn(new OwnClaims());        
        
        String privateKeySpec = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeWsMtPGR7C2SFGmQC73dnvLtXacA0RVS5SWj6VHlfZOo2cC/Fmrp+Y6qCFTPMHmMMonbn4w8kMTslQJkc3E9GQSJKr8CHLB7QfZ3u2zcQjI9+SASt8sD4GzEGHfSttBkGf2PW6gMkO8+zCtVe9glYl1tktQTROUOe9FQmyzNXhAgMBAAECgYAZPV3yfBkSph7BC6AuHxmxT8HcmaJOirREIjIkeW+z8Ndu/KyAZThuGmf2kjtdu8YTuI+Kh4ER2JrFqjK1aNZDq6f8ESjCwifnJ4uMcTyR2BmH9VEV9bfyzuOR1Ea5DpAF3sUx7soOCWXxrnR+0C7ZzCsQkYsRXJGK/NdGbFZigQJBAO0QHq+wCC4i86w4FeFyiDXEtd2+uItsaTQ88kGkMviHxnk+aa7zbWB00ZZ15I4IKhw+/0LLka6l0cB2ExAY8O8CQQCjsqQxcG4wibHS1xfVJRvpWegKwc8fe++wQHdAxGNPfsCfNk7EU6BK7lSAL1+Of/GY3n8oHzuYF8PRxqz6nQYvAkB/eaCWppjvfjn7zLjvXzAhgaKuF8WSq3wy6+b1Jz+FZzVxsv8PZbTWHlsphkGdooRKZhHLMD4pZN9Sl+uOR2sfAkBfOnQUIrCnYeLloaVGpIDZPikj4N1KFoI4SThWoCiZvUX3GNO3mGJ+VKkprOVs83crk/vq7khv0RtkdKx6WlCXAkAk8klGYVOy3nbJsJm6peNZVmTq/HeotUzOsHy6xv8k4OMMYeCtWjjzZjcyn6Zlu4Z6f5XhaextxR0KXpNB7o0D";
        String publicKeySpec = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXlrDLTxkewtkhRpkAu93Z7y7V2nANEVUuUlo+lR5X2TqNnAvxZq6fmOqghUzzB5jDKJ25+MPJDE7JUCZHNxPRkEiSq/Ahywe0H2d7ts3EIyPfkgErfLA+BsxBh30rbQZBn9j1uoDJDvPswrVXvYJWJdbZLUE0TlDnvRUJsszV4QIDAQAB";                
        
        Signer signer = Signer.fromAlg(Algorithm.RS256);
        String jwt = signer.sign(claims, privateKeySpec);
        
        System.out.println("JWT: " + jwt);

        Verifier verifier = Verifier.fromJwt(jwt);
        verifier.verify(jwt, publicKeySpec);
    }

    private static class OwnClaims {

        private List<String> roles = Lists.newArrayList();

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
