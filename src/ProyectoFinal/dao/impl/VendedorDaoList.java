package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.VendedorDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Vendedor;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendedorDaoList implements VendedorDao {

    private static List<Vendedor> bd= new ArrayList<>();
    private static List<String> cred=new ArrayList<>();

    @Override
    public void registrarVendedor(Vendedor vendedor) throws LlaveDuplicadaException {
        Optional<Vendedor> vendedorOptional=consultarVendedorPorId(vendedor.getId());
        Optional<Vendedor> vendedorOptionalCredencial=consultarVendedorPorCredencial(vendedor.getNroCredencial());
        if (vendedorOptional.isPresent()){
            throw new LlaveDuplicadaException(vendedor.getId());
        }
        if(vendedorOptionalCredencial.isPresent()){
            throw new LlaveDuplicadaException(vendedor.getNroCredencial());
        }
        bd.add(vendedor);
        cred.add((vendedor.getNroCredencial()));
    }

    @Override
    public Optional<Vendedor>  consultarVendedorPorId(String id){
        return bd.stream()
                .filter(vendedor -> vendedor.getId().equals(id))
                .findFirst();
    }

    @Override
    public  Optional<Vendedor> consultarVendedorPorCredencial(String credencial){
        return bd.stream()
                .filter(vendedor -> vendedor.getNroCredencial().equals(credencial))
                .findFirst();
    }

    @Override
    public List<Vendedor> listarVendedor(){
        return new ArrayList<>(bd);
    }

    @Override
    public List<String> listarCredenciales(){
        return new ArrayList<>(cred);
    }
}
