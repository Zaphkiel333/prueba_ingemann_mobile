package com.example.ingemann.Models;

import java.util.Date;

public class Invoice {
    private int id;
    private String codigo;
    private Date fecha;
    private String nombreCliente;
    private float total;

    public Invoice(int id, String codigo, Date fecha, String nombreCliente, float total) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.total = total;
    }

    public String getId() {
        return id+"";
    }

    public String getCodigo() {
        return codigo;
    }

    public String  getFecha() {
        return fecha.toString();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getTotal() {
        return ""+total;
    }
}
