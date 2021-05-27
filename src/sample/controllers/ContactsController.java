package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ContactsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_parent;

    @FXML
    private Button btn_contacts_yeni;

    @FXML
    private Button btn_contacts_guncelle;

    @FXML
    private Button btn_contacts_sil;

    @FXML
    private Button btn_contacts_yenile;

    @FXML
    private TableView<?> kayitlar_tablo;

    @FXML
    void kayitlar_tablo_click(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert anchor_parent != null : "fx:id=\"anchor_parent\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert btn_contacts_yeni != null : "fx:id=\"btn_contacts_yeni\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert btn_contacts_guncelle != null : "fx:id=\"btn_contacts_guncelle\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert btn_contacts_sil != null : "fx:id=\"btn_contacts_sil\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert btn_contacts_yenile != null : "fx:id=\"btn_contacts_yenile\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert kayitlar_tablo != null : "fx:id=\"kayitlar_tablo\" was not injected: check your FXML file 'Contacts.fxml'.";

    }
}
