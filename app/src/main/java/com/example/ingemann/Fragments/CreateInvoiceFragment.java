package com.example.ingemann.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ingemann.API.MyApiAdapter;
import com.example.ingemann.DetailsActivity;
import com.example.ingemann.MainActivity;
import com.example.ingemann.Models.Article;
import com.example.ingemann.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateInvoiceFragment extends Fragment {

    private TextView txtClient, txtDate, txtDescription, txtPrice;
    private EditText etCode, etQuantity;
    private MaterialButton btnSearch, btnAdd, btnCheck;

    private Article article;
    private List<Article> articles = new ArrayList<>();

    public CreateInvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create_invoice, container, false);

        txtClient = root.findViewById(R.id.txt_client_article);
        txtDescription = root.findViewById(R.id.txt_descripcion_article);
        txtDate = root.findViewById(R.id.txt_date_article);
        txtPrice = root.findViewById(R.id.txt_price_article);

        etQuantity = root.findViewById(R.id.et_quantity_article);
        etCode = root.findViewById(R.id.et_article_code);

        btnAdd = root.findViewById(R.id.btn_add);
        btnCheck = root.findViewById(R.id.btn_check_in);
        btnSearch = root.findViewById(R.id.btn_search);

        txtClient.setText(MainActivity.USERNAME);
        txtDate.setText(MainActivity.TODAY);

        buttonsListeners();

        return root;
    }

    private void buttonsListeners(){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCode.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Porfavor ingrese un codigo de articulo", Toast.LENGTH_SHORT).show();
                } else {
                    searchCode();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etQuantity.getText().toString().isEmpty() || Integer.parseInt(etQuantity.getText().toString()) <= 0){
                    Toast.makeText(getContext(), "Porfavor ingrese una cantidad", Toast.LENGTH_SHORT).show();
                } else{
                    article.setCantidad(Integer.parseInt(etQuantity.getText().toString()));
                    articles.add(article);
                    txtDescription.setText("");
                    txtPrice.setText("");
                    etQuantity.setText("");

                    btnAdd.setEnabled(false);
                    etQuantity.setEnabled(false);
                }
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("INT_INCOMING",1);
                intent.putParcelableArrayListExtra("LIST", (ArrayList<? extends Parcelable>) articles);
                startActivity(intent);
            }
        });
    }

    private void searchCode() {
        Call<Article> call = MyApiAdapter.getApiService().getArticle(etCode.getText().toString());
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    article = response.body();

                    txtDescription.setText(article.getDescripcion());
                    txtPrice.setText(article.getPrecio());
                    btnAdd.setEnabled(true);
                    etQuantity.setEnabled(true);
                } else {
                    Toast.makeText(getContext(), "Codigo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
                Log.e("retrofit", t.toString());
            }
        });
    }

}