package com.example.ingemann.Models;

public class QuantityArticles {
    private int id_articulo;
    private int cantidad;

    public QuantityArticles(int id_articulo, int cantidad) {
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
