package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.MecanicoDao;
import ProyectoFinal.dao.exception.LlaveDuplicadaException;
import ProyectoFinal.model.Personas.Mecanico;
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

public class MecanicoDaoNio implements MecanicoDao {

    private final static String NOMBRE_ARCHIVO="mecanicos";
    private final static Path ARCHIVO= Paths.get(NOMBRE_ARCHIVO);
    private final static String FIELD_SEPARATOR=",";
    private final static String RECORD_SEPARATOR=System.lineSeparator();

    public MecanicoDaoNio(){
        if (!Files.exists(ARCHIVO)){
            try{
                Files.createFile(ARCHIVO);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }


    @Override
    public void registrarMecanico(Mecanico mecanico) throws LlaveDuplicadaException {
        Optional<Mecanico> mecanicoOptional=this.consultarMecanicoPorId(mecanico.getId());
        if(mecanicoOptional.isPresent()){
            throw  new LlaveDuplicadaException(mecanico.getId());
        }
        mecanicoOptional=this.consultarMecanicoPorCredencial(mecanico.getNroCredencial());
        if (mecanicoOptional.isPresent()){
            throw new LlaveDuplicadaException(mecanico.getNroCredencial());
        }
        String mecanicoString=parseMecanico2String(mecanico);
        byte[] datosRegistro=mecanicoString.getBytes();
        ByteBuffer byteBuffer=ByteBuffer.wrap(datosRegistro);
        try(FileChannel fileChannel=FileChannel.open(ARCHIVO,APPEND)){
            fileChannel.write(byteBuffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String parseMecanico2String(Mecanico mecanico){
        StringBuilder sb=new StringBuilder();
        sb.append(mecanico.getId()).append(FIELD_SEPARATOR)
                .append(mecanico.getNombre()).append(FIELD_SEPARATOR)
                .append(mecanico.getApellido()).append(FIELD_SEPARATOR)
                .append(mecanico.getNroCredencial()).append(RECORD_SEPARATOR);
        return sb.toString();
    }

    @Override
    public Optional<Mecanico> consultarMecanicoPorId(String id) {
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            Optional<String> mecanicoString=stream
                    .filter((mecanico->id.equals(mecanico.split(",")[0])))
                    .findFirst();
            if(mecanicoString.isPresent()){
                return Optional.of(parseMecanico2Object(mecanicoString.get()));
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return  Optional.empty();
    }

    @Override
    public Optional<Mecanico> consultarMecanicoPorCredencial(String credencial) {
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            Optional<String> mecanicoString=stream
                    .filter((mecanico->credencial.equals(mecanico.split(",")[3])))
                    .findFirst();
            if(mecanicoString.isPresent()){
                return Optional.of(parseMecanico2Object(mecanicoString.get()));
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return  Optional.empty();
    }

    @Override
    public List<Mecanico> listarMecanicos() {
        List<Mecanico> mecanicos=new ArrayList<>();
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            stream.forEach(mecanicosString -> mecanicos.add(parseMecanico2Object(mecanicosString)));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return mecanicos;
    }

    private Mecanico parseMecanico2Object(String mecanicosString){
        String[] datosMecanico= mecanicosString.split(FIELD_SEPARATOR);
        // todo:validar que el tamaño del arreglo sea de 4 elementos
        Mecanico mecanico= new Mecanico(datosMecanico[0],
                datosMecanico[1],
                datosMecanico[2],
                datosMecanico[3]);
        return mecanico;
    }

    @Override
    public List<String> listarCredenciales() {
        List<String> credenciales=new ArrayList<>();
        try(Stream<String> stream= Files.lines(ARCHIVO)){
            stream.forEach(credencialesString -> credenciales.add(parseMecanico2Object(credencialesString).getNroCredencial()));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return credenciales;
    }
}
