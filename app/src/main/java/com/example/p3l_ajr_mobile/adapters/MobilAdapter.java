package com.example.p3l_ajr_mobile.adapters;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.example.p3l_ajr_mobile.R;
import com.example.p3l_ajr_mobile.apis.MobilAPI;
import com.example.p3l_ajr_mobile.models.Mobil;
import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder> {
    private List<Mobil> mobilList;
    private Context context;

    public MobilAdapter(List<Mobil> mobilList, Context context) {
        this.mobilList = mobilList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvHarga, tvTransmisi, tvBahanBakar, tvFasilitas;
        ImageView ivGambar;
        MaterialCardView cvMobil;
        LinearLayout hiddenView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.tv_hargaSewa);
            tvNama = itemView.findViewById(R.id.tv_namaMpbil);
            tvTransmisi = itemView.findViewById(R.id.tv_jenisTransmisi);
            tvBahanBakar = itemView.findViewById(R.id.tv_jenisBahanBakar);
            tvFasilitas = itemView.findViewById(R.id.tv_fasilitas);
            ivGambar = itemView.findViewById(R.id.iv_gambarMobil);
            cvMobil = itemView.findViewById(R.id.cv_mobil);
            hiddenView = itemView.findViewById(R.id.hidden_view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_mobil, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mobil mobil = mobilList.get(position);

        holder.tvNama.setText(mobil.getNamaMobil());
        holder.tvTransmisi.setText(mobil.getJenisTransmisi());
        holder.tvBahanBakar.setText(mobil.getJenisBahanBakar());
        holder.tvFasilitas.setText(mobil.getFasilitas());

        DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                .getCurrencyInstance(new Locale("in", "ID"));
        holder.tvHarga.setText(" " + rupiahFormat.format(mobil.getHargaSewaMobil()));

        Glide.with(context)
                .load(MobilAPI.GET_PICTURE + mobil.getGambarMobil())
                .placeholder(R.drawable.camera)
                .into(holder.ivGambar);

        holder.cvMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.hiddenView.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(holder.cvMobil,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.GONE);
                }else{
                    TransitionManager.beginDelayedTransition(holder.cvMobil,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mobilList.size();
    }

    public void setMobilList(List<Mobil> mobilList) {
        this.mobilList = mobilList;
    }
}
