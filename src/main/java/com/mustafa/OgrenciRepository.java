package com.mustafa;

import java.sql.*;
import java.util.Scanner;

public class OgrenciRepository {
    static Scanner scanner = new Scanner(System.in);
    static String adres = "jdbc:postgresql://localhost:5432/OkulDB";
    static String ka = "postgres";
    static String sf = "root";
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(adres,ka,sf);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dbislem() throws Exception {
        Driver.class.forName("org.postgresql.Driver");
    }

    public static void menu(){
        System.out.println("************************");
        System.out.println("*** OKUL OTOMASYONU ****");
        System.out.println("*** 1- Öğrenci Ekle ****");
        System.out.println("** 2- Öğrenci Listele **");
        System.out.println("** 3- Öğrenci Düzenle **");
        System.out.println("***       CIKIS      ***");
    }
    public static void app() throws Exception {
        int secim;
        do {
            menu();
            System.out.println("Yapmak istediğiniz işlemi seçiniz..");
            secim = scanner.nextInt();
            scanner.nextLine();
            switch (secim) {
                case 1:
                    ogrenciEkle();
                    break;
                case 2:
                    ogrenciListele();
                    break;
                case 3:
                    ogrenciDuzenle();
                    break;
                case 0:
                    System.exit(0);
                    conn.close();
                    break;
            }
        }while (secim!=0);
    }



    public static void ogrenciEkle() throws Exception {
        dbislem();
        System.out.println("Öğrenci adını giriniz..");
        String ogrenciAd= scanner.nextLine();
        System.out.println("Öğrencinin soyadını girin..");
        String ogrenciSoyad =scanner.nextLine();
        System.out.println("Öğrencinin yaşını giriniz..");
        int yas = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Öğrencinin veli adını giriniz..");
        String veliAdi = scanner.nextLine();
        System.out.println("Öğrencinin iletişim numarasını giriniz..");
        String iletisim = scanner.nextLine();
        String SQL = "insert into tblogrenci(ad,soyad,yas,veliadi,iletisimtel) " +
                "values('"+ogrenciAd+"',"+"'"+ogrenciSoyad+"',"+yas+","+"'"+veliAdi+"',"+"'"+iletisim+"')";
        PreparedStatement pr = conn.prepareCall(SQL);
        pr.executeUpdate();
        System.out.println("Öğrenci başarı ile eklendi");
    }

    public static void ogrenciListele() throws Exception {
        dbislem();
        String SQL = "select * from tblogrenci";
        PreparedStatement pr = conn.prepareCall(SQL);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String ad = rs.getString("ad");
            String soyad = rs.getString("soyad");
            int yas = rs.getInt("yas");
            String veliAdi = rs.getString("veliadi");
            String iletisim = rs.getString("iletisimtel");
            System.out.println("id..: "+id+" - ad..: "+ad+" - soyad..: "+soyad+" - yas..: "+yas+" - veliAdi..: "+veliAdi+" - iletisim..: "+iletisim);
        }


    }
    public static void ogrenciDuzenle() throws Exception {
        dbislem();
        System.out.println("Öğrencinin id sini giriniz..");
        int ogrenciId= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Değiştirmek istediğiniz kolon adını giriniz");
        String kolonAdi = scanner.nextLine();
        System.out.println("Değiştirmek istediğiniz yeni içeriği giriniz");
        String yeniIcerik = scanner.nextLine();
        String SQL = "update tblogrenci set "+kolonAdi+"='"+yeniIcerik+"' where id="+ogrenciId+"";
        PreparedStatement pr = conn.prepareCall(SQL);
        pr.executeUpdate();
        System.out.println("Öğrenci başarı ile düzenlendi");
    }
}
