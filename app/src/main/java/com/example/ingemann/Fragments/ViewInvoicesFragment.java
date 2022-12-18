package com.example.ingemann.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ingemann.Adapters.InvoiceAdapter;
import com.example.ingemann.Models.Invoice;
import com.example.ingemann.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewInvoicesFragment extends Fragment {

    RecyclerView recyclerInvoice;
    InvoiceAdapter adapter;

    public ViewInvoicesFragment() {
        // Required empty public constructor
    }

    public static ViewInvoicesFragment newInstance(String param1, String param2) {
        ViewInvoicesFragment fragment = new ViewInvoicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view_invoices, container, false);

        recyclerInvoice = root.findViewById(R.id.recyclerViewInvoices);
        recyclerInvoice.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Invoice> invoices = new ArrayList<>();

        invoices.add(new Invoice(1,"code",new Date(2022,10,10),"Rafael", 56));
        invoices.add(new Invoice(24,"code2", new Date(2022,10,12),"Yanna",79));

        adapter =  new InvoiceAdapter(invoices, new InvoiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Invoice invoice, int position) {
                Toast.makeText(getContext(),invoices.get(position).getId(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerInvoice.setAdapter(adapter);

        return root;
    }
}