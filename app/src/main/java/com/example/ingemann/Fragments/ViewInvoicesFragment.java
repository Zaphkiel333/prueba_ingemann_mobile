package com.example.ingemann.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ingemann.API.MyApiAdapter;
import com.example.ingemann.Adapters.InvoiceAdapter;
import com.example.ingemann.Models.Invoice;
import com.example.ingemann.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewInvoicesFragment extends Fragment {

    private RecyclerView recyclerInvoice;
    private InvoiceAdapter adapter;

    private List<Invoice> invoices;

    public ViewInvoicesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Call<ArrayList<Invoice>> call = MyApiAdapter.getApiService().getInvoices();
        call.enqueue(new Callback<ArrayList<Invoice>>() {
            @Override
            public void onResponse(Call<ArrayList<Invoice>> call, Response<ArrayList<Invoice>> response) {
                if (response.isSuccessful()) {
                    invoices = response.body();

                    adapter = new InvoiceAdapter(invoices, new InvoiceAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Invoice invoice, int position) {
                            Toast.makeText(getContext(), invoices.get(position).getId(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    recyclerInvoice.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Invoice>> call, Throwable t) {
                Toast.makeText(getContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view_invoices, container, false);

        recyclerInvoice = root.findViewById(R.id.recyclerViewInvoices);
        recyclerInvoice.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

}