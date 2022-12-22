package com.example.ingemann.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private int id;
    private String codigo;
    private String descripcion;
    private Double precio;
    private Double costo;
    private Boolean is_active;
    private int cantidad;

    public Article(int id, String codigo, String descripcion, Double precio, Double costo, Boolean is_active, int cantidad) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.costo = costo;
        this.is_active = is_active;
        this.cantidad = cantidad;
    }

    protected Article(Parcel in) {
        id = in.readInt();
        codigo = in.readString();
        descripcion = in.readString();
        if (in.readByte() == 0) {
            precio = null;
        } else {
            precio = in.readDouble();
        }
        if (in.readByte() == 0) {
            costo = null;
        } else {
            costo = in.readDouble();
        }
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        cantidad = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return "" + precio;
    }

    public Double getCosto() {
        return costo;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(codigo);
        parcel.writeString(descripcion);
        if (precio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(precio);
        }
        if (costo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(costo);
        }
        parcel.writeByte((byte) (is_active == null ? 0 : is_active ? 1 : 2));
        parcel.writeInt(cantidad);
    }
}
