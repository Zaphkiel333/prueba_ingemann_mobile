package com.example.ingemann;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ingemann.API.MyApiAdapter;
import com.example.ingemann.Adapters.DetailsAdapter;
import com.example.ingemann.Models.Article;
import com.example.ingemann.Models.InvoiceDetail;
import com.example.ingemann.Models.QuantityArticles;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private TextView txtClient, txtDate, txtInvoice, txtTotal;
    private MaterialButton btnSave;
    private RecyclerView recyclerDetails;
    private DetailsAdapter adapter;

    private List<Article> articles;
    private InvoiceDetail invoiceDetail;
    private List<QuantityArticles> quantityArticles = new ArrayList<>();
    private QuantityArticles quantity;
    private int intComing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        bindUI();

        Bundle bundle = getIntent().getExtras();
        articles = bundle.getParcelableArrayList("LIST");
        intComing = bundle.getInt("INT_INCOMING");
        if (intComing == 1){
            adaterForStoring();
        }else {

        }
    }

    private void bindUI() {
        txtClient = findViewById(R.id.txt_client_article_details);
        txtDate = findViewById(R.id.txt_date_article_details);
        btnSave = findViewById(R.id.btn_save);
        txtInvoice = findViewById(R.id.txt_invoice_articles_details);
        txtTotal = findViewById(R.id.txt_total);

        txtClient.setText(MainActivity.USERNAME);

        recyclerDetails = findViewById(R.id.recyclerViewDetails);
        recyclerDetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void adaterForStoring() {
        btnSave.setVisibility(View.VISIBLE);
        txtDate.setText(MainActivity.TODAY);

        String invoice = UUID.randomUUID().toString().toUpperCase().substring(0,8);

        txtInvoice.setText(invoice);

        adapter = new DetailsAdapter(articles);
        recyclerDetails.setAdapter(adapter);

        createInvoiceDetails(invoice);
    }

    private void createInvoiceDetails(String invoice){
        double subtotal,total=0;
        for(Article article: articles){
            quantity = new QuantityArticles(article.getId(), article.getCantidad());
            quantityArticles.add(quantity);
            subtotal = (article.getCantidad()* Float.parseFloat(article.getPrecio()));
            total += ((subtotal*15)/100)+subtotal;
        }
        invoiceDetail = new InvoiceDetail(invoice,4,MainActivity.TODAY,quantityArticles);
        txtTotal.setText(total+"");
    }

    public void save(View view) {
        Call<InvoiceDetail> call = MyApiAdapter.getApiService().sendInvoiceDetail(invoiceDetail);
        call.enqueue(new Callback<InvoiceDetail>() {
            @Override
            public void onResponse(Call<InvoiceDetail> call, Response<InvoiceDetail> response) {
                if(response.isSuccessful()){
                    if(response.code() == 200){
                        Toast.makeText(getApplicationContext(),"Guardado exitosamente",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<InvoiceDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        });
    }
}