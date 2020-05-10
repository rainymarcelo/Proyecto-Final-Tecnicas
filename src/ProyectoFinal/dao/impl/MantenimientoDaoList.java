package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.MantenimientoDao;
import ProyectoFinal.model.Carros.Mantenimiento;

import java.util.ArrayList;
import java.util.List;

public class MantenimientoDaoList implements MantenimientoDao {

    private static List<Mantenimiento> bd=new ArrayList<>();

    @Override
    public void registrarMantenimientos(Mantenimiento mantenimiento){
        bd.add(mantenimiento);
    }

    @Override
    public List<Mantenimiento> ListarMante(){
        return bd;
    }
}
