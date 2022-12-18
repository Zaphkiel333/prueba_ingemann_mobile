package com.example.ingemann.API;

import com.example.ingemann.Models.Invoice;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiService {

    @GET("invoices")
    Call<ArrayList<Invoice>> getInvoices();

}
