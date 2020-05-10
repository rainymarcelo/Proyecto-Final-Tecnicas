package ProyectoFinal.dao;

import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Mecanico;

import java.util.List;
import java.util.Optional;

public interface MecanicoDao {

    void registrarMecanico(Mecanico mecanico) throws LlaveDuplicadaException;

    Optional<Mecanico>  consultarMecanicoPorId(String id);

    Optional<Mecanico> consultarMecanicoPorCredencial(String credencial);

    List<Mecanico> listarMecanicos();

    List<String> listarCredenciales();
}
