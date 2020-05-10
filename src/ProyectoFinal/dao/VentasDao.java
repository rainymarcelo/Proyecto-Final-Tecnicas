package ProyectoFinal.dao;

import ProyectoFinal.model.Carros.Venta;

import java.util.List;

public interface VentasDao {
    void registrarVentas(Venta ventas);
    List<Venta> ListarVentas();
}
