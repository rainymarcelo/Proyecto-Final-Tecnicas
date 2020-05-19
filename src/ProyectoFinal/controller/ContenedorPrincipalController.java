package ProyectoFinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ContenedorPrincipalController {

    private Stage primaryStage;

    @FXML
    private BorderPane contenerdorPrincipal;

    @FXML
    private ImageView ivImg;

    public void mnuSalir_action() {
        System.exit(0);
    }

    public void mnuRegistrarTrabajador_action() {
        try {
            AnchorPane registrarTrabajador = FXMLLoader
                    .load(getClass().getResource("../view/registrar-trabajador.fxml"));
            this.contenerdorPrincipal.setCenter(registrarTrabajador);
            this.primaryStage.setWidth(500);
            this.primaryStage.setHeight(350);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void mnuRegistrarVenta() {
        try {
            AnchorPane registrarVenta = FXMLLoader
                    .load(getClass().getResource("../view/registrar-venta.fxml"));
            this.contenerdorPrincipal.setCenter(registrarVenta);
            this.primaryStage.setWidth(750);
            this.primaryStage.setHeight(550);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void mnuRegistrarMantenimiento() {
        try {
            AnchorPane registrarMantenimiento = FXMLLoader
                    .load(getClass().getResource("../view/registrar-mantenimiento.fxml"));
            this.contenerdorPrincipal.setCenter(registrarMantenimiento);
            this.primaryStage.setWidth(750);
            this.primaryStage.setHeight(550);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void mnuNegocio() {
        try {
            AnchorPane mostrarNegocio = FXMLLoader
                    .load(getClass().getResource("../view/mostrar-negocios.fxml"));
            this.contenerdorPrincipal.setCenter(mostrarNegocio);
            this.primaryStage.setWidth(1525);
            this.primaryStage.setHeight(1000);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
