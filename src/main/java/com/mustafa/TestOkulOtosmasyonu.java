package com.mustafa;

import java.sql.Driver;


public class TestOkulOtosmasyonu {
    public static void main(String[] args) throws Exception {
        OgrenciRepository ogrenciRepository = new OgrenciRepository();
        Driver.class.forName("org.postgresql.Driver");
        ogrenciRepository.app();

    }
}
