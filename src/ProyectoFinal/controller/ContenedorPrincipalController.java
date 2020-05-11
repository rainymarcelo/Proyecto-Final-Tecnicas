package ProyectoFinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ContenedorPrincipalController {

    @FXML
    private BorderPane contenerdorPrincipal;

    @FXML
    private ImageView ivImg;

    public void mnuSalir_action(){
        System.exit(0);
    }

    public void mnuRegistrarTrabajador_action(){
        try{
            AnchorPane registrarVendedor= FXMLLoader
                    .load(getClass().getResource("../view/registrar-trabajador.fxml"));
            this.contenerdorPrincipal.setCenter(registrarVendedor);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void mnuRegistrarVenta(){
        try{
            AnchorPane registrarVendedor= FXMLLoader
                    .load(getClass().getResource("../view/registrar-venta.fxml"));
            this.contenerdorPrincipal.setCenter(registrarVendedor);
        this.contenerdorPrincipal.setMinSize(600,500);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void mnuRegistrarMantenimiento(){
        try{
            AnchorPane registrarVendedor= FXMLLoader
                    .load(getClass().getResource("../view/registrar-mantenimiento.fxml"));
            this.contenerdorPrincipal.setCenter(registrarVendedor);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public void mnuNegocio(){
        try{
            AnchorPane registrarVendedor= FXMLLoader
                    .load(getClass().getResource("../view/mostrar-negocios.fxml"));
            this.contenerdorPrincipal.setCenter(registrarVendedor);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
