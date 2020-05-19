package ProyectoFinal.bsn;

import ProyectoFinal.dao.MantenimientoDao;
import ProyectoFinal.dao.impl.MantenimientoDaoList;
import ProyectoFinal.dao.impl.MantenimientoDaoNio;
import ProyectoFinal.model.Carros.Mantenimiento;

import java.util.List;

public class MantenimientoBsn {
    private MantenimientoDao mantenimientoDao;

    public MantenimientoBsn() {
        this.mantenimientoDao = new MantenimientoDaoNio();
    }

    public void  registrarMantenimientos(Mantenimiento mantenimiento){
        this.mantenimientoDao.registrarMantenimientos(mantenimiento);
    }

    public List<Mantenimiento> ListarMante(){
        return  mantenimientoDao.ListarMante();
    }
}
