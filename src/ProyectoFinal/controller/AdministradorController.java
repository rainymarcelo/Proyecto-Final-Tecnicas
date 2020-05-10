package ProyectoFinal.controller;

import ProyectoFinal.bsn.MecanicoBsn;
import ProyectoFinal.bsn.VendedorBsn;
import ProyectoFinal.bsn.exception.ObjetoYaExisteException;
import ProyectoFinal.model.Personas.Mecanico;
import ProyectoFinal.model.Personas.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class AdministradorController {

    ObservableList<String> cargolist= FXCollections.observableArrayList("Vendedor","Mecanico");

    @FXML
    private ChoiceBox<String> cbCargo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCredencial;

    private MecanicoBsn mecanicoBsn=new MecanicoBsn();
    private VendedorBsn vendedorBsn=new VendedorBsn();

    @FXML
    public void initialize(){
        cbCargo.setItems(cargolist);
    }

    @FXML
    public void btnGuardar_action(){
        String idIngresado=txtIdentificacion.getText();
        String nombreIngresado=txtNombre.getText();
        String apellidosIngresado=txtApellidos.getText();
        String credencialIngresada=txtCredencial.getText();
        String cargoSeleccionado=cbCargo.getValue();

        //revisa que todos los campos esten llenos
        boolean esValido= validarCampos(idIngresado,nombreIngresado,apellidosIngresado,credencialIngresada,cargoSeleccionado);

        if(!esValido){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro de trabajador");
            alert.setHeaderText("Registro de trabajador");
            alert.setContentText("Diligencie todos los campos");
            alert.showAndWait();
            return;
        }
        else{
            if(cargoSeleccionado.equals("Mecanico")){
                Mecanico mecanico=new Mecanico(idIngresado,nombreIngresado,apellidosIngresado,credencialIngresada);
                try {
                    mecanicoBsn.registrarMecanico(mecanico);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registro de mecanico");
                    alert.setHeaderText("Resultado de la operación");
                    alert.setContentText("El registro ha sido exitoso");
                    alert.showAndWait();
                    limpiarCampos();
                }catch (ObjetoYaExisteException oyee){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registro de mecanico");
                    alert.setHeaderText("Ha ocurrido un error");
                    alert.setContentText(oyee.getMessage());
                    alert.showAndWait();
                }
            }

            if (cargoSeleccionado.equals("Vendedor")){
                Vendedor vendedor=new Vendedor(idIngresado,nombreIngresado,apellidosIngresado,credencialIngresada);
                try {
                    vendedorBsn.registrarVendedor(vendedor);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registro de vendedor");
                    alert.setHeaderText("Resultado de la operación");
                    alert.setContentText("El registro ha sido exitoso");
                    alert.showAndWait();
                    limpiarCampos();
                }catch (ObjetoYaExisteException oyee){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registro de vendedor");
                    alert.setHeaderText("Ha ocurrido un error");
                    alert.setContentText(oyee.getMessage());
                    alert.showAndWait();
                }
            }
        }
    }

    private Boolean validarCampos(String ... campos){
        for (int i=0;i<campos.length;i++){
            if(campos[i]==null || "".equals(campos[i]) ){
                return false;
            }
        }
        return true;
    }

    private void limpiarCampos(){
        txtApellidos.clear();
        txtCredencial.clear();
        txtIdentificacion.clear();
        txtNombre.clear();
    }

}
