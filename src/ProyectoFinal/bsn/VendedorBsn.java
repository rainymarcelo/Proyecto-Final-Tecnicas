package ProyectoFinal.bsn;

import ProyectoFinal.bsn.exception.ObjetoYaExisteException;
import ProyectoFinal.dao.VendedorDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.dao.impl.VendedorDaoList;
import ProyectoFinal.model.Personas.Vendedor;

import javax.swing.*;
import java.util.List;

public class VendedorBsn {

    private VendedorDao vendedorDao;

    public VendedorBsn(){
        this.vendedorDao=new VendedorDaoList();
    }

    public void registrarVendedor(Vendedor vendedor) throws ObjetoYaExisteException {

        try{
            this.vendedorDao.registrarVendedor(vendedor);
        }catch (LlaveDuplicadaException lde){
            JOptionPane.showMessageDialog(null,lde);
            throw new ObjetoYaExisteException((String.format("El vendedor con cedula %s o credencial %s habia sido creado",vendedor.getId(),vendedor.getNroCredencial())));
        }
    }

    public List<Vendedor> listarVendedor(){
        return this.vendedorDao.listarVendedor();
    }

    public List<String> listarCredenciales(){
        return this.vendedorDao.listarCredenciales();
    }
}
