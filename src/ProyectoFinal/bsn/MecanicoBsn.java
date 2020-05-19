package ProyectoFinal.bsn;

import ProyectoFinal.bsn.exception.ObjetoYaExisteException;
import ProyectoFinal.dao.MecanicoDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.dao.impl.MecanicoDAOList;
import ProyectoFinal.dao.impl.MecanicoDaoNio;
import ProyectoFinal.model.Personas.Mecanico;

import javax.swing.*;
import java.util.List;

public class MecanicoBsn {

    private MecanicoDao mecanicoDao;

    public MecanicoBsn(){
        this.mecanicoDao=new MecanicoDaoNio();
    }

    public void registrarMecanico(Mecanico mecanico) throws ObjetoYaExisteException{

        try{
            this.mecanicoDao.registrarMecanico(mecanico);
        }catch (LlaveDuplicadaException lde){
            JOptionPane.showMessageDialog(null,lde);
            throw new ObjetoYaExisteException((String.format("El mecanico con cedula %s o credencial %s habia sido creado",mecanico.getId(),mecanico.getNroCredencial())));
        }
    }

    public List<Mecanico> listarMecanicos(){
        return this.mecanicoDao.listarMecanicos();
    }

    public List<String> listarCredenciales(){return this.mecanicoDao.listarCredenciales();}
}
