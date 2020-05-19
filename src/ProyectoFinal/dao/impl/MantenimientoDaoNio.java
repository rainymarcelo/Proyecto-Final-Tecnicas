package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.MantenimientoDao;
import ProyectoFinal.model.Carros.Mantenimiento;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;

public class MantenimientoDaoNio implements MantenimientoDao{

    private final static String NOMBRE_ARCHIVO="mantenimiento";
    private final static Path ARCHIVO= Paths.get(NOMBRE_ARCHIVO);
    private final static String FIELD_SEPARATOR=",";
    private final static String RECORD_SEPARATOR=System.lineSeparator();

    public MantenimientoDaoNio(){
        if (!Files.exists(ARCHIVO)){
            try{
                Files.createFile(ARCHIVO);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void registrarMantenimientos(Mantenimiento mantenimiento) {
        String mantenimientoString=parseMantenimiento2String(mantenimiento);
        byte[] datosRegistro=mantenimientoString.getBytes();
        ByteBuffer byteBuffer=ByteBuffer.wrap(datosRegistro);
        try(FileChannel fileChannel=FileChannel.open(ARCHIVO,APPEND)){
            fileChannel.write(byteBuffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String parseMantenimiento2String(Mantenimiento mantenimiento){
        StringBuilder sb=new StringBuilder();
        sb.append(mantenimiento.getMatricula()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getColor()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getMarca()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getTipo()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getModelo()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getNroCredencial()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getNombreCliente()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getIdCliente()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getApellidosCliente()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getValor()).append(FIELD_SEPARATOR)
                .append(mantenimiento.getDescripcionMantenimiento()).append(RECORD_SEPARATOR);
        return sb.toString();
    }

    @Override
    public List<Mantenimiento> ListarMante() {
        List<Mantenimiento> mantenimientos=new ArrayList<>();
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            stream.forEach(mantenimientosString -> mantenimientos.add(parseMantenimiento2Object(mantenimientosString)));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return mantenimientos;
    }

    private Mantenimiento parseMantenimiento2Object(String mantenimientoString){
        String[] datosMantenimiento= mantenimientoString.split(FIELD_SEPARATOR);
        //todo: validar que el tamaño del arreglo sea de 11 elementos
        Mantenimiento mantenimiento=new Mantenimiento(datosMantenimiento[0],
                datosMantenimiento[1],
                datosMantenimiento[2],
                datosMantenimiento[3],
                datosMantenimiento[4],
                datosMantenimiento[5],
                datosMantenimiento[6],
                datosMantenimiento[7],
                datosMantenimiento[8],
                Integer.parseInt(datosMantenimiento[9]),
                datosMantenimiento[10]);
        return mantenimiento;
    }
}
