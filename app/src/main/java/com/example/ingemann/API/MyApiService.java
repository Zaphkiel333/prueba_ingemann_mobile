package com.example.ingemann.API;

import com.example.ingemann.Models.Article;
import com.example.ingemann.Models.Invoice;
import com.example.ingemann.Models.InvoiceDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApiService {

    @GET("invoices")
    Call<ArrayList<Invoice>> getInvoices();

    @GET("articles/{codigo}")
    Call<Article> getArticle(@Path("codigo") String codigo);

    @POST("invoice_details/add")
    Call<InvoiceDetail> sendInvoiceDetail (@Body InvoiceDetail invoiceDetail);

    @GET("invoice_details/{id_factura}")
    Call<List<InvoiceDetail>> getInvoiceDetail(@Path("id_factura") int id_factura);
}
