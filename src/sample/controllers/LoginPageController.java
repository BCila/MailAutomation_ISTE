package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_main;

    @FXML
    private AnchorPane anchor_login_body;

    @FXML
    private AnchorPane anchor_signup;

    @FXML
    private TextField txt_kayit_kadi;

    @FXML
    private TextField txt_kayit_email;

    @FXML
    private Button btn_kayit;

    @FXML
    private PasswordField txt_kayit_parola;

    @FXML
    private PasswordField txt_kayit_parolatekrar;

    @FXML
    private AnchorPane anchor_login;

    @FXML
    private TextField txt_giris_kadi;

    @FXML
    private Button btn_giris;

    @FXML
    private PasswordField txt_giris_parola;

    @FXML
    void btn_giris_click(ActionEvent event) {
        try {
            AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxml_files/MainPage.fxml"));
            panel.getStylesheets().add(getClass().getResource("../css_files/MainPage.css").toExternalForm());
            anchor_login_body.getChildren().setAll(panel);
        } catch (Exception e) {e.printStackTrace();}
    }

    @FXML
    void btn_kayit_click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert anchor_main != null : "fx:id=\"anchor_main\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert anchor_login_body != null : "fx:id=\"anchor_login_body\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert anchor_signup != null : "fx:id=\"anchor_signup\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_kayit_kadi != null : "fx:id=\"txt_kayit_kadi\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_kayit_email != null : "fx:id=\"txt_kayit_email\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btn_kayit != null : "fx:id=\"btn_kayit\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_kayit_parola != null : "fx:id=\"txt_kayit_parola\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_kayit_parolatekrar != null : "fx:id=\"txt_kayit_parolatekrar\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert anchor_login != null : "fx:id=\"anchor_login\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_giris_kadi != null : "fx:id=\"txt_giris_kadi\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert btn_giris != null : "fx:id=\"btn_giris\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert txt_giris_parola != null : "fx:id=\"txt_giris_parola\" was not injected: check your FXML file 'LoginPage.fxml'.";

    }
}
