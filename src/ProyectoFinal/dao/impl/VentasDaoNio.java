package ProyectoFinal.dao.impl;

import ProyectoFinal.dao.VentasDao;
import ProyectoFinal.model.Carros.Venta;

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

public class VentasDaoNio implements VentasDao {

    private final static String NOMBRE_ARCHIVO="ventas";
    private final static Path ARCHIVO= Paths.get(NOMBRE_ARCHIVO);
    private final static String FIELD_SEPARATOR=",";
    private final static String RECORD_SEPARATOR=System.lineSeparator();

    public VentasDaoNio(){
        if (!Files.exists(ARCHIVO)){
            try{
                Files.createFile(ARCHIVO);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void registrarVentas(Venta ventas) {
        String ventasString=parseVentas2String(ventas);
        byte[] datosRegistro=ventasString.getBytes();
        ByteBuffer byteBuffer=ByteBuffer.wrap(datosRegistro);
        try(FileChannel fileChannel=FileChannel.open(ARCHIVO,APPEND)){
            fileChannel.write(byteBuffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private String parseVentas2String(Venta ventas){
        StringBuilder sb=new StringBuilder();
        sb.append(ventas.getMatricula()).append(FIELD_SEPARATOR)
                .append(ventas.getColor()).append(FIELD_SEPARATOR)
                .append(ventas.getMarca()).append(FIELD_SEPARATOR)
                .append(ventas.getTipo()).append(FIELD_SEPARATOR)
                .append(ventas.getModelo()).append(FIELD_SEPARATOR)
                .append(ventas.getNroCredencial()).append(FIELD_SEPARATOR)
                .append(ventas.getNombreCliente()).append(FIELD_SEPARATOR)
                .append(ventas.getIdCliente()).append(FIELD_SEPARATOR)
                .append(ventas.getApellidosCliente()).append(FIELD_SEPARATOR)
                .append(ventas.getValor()).append(RECORD_SEPARATOR);
        return sb.toString();
    }

    @Override
    public List<Venta> ListarVentas() {
        List<Venta> ventas=new ArrayList<>();
        try(Stream<String> stream=Files.lines(ARCHIVO)){
            stream.forEach(ventasString -> ventas.add(parseVentas2Object(ventasString)));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return ventas;
    }

    private Venta parseVentas2Object(String ventasString){
        String[] datosVenta= ventasString.split(FIELD_SEPARATOR);
        // todo:validar que el tamaño del arreglo sea de 10 elementos
        Venta venta=new Venta(datosVenta[0],
                datosVenta[1],
                datosVenta[2],
                datosVenta[3],
                datosVenta[4],
                datosVenta[5],
                datosVenta[6],
                datosVenta[7],
                datosVenta[8],
                Integer.parseInt(datosVenta[9]));
        return  venta;
    }
}
