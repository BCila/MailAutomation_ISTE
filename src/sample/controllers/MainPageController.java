package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane anchor_govde;

    @FXML
    void initialize() {
        assert anchor_fitted != null : "fx:id=\"anchor_fitted\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert anchor_sol_menu != null : "fx:id=\"anchor_sol_menu\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert anchor_govde != null : "fx:id=\"anchor_govde\" was not injected: check your FXML file 'MainPage.fxml'.";

    }
}
