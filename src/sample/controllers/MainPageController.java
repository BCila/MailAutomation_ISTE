package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_fitted;

    @FXML
    private AnchorPane anchor_sol_menu;

    @FXML
    private ImageView img_menu;

    @FXML
    private Button btn_menu_mailgonder;

    @FXML
    private Button btn_menu_yonet;

    @FXML
    private Button btn_menu_cikis;

    @FXML
    private AnchorPane anchor_govde;

    @FXML
    private Label lbl_usermail;

    @FXML
    private Label lbl_username;

    @FXML
    void btn_menu_cikis_click(ActionEvent event) {
        System.out.println("cikis");
        Stage stage = (Stage) btn_menu_cikis.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_menu_mailgonder_click(ActionEvent event) {
        System.out.println("gonder");
        try {
            AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxml_files/SendMail.fxml"));
            panel.getStylesheets().add(getClass().getResource("../css_files/SendMail.css").toExternalForm());
            anchor_govde.getChildren().setAll(panel);
        } catch (Exception e) {e.printStackTrace();}
    }

    @FXML
    void btn_menu_yonet_click(ActionEvent event) {
        System.out.println("yonet");
        try {
            AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxml_files/Contacts.fxml"));
            panel.getStylesheets().add(getClass().getResource("../css_files/Contacts.css").toExternalForm());
            anchor_govde.getChildren().setAll(panel);
        } catch (Exception e) {e.printStackTrace();}

    }

    @FXML
    void initialize() {
        assert anchor_fitted != null : "fx:id=\"anchor_fitted\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert anchor_sol_menu != null : "fx:id=\"anchor_sol_menu\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert img_menu != null : "fx:id=\"img_menu\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert btn_menu_mailgonder != null : "fx:id=\"btn_menu_mailgonder\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert btn_menu_yonet != null : "fx:id=\"btn_menu_yonet\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert btn_menu_cikis != null : "fx:id=\"btn_menu_cikis\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert anchor_govde != null : "fx:id=\"anchor_govde\" was not injected: check your FXML file 'MainPage.fxml'.";

    }
}
