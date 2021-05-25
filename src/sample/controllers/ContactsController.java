package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ContactsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_parent;

    @FXML
    void initialize() {
        assert anchor_parent != null : "fx:id=\"anchor_parent\" was not injected: check your FXML file 'Contacts.fxml'.";

    }
}
