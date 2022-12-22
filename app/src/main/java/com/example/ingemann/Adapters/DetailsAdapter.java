package com.example.ingemann.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ingemann.Models.Article;
import com.example.ingemann.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    private List<Article> articles;

    public DetailsAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_articulos, parent, false);
        return new DetailsAdapter.DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCodeArticle, txtDescription, txtPrice, txtCantidad, txtSubTotal, txtTotal;
        float subTotal, total;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCodeArticle = itemView.findViewById(R.id.txt_code_article_details);
            txtDescription = itemView.findViewById(R.id.txt_description_article_details);
            txtPrice = itemView.findViewById(R.id.txt_price_article_details);
            txtCantidad = itemView.findViewById(R.id.txt_quantity_articles_details);
            txtSubTotal = itemView.findViewById(R.id.txt_subtotal_article_details);
            txtTotal = itemView.findViewById(R.id.txt_total_article_details);
        }

        public void bind(final Article article){
            subTotal=Float.parseFloat(article.getPrecio()) * article.getCantidad();
            total = ((subTotal*15)/100)+subTotal;

            txtCodeArticle.setText(article.getCodigo());
            txtDescription.setText(article.getDescripcion());
            txtCantidad.setText(article.getCantidad()+"");
            txtPrice.setText(article.getPrecio());
            txtSubTotal.setText(subTotal+"");
            txtTotal.setText(total+"");
        }
    }
}
