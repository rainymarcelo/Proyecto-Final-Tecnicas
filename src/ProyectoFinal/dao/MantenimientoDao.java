package ProyectoFinal.dao;

import ProyectoFinal.model.Carros.Mantenimiento;

import java.util.List;

public interface MantenimientoDao {
    void registrarMantenimientos(Mantenimiento mantenimiento);
    List<Mantenimiento> ListarMante();
}
