package ProyectoFinal.controller;

import ProyectoFinal.bsn.MantenimientoBsn;
import ProyectoFinal.bsn.MecanicoBsn;
import ProyectoFinal.model.Carros.Mantenimiento;
import ProyectoFinal.model.Personas.Mecanico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MecanicoController {

    ObservableList<String> pagoList = FXCollections.observableArrayList("Si", "No");
    ObservableList<String> tipoList = FXCollections.observableArrayList("Sedan", "Todoterreno", "Deportivo");

    @FXML
    private ChoiceBox<String> cbTipo;

    @FXML
    private ChoiceBox<String> cbMecanicos;

    @FXML
    private ChoiceBox<String> cbPago;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtDescripcion;

    private MecanicoBsn mecanicoBsn = new MecanicoBsn();
    private MantenimientoBsn mantenimientoBsn = new MantenimientoBsn();

    @FXML
    public void initialize() {
        List<String> mecanicos = mecanicoBsn.listarCredenciales();
        ObservableList<String> credenciales = FXCollections.observableArrayList(mecanicos);
        cbMecanicos.setItems(credenciales);
        cbPago.setItems(pagoList);
        cbTipo.setItems(tipoList);

        txtCedula.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCedula.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

        txtValor.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtValor.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

        txtNombre.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtNombre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        }));

        txtApellidos.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtApellidos.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        }));

        txtMarca.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtMarca.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        }));

        txtModelo.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtModelo.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        }));

        txtColor.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtColor.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        }));

        txtCedula.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtCedula.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

    }

    public void btnGuardar_action() {
        String cedulaCliente = txtCedula.getText();
        String nombreCliente = txtNombre.getText();
        String apellidosCliente = txtApellidos.getText();
        String marca = txtMarca.getText();
        String matricula = txtMatricula.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        String tipo = cbTipo.getValue();
        String valor = txtValor.getText();
        String mecanico = cbMecanicos.getValue();
        String descripcion = txtDescripcion.getText();

        boolean esValido = validarCampos(cedulaCliente, nombreCliente, apellidosCliente, marca, matricula, modelo, color, tipo,
                mecanico, cbPago.getValue(), valor, descripcion);

        if (!esValido) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro de mantenimiento");
            alert.setHeaderText("Registro de mantenimiento");
            alert.setContentText("Diligencie todos los campos");
            alert.showAndWait();
            return;
        }

        int valorVenta = Integer.parseInt(valor);

        if (valorVenta <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro de mantenimiento");
            alert.setHeaderText("Registro de mantenimiento");
            alert.setContentText("Precio de mantenimiento negativo");
            alert.showAndWait();
            return;
        } else {
            if (cbPago.getValue().equals("Si")) {
                Mantenimiento mantenimiento = new Mantenimiento(matricula, color, marca, tipo, modelo, mecanico, nombreCliente, cedulaCliente, apellidosCliente, valorVenta, descripcion);
                mantenimientoBsn.registrarMantenimientos(mantenimiento);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro de venta");
                alert.setHeaderText("Registro de venta");
                alert.setContentText("La venta fue exitoso");
                alert.showAndWait();
                limpiarCampos();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registro de venta");
                alert.setHeaderText("Registro de venta");
                alert.setContentText("El cliente debe pagar para poder completar la venta");
                alert.showAndWait();
            }
        }
    }


    private Boolean validarCampos(String... campos) {
        for (int i = 0; i < campos.length; i++) {
            if (campos[i] == null || "".equals(campos[i])) {
                return false;
            }
        }
        return true;
    }

    private void limpiarCampos() {
        txtApellidos.clear();
        txtCedula.clear();
        txtColor.clear();
        txtMarca.clear();
        txtMatricula.clear();
        txtModelo.clear();
        txtNombre.clear();
        txtValor.clear();
        txtDescripcion.clear();
    }

}
