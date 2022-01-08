package iset.dsi.projet_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;



public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArrayList<ArticleModel> ArticleModelArrayList;
    private Context context;

    public ArticleAdapter(ArrayList<ArticleModel> articleModelArrayList, Context context) {
        ArticleModelArrayList = articleModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleModel articlemodel = ArticleModelArrayList.get(position);
        holder.nomarticle.setText(articlemodel.getName());
        holder.prixArticle.setText("TN " + articlemodel.getPrice());
    }

    @Override
    public int getItemCount() {
        return ArticleModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomarticle,prixArticle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomarticle = itemView.findViewById(R.id.idnomArticle);
            prixArticle = itemView.findViewById(R.id.idprixArticle);
        }
    }
}
