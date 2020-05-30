package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.VendedorDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Vendedor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;

public class VendedorDaoNio implements VendedorDao {

    private final static String NOMBRE_ARCHIVO="vendedores";
    private final static Path ARCHIVO=Paths.get(NOMBRE_ARCHIVO);
    private final static String FIELD_SEPARATOR=",";
    private final static String RECORD_SEPARATOR=System.lineSeparator();

    public VendedorDaoNio(){
        if (!Files.exists(ARCHIVO)){
            try{
                Files.createFile(ARCHIVO);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void registrarVendedor(Vendedor vendedor) throws LlaveDuplicadaException {
        Optional<Vendedor> vendedorOptional=this.consultarVendedorPorId(vendedor.getId());
        if (vendedorOptional.isPresent()){
            throw new LlaveDuplicadaException(vendedor.getId());
        }
        vendedorOptional=this.consultarVendedorPorCredencial(vendedor.getNroCredencial());
        if (vendedorOptional.isPresent()){
            throw new LlaveDuplicadaException(vendedor.getNroCredencial());
        }
        String vendedorString=parseVendedor2String(vendedor);
        byte[] datosRegistro=vendedorString.getBytes();
        ByteBuffer byteBuffer=ByteBuffer.wrap(datosRegistro);
        try(FileChannel fileChannel=FileChannel.open(ARCHIVO,APPEND)){
            fileChannel.write(byteBuffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String parseVendedor2String(Vendedor vendedor){
        StringBuilder sb=new StringBuilder();
        sb.append(vendedor.getId()).append(FIELD_SEPARATOR)
                .append(vendedor.getNombre()).append(FIELD_SEPARATOR)
                .append(vendedor.getApellido()).append(FIELD_SEPARATOR)
                .append(vendedor.getNroCredencial()).append(RECORD_SEPARATOR);
        return sb.toString();
    }

    @Override
    public Optional<Vendedor> consultarVendedorPorId(String id) {
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            Optional<String> vendedorString=stream
                    .filter((vendedor->id.equals(vendedor.split(",")[0])))
                    .findFirst();
            if(vendedorString.isPresent()){
                return Optional.of(parseVendedor2Object(vendedorString.get()));
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return  Optional.empty();
    }

    @Override
    public Optional<Vendedor> consultarVendedorPorCredencial(String credencial) {
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            Optional<String> vendedorString=stream
                    .filter((vendedor->credencial.equals(vendedor.split(",")[3])))
                    .findFirst();
            if(vendedorString.isPresent()){
                return Optional.of(parseVendedor2Object(vendedorString.get()));
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return  Optional.empty();
    }

    @Override
    public List<Vendedor> listarVendedor() {
        List<Vendedor> vendedores=new ArrayList<>();
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            stream.forEach(vendedoresString -> vendedores.add(parseVendedor2Object(vendedoresString)));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return vendedores;
    }

    private Vendedor parseVendedor2Object(String vendedoresString){
        String[] datosVendedor= vendedoresString.split(FIELD_SEPARATOR);
        // todo:validar que el tamaño del arreglo sea de 4 elementos
        Vendedor vendedor= new Vendedor(datosVendedor[0],
        datosVendedor[1],
        datosVendedor[2],
        datosVendedor[3]);
        return vendedor;
    }

    @Override
    public List<String> listarCredenciales() {
        List<String> credenciales=new ArrayList<>();
        try(Stream<String> stream= Files.lines(ARCHIVO)){
            stream.forEach(credencialesString -> credenciales.add(parseVendedor2Object(credencialesString).getNroCredencial()));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return credenciales;
    }
}
