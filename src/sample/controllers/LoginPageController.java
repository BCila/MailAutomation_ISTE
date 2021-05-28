package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.com.IsteMySQL.util.VeritabaniUtil;
import sample.emailValidation.isValidAddress;
import java.sql.*;


public class LoginPageController{


    public LoginPageController() {
        baglanti= VeritabaniUtil.Baglan();
    }

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
    public TextField txt_giris_kadi;

    @FXML
    private Button btn_giris;

    @FXML
    private PasswordField txt_giris_parola;

    Connection baglanti=null;
    PreparedStatement sorguIfadesi = null;
    ResultSet getirilen=null;
    String sql;
    String kullanici_adi;


    @FXML
    void btn_giris_click(ActionEvent event) {
        sql="select * from kullanicilar where (k_adi=? or mail_adres=?) and parola=?";
        try {
            sorguIfadesi=baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1,txt_giris_kadi.getText().trim());
            sorguIfadesi.setString(2,txt_giris_kadi.getText().trim());
            sorguIfadesi.setString(3,txt_giris_parola.getText().trim());

            ResultSet getirilen=sorguIfadesi.executeQuery();
            if (!getirilen.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText("Kullanıcı Adı veya Şifre Hatalı");
                alert.setContentText("Lütfen Kullanıcı Adı veya Şifrenizi kontrol ediniz.");
                alert.showAndWait();
            }
            else {
                try {
                    /*
                    String username = txt_giris_kadi.getText();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml_files/MainPage.fxml"));

                    MainPageController mpcontroller = loader.getController();
                    mpcontroller.displayUsername(username);
                    */

                    /*
                    AnchorPane panel = (AnchorPane) FXMLLoader.load(getClass().getResource("../fxml_files/MainPage.fxml"));
                    panel.getStylesheets().add(getClass().getResource("../css_files/MainPage.css").toExternalForm());
                    anchor_login_body.getChildren().setAll(panel);
                     */
                    /*
                    Stage stage1 = new Stage();
                    AnchorPane anchor1 = (AnchorPane)FXMLLoader.load(getClass().getResource("../fxml_files/MainPage.fxml"));
                    Scene scene = new Scene(anchor1,1024,768);
                    scene.getStylesheets().add(getClass().getResource("../css_files/MainPage.css").toExternalForm());
                    stage1.resizableProperty().setValue(false);
                    stage1.setScene(scene);
                    stage1.show();

                     */
                    kullanici_adi="select mail_adres from kullanicilar where k_adi=?";
                    sorguIfadesi=baglanti.prepareStatement(kullanici_adi);
                    sorguIfadesi.setString(1,txt_giris_kadi.getText().trim());
                    ResultSet get_kullanici_adi = sorguIfadesi.executeQuery();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml_files/MainPage.fxml"));
                    AnchorPane pane2 = (AnchorPane) loader.load();
                    pane2.getStylesheets().add(getClass().getResource("../css_files/MainPage.css").toExternalForm());

                    MainPageController mpCont = loader.getController();
                    mpCont.displayUsername(txt_giris_kadi.getText());
                    while (get_kullanici_adi.next()) {
                        mpCont.displayUsermail(get_kullanici_adi.getString("mail_adres"));
                    }
                    anchor_login_body.getChildren().setAll(pane2);

                    /*
                    Stage stage2=new Stage();
                    stage2.setScene(scene2);
                    stage2.getIcons().add(new Image(getClass().getResourceAsStream("../img/iconw.png")));
                    stage2.show();
                    */





                } catch (Exception e) {e.printStackTrace();}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TextField getTxt_giris_kadi() {
        return txt_giris_kadi;
    }

    @FXML
    void btn_kayit_click(ActionEvent event) {
        sql="insert into kullanicilar(k_adi,mail_adres,parola) values (?,?,?)";
        if (isValidAddress.isValidEmailAddress(txt_kayit_email.getText())){
            if (txt_kayit_parola.getText().equals(txt_kayit_parolatekrar.getText())) {
                try {
                    sorguIfadesi=baglanti.prepareStatement(sql);
                    sorguIfadesi.setString(1,txt_kayit_kadi.getText().trim());
                    sorguIfadesi.setString(2,txt_kayit_email.getText().trim());
                    sorguIfadesi.setString(3,txt_kayit_parola.getText().trim());
                    sorguIfadesi.executeUpdate();

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Kayıt");
                    alert.setHeaderText("Kayıt Başarılı");
                    alert.setContentText("Kaydınız başarılı bir şekilde oluşturuldu.");
                    alert.showAndWait();
                    txt_giris_kadi.setText(txt_kayit_kadi.getText());
                    txt_kayit_kadi.clear();
                    txt_kayit_parola.clear();
                    txt_kayit_email.clear();
                    txt_kayit_parolatekrar.clear();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText("");
                alert.setContentText("Parola ve Parola Takrar birbinine uymamaktadır!");
                alert.showAndWait();
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText("");
            alert.setContentText("Mail adresi '@gmail.com' formatında olmalıdır!");
            alert.showAndWait();
        }


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
