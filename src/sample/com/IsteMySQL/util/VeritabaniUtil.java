package sample.com.IsteMySQL.util;
import javafx.scene.control.Alert;

import java.sql.*;

public class VeritabaniUtil {
    static Connection conn=null;

    public static Connection Baglan() {
        try {
            //"jdbc:mysql://ServerIPAdresi/db_ismi"; "kullanici","sifre"
            conn=DriverManager.getConnection("jdbc:mysql://localhost/otomasyondb", "root", "mysql");
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            return null;
        }
    }

    public static String returnMail(String kadi) {
        Connection baglanti2=null;
        PreparedStatement sorguIfadesi2=null;
        baglanti2 = VeritabaniUtil.Baglan();
        String kullanici_mail="select mail_adres from kullanicilar where k_adi=?";
        try {
            sorguIfadesi2=baglanti2.prepareStatement(kullanici_mail);
            sorguIfadesi2.setString(1,kadi);
            ResultSet get_kullanici_mail = sorguIfadesi2.executeQuery();
            get_kullanici_mail.next();
            return get_kullanici_mail.getString("mail_adres");

        } catch (Exception e) {
            System.out.println("hata");
        }
        return "Deger Donmedi";
    }
    public static String returnPass(String kadi) {
        Connection baglanti2=null;
        PreparedStatement sorguIfadesi2=null;
        baglanti2 = VeritabaniUtil.Baglan();
        String kullanici_mail="select parola from kullanicilar where k_adi=?";
        try {
            sorguIfadesi2=baglanti2.prepareStatement(kullanici_mail);
            sorguIfadesi2.setString(1,kadi);
            ResultSet get_kullanici_mail = sorguIfadesi2.executeQuery();
            get_kullanici_mail.next();
            return get_kullanici_mail.getString("parola");

        } catch (Exception e) {
            System.out.println("hata");
        }
        return "Deger Donmedi";
    }

    public static Integer returnID(String kadi) {
        Connection baglanti;
        PreparedStatement sorguIfadesi;
        baglanti = VeritabaniUtil.Baglan();
        String kullanici_adi="select id from kullanicilar where k_adi=?";
        try {
            sorguIfadesi=baglanti.prepareStatement(kullanici_adi);
            sorguIfadesi.setString(1,kadi);
            ResultSet get_kullanici_id = sorguIfadesi.executeQuery();
            if (get_kullanici_id.next()) {
                return get_kullanici_id.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("hata");
        }
        return null;
    }

    public static void kayitSil(Integer id) {
        Connection baglanti=null;
        PreparedStatement sorguIfadesi=null;
        String sql;
        baglanti = VeritabaniUtil.Baglan();
        sql = "DELETE FROM kayitlar where kayit_id=" + id;
        try {
            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.executeUpdate();
            System.out.println("Kayıt silindi.");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Silme işlemi sırasında bir hata oluştu.");

        }
    }

    public static void kayitEkle(String kadi,String ad,String soyad,String mail) {
        Connection baglanti=null;
        PreparedStatement sorguIfadesi=null;
        String sql;
        baglanti = VeritabaniUtil.Baglan();
        sql="insert into kayitlar (kayit_adi, kayit_soyadi, kayit_mail, kullanici_id) values (?,?,?,?)";

        try {
            int getid = VeritabaniUtil.returnID(kadi);

            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1,ad);
            sorguIfadesi.setString(2,soyad);
            sorguIfadesi.setString(3,mail);
            sorguIfadesi.setInt(4,getid);
            sorguIfadesi.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void kayitGuncelle(String ad,String soyad,String mail,Integer id) {
        Connection baglanti=null;
        PreparedStatement sorguIfadesi=null;
        String sql;
        baglanti = VeritabaniUtil.Baglan();
        sql="UPDATE kayitlar SET kayit_adi=?, kayit_soyadi=?, kayit_mail=? WHERE kayit_id=?";
        try {
            //System.out.println(txt_menu_ad.getText());
            sorguIfadesi=baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1,ad);
            sorguIfadesi.setString(2,soyad);
            sorguIfadesi.setString(3,mail);
            sorguIfadesi.setInt(4,id);
            sorguIfadesi.executeUpdate();
            System.out.println("Kayıt güncellendi.");


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Güncelleme işlemi sırasında bir hata oluştu.");

        }
    }
}
