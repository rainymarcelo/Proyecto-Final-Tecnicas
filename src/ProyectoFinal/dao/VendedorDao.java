package ProyectoFinal.dao;

import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Vendedor;


import java.util.List;
import java.util.Optional;

public interface VendedorDao {
    void registrarVendedor(Vendedor vendedor) throws LlaveDuplicadaException;

    Optional<Vendedor> consultarVendedorPorId(String id);

    Optional<Vendedor> consultarVendedorPorCredencial(String credencial);

    List<Vendedor> listarVendedor();

    List<String> listarCredenciales();
}
