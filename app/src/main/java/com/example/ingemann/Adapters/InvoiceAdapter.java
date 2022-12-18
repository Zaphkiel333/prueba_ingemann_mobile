package com.example.ingemann.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ingemann.Models.Invoice;
import com.example.ingemann.R;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private List<Invoice> invoices;
    private OnItemClickListener itemClickListener;

    public InvoiceAdapter(List<Invoice> invoices, OnItemClickListener itemClickListener) {
        this.invoices = invoices;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_invoices, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.bind(invoices.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder {
        public TextView txtClient, txtInvoice, txtDate, txtMount;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClient = itemView.findViewById(R.id.txt_client);
            txtInvoice =  itemView.findViewById(R.id.txt_invoice);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtMount = itemView.findViewById(R.id.txt_mount);
        }

        public void bind(final Invoice invoice, final OnItemClickListener listener){
            txtInvoice.setText(invoice.getCodigo());
            txtDate.setText(invoice.getFecha());
            txtClient.setText(invoice.getCliente());
            txtMount.setText(invoice.getTotal());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(invoice,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Invoice invoice, int position);
    }

}
