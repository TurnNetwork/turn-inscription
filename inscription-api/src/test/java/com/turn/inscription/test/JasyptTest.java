package com.turn.inscription.test;

import com.turn.inscription.InscriptionApiApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InscriptionApiApplication.class,value = "spring.profiles.active=dev", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JasyptTest {

    @Autowired
    StringEncryptor encryptor;


    @Test
    public void encry () {
        String username = encryptor.encrypt("root");//加密root
        System.out.println("username:" + username);
        String password = encryptor.encrypt("Juzhen123!");//加密123456
        System.out.println("password:" + password);
        assertTrue(true);
    }

//    @Test
    public void dect () {
        String deUserName = encryptor.decrypt("ENC(bpshgjWOgn2d+oGmSTBPNA==)");//解密
        System.out.println("deUserName:" + deUserName);
    }
}
