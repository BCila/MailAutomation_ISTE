package sample.controllers;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.com.IsteMySQL.util.Kayitlar;
import sample.com.IsteMySQL.util.VeritabaniUtil;
import java.sql.*;

public class ContactsController {

    public ContactsController() {
        baglanti= VeritabaniUtil.Baglan();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_parent;

    @FXML
    private AnchorPane contacts_menu;

    @FXML
    private Button btn_contacts_yeni;

    @FXML
    private Button btn_contacts_guncelle;

    @FXML
    private Button btn_contacts_sil;

    @FXML
    private Button btn_contacts_yenile;

    @FXML
    private Label lbl_controller;
    public void displayContname(String username) {
        lbl_controller.setText(username);
        System.out.println(lbl_controller.getText().trim());
        degerleriGetir(kayitlar_tablo);
    }


    @FXML
    private TextField txt_menu_ad;

    @FXML
    private TextField txt_menu_soyad;

    @FXML
    private TextField txt_menu_mail;


    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;


    @FXML
    private TableView<Kayitlar> kayitlar_tablo;

    @FXML
    private TableColumn<Kayitlar, Integer> tablo_id;

    @FXML
    private TableColumn<Kayitlar, String> tablo_ad;

    @FXML
    private TableColumn<Kayitlar, String> tablo_soyad;

    @FXML
    private TableColumn<Kayitlar, String> tablo_mail;



    public void degerleriGetir(TableView tablo) {
        sql="select * from kayitlar where kullanici_id=(select id from kullanicilar where k_adi=?)";
        ObservableList<Kayitlar> kayitlarListe = FXCollections.observableArrayList();
        try {
            sorguIfadesi=baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1,lbl_controller.getText().trim());
            ResultSet getirilen = sorguIfadesi.executeQuery();
            while (getirilen.next()) {
                kayitlarListe.add(new Kayitlar(getirilen.getInt("kayit_id"),getirilen.getString("kayit_adi"),getirilen.getString("kayit_soyadi"),getirilen.getString("kayit_mail")));
            }

            tablo_id.setCellValueFactory(new PropertyValueFactory<>("kayit_id"));
            tablo_ad.setCellValueFactory(new PropertyValueFactory<>("kayit_ad"));
            tablo_soyad.setCellValueFactory(new PropertyValueFactory<>("kayit_soyad"));
            tablo_mail.setCellValueFactory(new PropertyValueFactory<>("kayit_mail"));

            kayitlar_tablo.setItems(kayitlarListe);

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

    }

    @FXML
    void btn_contacts_guncelle_click(ActionEvent event) {

        if (txt_menu_ad.getText().isEmpty() || txt_menu_soyad.getText().isEmpty() || txt_menu_mail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Güncelleme Hata");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen güncellenecek kaydı tablodan seçin.");
            alert.showAndWait();
        }
        else {
            Kayitlar kayit = new Kayitlar();
            kayit=(Kayitlar) kayitlar_tablo.getItems().get(kayitlar_tablo.getSelectionModel().getSelectedIndex());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kayıt Güncelle");
            alert.setHeaderText(null);
            alert.setContentText(
                    kayit.getKayit_ad()+" "+kayit.getKayit_soyad()
                            +" isimli kayıt, "+txt_menu_ad.getText().trim()+" "+txt_menu_soyad.getText().trim()+"\n"
                            +"ve "+kayit.getKayit_mail()+" adresi "+txt_menu_mail.getText().trim()
                            +" ile değiştirilecek. Emin misiniz?");

            ButtonType buttonTypeOk = new ButtonType("Evet", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk,buttonTypeCancel);

            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == buttonTypeOk) {
                System.out.println(kayit.getKayit_id());
                System.out.println(txt_menu_ad.getText());
                System.out.println(txt_menu_ad.getText().trim());
                sql="UPDATE kayitlar SET kayit_adi=?, kayit_soyadi=?, kayit_mail=? WHERE kayit_id=?";
                System.out.println(sql);
                try {
                    //System.out.println(txt_menu_ad.getText());
                    sorguIfadesi=baglanti.prepareStatement(sql);
                    sorguIfadesi.setString(1,txt_menu_ad.getText().trim());
                    sorguIfadesi.setString(2,txt_menu_soyad.getText().trim());
                    sorguIfadesi.setString(3,txt_menu_mail.getText().trim());
                    sorguIfadesi.setInt(4,kayit.getKayit_id());
                    sorguIfadesi.executeUpdate();
                    System.out.println("Kayıt güncellendi.");
                    degerleriGetir(kayitlar_tablo);

                    txt_menu_ad.clear();
                    txt_menu_soyad.clear();
                    txt_menu_mail.clear();

                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText(null);
                    alert.setContentText("Güncelleme işlemi sırasında bir hata oluştu.");

                }
            }
        }


    }

    @FXML
    void btn_contacts_sil_click(ActionEvent event) {

        if (txt_menu_ad.getText().isEmpty() || txt_menu_soyad.getText().isEmpty() || txt_menu_mail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Silme Hatası");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen silinecek kaydı tablodan seçin.");
            alert.showAndWait();
        }
        else {

            Kayitlar kayit = new Kayitlar();
            kayit=(Kayitlar) kayitlar_tablo.getItems().get(kayitlar_tablo.getSelectionModel().getSelectedIndex());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kişi Sil");
            alert.setHeaderText(null);
            alert.setContentText(kayit.getKayit_ad()+" "+kayit.getKayit_soyad()+" isimli kayıt silinecek. Emin misiniz?");

            ButtonType buttonTypeOk = new ButtonType("Evet", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOk,buttonTypeCancel);

            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == buttonTypeOk) {
                sql = "DELETE FROM kayitlar where kayit_id=" + kayit.getKayit_id();
                try {
                    sorguIfadesi = baglanti.prepareStatement(sql);
                    sorguIfadesi.executeUpdate();
                    System.out.println("Kayıt silindi.");
                    txt_menu_ad.clear();
                    txt_menu_soyad.clear();
                    txt_menu_mail.clear();
                    degerleriGetir(kayitlar_tablo);
                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Hata");
                    alert.setHeaderText(null);
                    alert.setContentText("Silme işlemi sırasında bir hata oluştu.");

                }
            }
        }
    }

    public Integer returnID(String kadi) {
        String kullanici_adi="select id from kullanicilar where k_adi=?";
        try {
            sorguIfadesi=baglanti.prepareStatement(kullanici_adi);
            sorguIfadesi.setString(1,lbl_controller.getText());
            ResultSet get_kullanici_id = sorguIfadesi.executeQuery();
            if (get_kullanici_id.next()) {
                return get_kullanici_id.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("hata");
        }
        return null;
    }

    @FXML
    void btn_contacts_yeni_click(ActionEvent event) {
        sql="insert into kayitlar (kayit_adi, kayit_soyadi, kayit_mail, kullanici_id) values (?,?,?,?)";
        if (txt_menu_ad.getText().isEmpty() || txt_menu_soyad.getText().isEmpty() || txt_menu_mail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kayıt Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen verileri eksiksiz girin.");
            alert.showAndWait();
        }
        else {
            System.out.println("hello");
            /*
            String kullanici_adi="select id from kullanicilar where k_adi=?";
            try {
                sorguIfadesi=baglanti.prepareStatement(kullanici_adi);
                sorguIfadesi.setString(1,lbl_controller.getText());
                ResultSet get_kullanici_id = sorguIfadesi.executeQuery();
                while (get_kullanici_id.next()) {
                    System.out.println(get_kullanici_id.getInt("id"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            */

            try {
                int id = returnID(lbl_controller.getText());

                sorguIfadesi = baglanti.prepareStatement(sql);
                sorguIfadesi.setString(1,txt_menu_ad.getText());
                sorguIfadesi.setString(2,txt_menu_soyad.getText());
                sorguIfadesi.setString(3,txt_menu_mail.getText());
                sorguIfadesi.setInt(4,id);
                sorguIfadesi.execute();
                degerleriGetir(kayitlar_tablo);
                txt_menu_ad.clear();
                txt_menu_soyad.clear();
                txt_menu_mail.clear();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @FXML
    void btn_contacts_yenile_click(ActionEvent event) {
        degerleriGetir(kayitlar_tablo);
        System.out.println("Degerler yenilendi");
    }

    @FXML
    void kayitlar_tablo_click(MouseEvent event) {
        try {

            Kayitlar kayit = new Kayitlar();
            kayit=(Kayitlar) kayitlar_tablo.getItems().get(kayitlar_tablo.getSelectionModel().getSelectedIndex());
            txt_menu_ad.setText(kayit.getKayit_ad());
            txt_menu_soyad.setText(kayit.getKayit_soyad());
            txt_menu_mail.setText(kayit.getKayit_mail());
        } catch (Exception e) {
            System.out.println("Tıklanan alanda değer yok");
        }
    }

    @FXML
    void initialize() {

    }
}
