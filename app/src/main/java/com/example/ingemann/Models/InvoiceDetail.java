package com.example.ingemann.Models;

import java.util.List;

public class InvoiceDetail {

    private String codigo;
    private int cliente;
    private String fecha;
    private List<QuantityArticles> articulos;

    public InvoiceDetail(String codigo, int cliente, String fecha, List<QuantityArticles> articulos) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.articulos = articulos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<QuantityArticles> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<QuantityArticles> articulos) {
        this.articulos = articulos;
    }
}