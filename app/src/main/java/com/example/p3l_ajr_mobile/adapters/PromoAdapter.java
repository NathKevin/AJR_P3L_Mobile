package com.example.p3l_ajr_mobile.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3l_ajr_mobile.R;
import com.example.p3l_ajr_mobile.models.Promo;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> {
    private List<Promo> promoList;
    private Context context;

    public PromoAdapter(List<Promo> promoList, Context context) {
        this.promoList = promoList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJenis, tvBesarPromo, tvKeterangan;
        MaterialCardView cvPromo;
        LinearLayout hiddenView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJenis = itemView.findViewById(R.id.tv_jenisPromo);
            tvBesarPromo = itemView.findViewById(R.id.tv_besarPromo);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            cvPromo = itemView.findViewById(R.id.cv_promo_item);
            hiddenView = itemView.findViewById(R.id.hidden_view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_promo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.ViewHolder holder, int position) {
        Promo promo = promoList.get(position);

        holder.tvJenis.setText(promo.getJenisPromo());
        Double temp = promo.getBesarPromo() * 100;
        Long temp2 = Math.round(temp);
        holder.tvBesarPromo.setText("Diskon : " + String.valueOf(temp2) + "%");
        holder.tvKeterangan.setText(promo.getKeterangan());

        holder.cvPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.hiddenView.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(holder.cvPromo,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.GONE);
                }else{
                    TransitionManager.beginDelayedTransition(holder.cvPromo,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public void setPromoList(List<Promo> promoList){
        this.promoList = promoList;
    }

}
