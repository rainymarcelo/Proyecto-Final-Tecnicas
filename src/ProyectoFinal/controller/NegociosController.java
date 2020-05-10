package ProyectoFinal.controller;

import ProyectoFinal.bsn.MantenimientoBsn;
import ProyectoFinal.bsn.VentasBsn;
import ProyectoFinal.model.Carros.Mantenimiento;
import ProyectoFinal.model.Carros.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class NegociosController {

    //VENTAS

    @FXML
    private TableView<Venta> tblVenta;

    @FXML
    private TableColumn<Venta,Date> clmFechaV;

    @FXML
    private TableColumn<Venta,String> clmCredencialV;

    @FXML
    private TableColumn<Venta,String> clmNombreClienteV;

    @FXML
    private TableColumn<Venta,String> clmCedulaClienteV;

    @FXML
    private TableColumn<Venta,String> clmMatriculaV;

    @FXML
    private TableColumn<Venta,String> clmMarcaV;

    @FXML
    private TableColumn<Venta,String> clmModeloV;

    @FXML
    private TableColumn<Venta,String> clmTipoV;

    @FXML
    private TableColumn<Venta,Integer> clmValorV;

    //MANTENIMIENTO

    @FXML
    private TableView<Mantenimiento> tblMantenimiento;

    @FXML
    private TableColumn<Mantenimiento, Date> clmFechaM;

    @FXML
    private TableColumn<Mantenimiento,String> clmCredencialM;

    @FXML
    private TableColumn<Mantenimiento,String> clmNombreClienteM;

    @FXML
    private TableColumn<Mantenimiento,String> clmCedulaClienteM;

    @FXML
    private TableColumn<Mantenimiento,String> clmMatriculaM;

    @FXML
    private TableColumn<Mantenimiento,String> clmMarcaM;

    @FXML
    private TableColumn<Mantenimiento,String> clmModeloM;

    @FXML
    private TableColumn<Mantenimiento,String> clmTipoM;

    @FXML
    private TableColumn<Mantenimiento,Integer> clmValorM;

    @FXML
    private  TableColumn<Mantenimiento,String> clmDescripcionM;

    private VentasBsn ventasBsn=new VentasBsn();
    private MantenimientoBsn mantenimientoBsn=new MantenimientoBsn();

    @FXML
    public void initialize(){
        List<Venta> ventas=ventasBsn.ListarVentas();
        ObservableList<Venta> ventaObservableList= FXCollections.observableList(ventas);
        this.tblVenta.setItems(ventaObservableList);

        List<Mantenimiento> mantenimientos=mantenimientoBsn.ListarMante();
        ObservableList<Mantenimiento> mantenimientoObservableList=FXCollections.observableList(mantenimientos);
        this.tblMantenimiento.setItems(mantenimientoObservableList);

        //creacion tabla ventas

        clmFechaV.setCellValueFactory(new PropertyValueFactory<>("fechaNegocio"));
        clmCredencialV.setCellValueFactory(new PropertyValueFactory<>("nroCredencial"));
        clmNombreClienteV.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        clmCedulaClienteV.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        clmMatriculaV.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        clmMarcaV.setCellValueFactory(new PropertyValueFactory<>("marca"));
        clmModeloV.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmTipoV.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clmValorV.setCellValueFactory(new PropertyValueFactory<>("valor"));

        //creacion tabla mantenimiento

        clmFechaM.setCellValueFactory(new PropertyValueFactory<>("fechaNegocio"));
        clmCredencialM.setCellValueFactory(new PropertyValueFactory<>("nroCredencial"));
        clmNombreClienteM.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        clmCedulaClienteM.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        clmMatriculaM.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        clmMarcaM.setCellValueFactory(new PropertyValueFactory<>("marca"));
        clmModeloM.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmTipoM.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clmValorM.setCellValueFactory(new PropertyValueFactory<>("valor"));
        clmDescripcionM.setCellValueFactory(new PropertyValueFactory<>("descripcionMantenimiento"));



    }


}
