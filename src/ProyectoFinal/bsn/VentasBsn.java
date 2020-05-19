package ProyectoFinal.bsn;

import ProyectoFinal.dao.VentasDao;
import ProyectoFinal.dao.impl.VentasDaoList;
import ProyectoFinal.dao.impl.VentasDaoNio;
import ProyectoFinal.model.Carros.Venta;

import java.util.List;

public class VentasBsn {
    private VentasDao ventasDao;

    public VentasBsn() {
        this.ventasDao = new VentasDaoNio();
    }

    public void registrarVentas(Venta venta){
        this.ventasDao.registrarVentas(venta);
    }

    public List<Venta> ListarVentas(){
        return  ventasDao.ListarVentas();
    }
}
