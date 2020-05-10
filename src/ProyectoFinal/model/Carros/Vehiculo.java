package ProyectoFinal.model.Carros;

import java.util.Date;

public abstract class Vehiculo {
    private String matricula,color,marca,tipo,modelo,nroCredencial,nombreCliente,idCliente,apellidosCliente;
    private int valor;
    private Date fechaNegocio;

    public Vehiculo(String matricula, String color, String marca, String tipo, String modelo, String nroCredencial, String nombreCliente, String idCliente, String apellidosCliente, int valor, Date fechaNegocio) {
        this.matricula = matricula;
        this.color = color;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.nroCredencial = nroCredencial;
        this.nombreCliente = nombreCliente;
        this.idCliente = idCliente;
        this.apellidosCliente = apellidosCliente;
        this.valor = valor;
        this.fechaNegocio = fechaNegocio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNroCredencial() {
        return nroCredencial;
    }

    public void setNroCredencial(String nroCredencial) {
        this.nroCredencial = nroCredencial;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getFechaNegocio() {
        return fechaNegocio;
    }

    public void setFechaNegocio(Date fechaNegocio) {
        this.fechaNegocio = fechaNegocio;
    }
}
