package ProyectoFinal.model.Personas;

public class Mecanico extends Persona {

    private String nroCredencial;

    public Mecanico(String id, String nombre, String apellido, String nroCredencial) {
        super(id, nombre, apellido);
        this.nroCredencial = nroCredencial;
    }

    public String getNroCredencial() {
        return nroCredencial;
    }

    public void setNroCredencial(String nroCredencial) {
        this.nroCredencial = nroCredencial;
    }
}
