package com.scofield;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Jasypt加密库
 *
 * @author Tellsea
 * @date 2021/02/28
 */
public class JasyptTest {

    public static void main(String[] arg) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //1.加密所需的salt(盐)，配置值jasypt.encryptor.password
        textEncryptor.setPassword("SCOFIELDSALT");
        System.out.println("mysql加密账号:" + textEncryptor.encrypt("root"));
        System.out.println("mysql加密密码:" + textEncryptor.encrypt("root"));
    }
}
