package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.MecanicoDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Mecanico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MecanicoDAOList implements MecanicoDao {

    private static List<Mecanico> bd= new ArrayList<>();
    private static List<String> cred= new ArrayList<>();

    @Override
    public void registrarMecanico(Mecanico mecanico) throws LlaveDuplicadaException{
        Optional<Mecanico> mecanicoOptional=consultarMecanicoPorId(mecanico.getId());
        Optional<Mecanico> mecanicoOptionalCredencial=consultarMecanicoPorCredencial(mecanico.getNroCredencial());
        if (mecanicoOptional.isPresent()){
            throw new LlaveDuplicadaException(mecanico.getId());
        }
        if(mecanicoOptionalCredencial.isPresent()){
            throw new LlaveDuplicadaException(mecanico.getNroCredencial());
        }
        bd.add(mecanico);
        cred.add(mecanico.getNroCredencial());
    }

    @Override
    public Optional<Mecanico> consultarMecanicoPorCredencial(String credencial){
        return bd.stream()
                .filter(mecanico -> mecanico.getNroCredencial().equals(credencial))
                .findFirst();
    }

    @Override
    public Optional<Mecanico>  consultarMecanicoPorId(String id){
        return bd.stream()
                .filter(mecanico -> mecanico.getId().equals(id))
                .findFirst();
    }


    @Override
    public List<Mecanico> listarMecanicos(){
        return new ArrayList<>(bd);
    }

    @Override
    public List<String> listarCredenciales(){return new ArrayList<>(cred);}
}
