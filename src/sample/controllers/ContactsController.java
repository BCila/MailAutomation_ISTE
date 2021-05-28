package sample.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        sql="select * from kayitlar";
        ObservableList<Kayitlar> kayitlarListe = FXCollections.observableArrayList();

        try {
            sorguIfadesi=baglanti.prepareStatement(sql);
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

    }

    @FXML
    void btn_contacts_sil_click(ActionEvent event) {

    }

    @FXML
    void btn_contacts_yeni_click(ActionEvent event) {

    }

    @FXML
    void btn_contacts_yenile_click(ActionEvent event) {

    }

    @FXML
    void kayitlar_tablo_click(MouseEvent event) {

    }

    @FXML
    void initialize() {
        degerleriGetir(kayitlar_tablo);
    }
}
