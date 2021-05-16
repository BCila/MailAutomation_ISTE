package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_menu_mailgonder;

    @FXML
    private Button btn_menu_yonet;

    @FXML
    private ImageView img_menu;

    @FXML
    private Button btn_menu_cikis;

    @FXML
    void initialize() {
        assert btn_menu_mailgonder != null : "fx:id=\"btn_menu_mailgonder\" was not injected: check your FXML file 'Menu.fxml'.";
        assert btn_menu_yonet != null : "fx:id=\"btn_menu_yonet\" was not injected: check your FXML file 'Menu.fxml'.";
        assert img_menu != null : "fx:id=\"img_menu\" was not injected: check your FXML file 'Menu.fxml'.";
        assert btn_menu_cikis != null : "fx:id=\"btn_menu_cikis\" was not injected: check your FXML file 'Menu.fxml'.";

    }
}
