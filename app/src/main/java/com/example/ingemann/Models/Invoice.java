package com.example.ingemann.Models;

public class Invoice {
    private int id;
    private String codigo;
    private String fecha;
    private String cliente;
    private float total;

    public Invoice(int id, String codigo, String fecha, String cliente, float total) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
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

    public String getCliente() {
        return cliente;
    }

    public String getTotal() {
        return ""+total;
    }
}
