package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.VentasDao;
import ProyectoFinal.model.Carros.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentasDaoList implements VentasDao {

    private static List<Venta> bd=new ArrayList<>();

    @Override
    public void registrarVentas(Venta ventas){
        bd.add(ventas);
    }

    @Override
    public List<Venta> ListarVentas(){
        return  bd;
    }
}
