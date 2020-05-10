package ProyectoFinal.model.Carros;

import java.util.Date;

public class Mantenimiento extends Vehiculo {

    private String descripcionMantenimiento;

    public Mantenimiento(String matricula, String color, String marca, String tipo, String modelo, String nroCredencial, String nombreCliente, String idCliente, String apellidosCliente, int valor, Date fechaNegocio, String descripcionMantenimiento) {
        super(matricula, color, marca, tipo, modelo, nroCredencial, nombreCliente, idCliente, apellidosCliente, valor, fechaNegocio);
        this.descripcionMantenimiento = descripcionMantenimiento;
    }

    public String getDescripcionMantenimiento() {
        return descripcionMantenimiento;
    }

    public void setDescripcionMantenimiento(String descripcionMantenimiento) {
        this.descripcionMantenimiento = descripcionMantenimiento;
    }
}
