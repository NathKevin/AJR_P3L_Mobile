package com.example.p3l_ajr_mobile.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p3l_ajr_mobile.R;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Transaksi;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class RiwayatDriverAdapter extends RecyclerView.Adapter<RiwayatDriverAdapter.ViewHolder>{
    private List<Transaksi> transaksiList;
    private Context context;

    public RiwayatDriverAdapter(List<Transaksi> transaksiList, Context context) {
        this.transaksiList = transaksiList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdTransaksi, tvTanggalSewa, tvTanggalSelesai;
        Chip chipStatus;

        MaterialCardView cvTransaksi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdTransaksi = itemView.findViewById(R.id.tv_idTransaksi);
            tvTanggalSewa = itemView.findViewById(R.id.tv_tanggalSewa);
            tvTanggalSelesai = itemView.findViewById(R.id.tv_tanggalSelesai);
            chipStatus = itemView.findViewById(R.id.chip_status);

            cvTransaksi = itemView.findViewById(R.id.cv_riwayatDriver_item);
        }
    }

    @NonNull
    @Override
    public RiwayatDriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_riwayat_driver, parent, false);

        return new RiwayatDriverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatDriverAdapter.ViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);

        holder.tvIdTransaksi.setText(transaksi.getIdTransaksi());
        holder.tvTanggalSewa.setText(transaksi.getTanggalWaktuSewa());
        holder.tvTanggalSelesai.setText(transaksi.getTanggalWaktuSelesai());
        holder.chipStatus.setText(transaksi.getStatusTransaksi());
        if(transaksi.getStatusTransaksi().equals("Selesai")){
            holder.chipStatus.setChipBackgroundColorResource(R.color.selesai);
        }else if(transaksi.getStatusTransaksi().equals("Menunggu Konfirmasi")){
            holder.chipStatus.setChipBackgroundColorResource(R.color.menungguKonfirmasi);
        }else if(transaksi.getStatusTransaksi().equals("Diterima")){
            holder.chipStatus.setChipBackgroundColorResource(R.color.selesai);
        }else if(transaksi.getStatusTransaksi().equals("Ditolak") || transaksi.getStatusTransaksi().equals("Transaksi Ditolak") ){
            holder.chipStatus.setChipBackgroundColorResource(R.color.ditolak);
        }else if(transaksi.getStatusTransaksi().equals("Batal")){
            holder.chipStatus.setChipBackgroundColorResource(R.color.black);
        }else if(transaksi.getStatusTransaksi().equals("Peminjaman Berlangsung")){
            holder.chipStatus.setChipBackgroundColorResource(R.color.pinjam);
        }

        holder.cvTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialog = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_detil_riwayat_driver, null);

                DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                        .getCurrencyInstance(new Locale("in", "ID"));

                //Transaksi
                TextView tvTanggalKembali, tvTanggalTransaksi, tvDetailIdTransaksi, tvDetailTanggalSewa, tvDetailTanggalSelesai;
                Chip chipDetailStatus;
                RatingBar ratingDriver;
                TextInputEditText performaDriver;

                //Mobil
                TextView tvNamaMobil, tvNoPlat;
                ImageView ivGambarMobil;

                //Pembayaran
                TextView tvTotalBiayaDriver;
                Chip chipDetailStatusPembayaran;

                ratingDriver = dialog.findViewById(R.id.rating_detailDriverr);
                performaDriver = dialog.findViewById(R.id.tiet_performaDriver);
                tvDetailIdTransaksi = dialog.findViewById(R.id.tv_detailIdTransaksi);
                tvDetailTanggalSewa = dialog.findViewById(R.id.tv_detilTanggalSewa);
                tvDetailTanggalSelesai = dialog.findViewById(R.id.tv_detilTanggalSelesai);
                tvTanggalTransaksi = dialog.findViewById(R.id.tv_detailTanggalTransaksi);
                tvTanggalKembali = dialog.findViewById(R.id.tv_detilTanggalKembali);
                chipDetailStatus = dialog.findViewById(R.id.chip_detailStatus);

                tvNamaMobil = dialog.findViewById(R.id.tv_detilNamaMobil);
                tvNoPlat = dialog.findViewById(R.id.tv_detilNoPlat);
                ivGambarMobil = dialog.findViewById(R.id.iv_detilGambarMobil);

                tvTotalBiayaDriver = dialog.findViewById(R.id.tv_detilTotalBiayaDriver);
                chipDetailStatusPembayaran = dialog.findViewById(R.id.chip2_detailStatusPembayaran);

                tvDetailIdTransaksi.setText(transaksi.getIdTransaksi());
                tvTanggalTransaksi.setText(transaksi.getTanggalTransaksi());
                tvDetailTanggalSewa.setText(transaksi.getTanggalWaktuSewa());
                tvDetailTanggalSelesai.setText(transaksi.getTanggalWaktuSelesai());
                chipDetailStatus.setText(transaksi.getStatusTransaksi());
                if(transaksi.getStatusTransaksi().equals("Selesai")){
                    tvTanggalKembali.setText(transaksi.getTanggalWaktuKembali());
                    chipDetailStatus.setChipBackgroundColorResource(R.color.selesai);
                }else if(transaksi.getStatusTransaksi().equals("Menunggu Konfirmasi")){
                    tvTanggalKembali.setText("Belum Kembali");
                    chipDetailStatus.setChipBackgroundColorResource(R.color.menungguKonfirmasi);
                }else if(transaksi.getStatusTransaksi().equals("Diterima")){
                    tvTanggalKembali.setText("Belum Kembali");
                    chipDetailStatus.setChipBackgroundColorResource(R.color.selesai);
                }else if(transaksi.getStatusTransaksi().equals("Ditolak") || transaksi.getStatusTransaksi().equals("Transaksi Ditolak") ){
                    tvTanggalKembali.setText("-");
                    chipDetailStatus.setChipBackgroundColorResource(R.color.ditolak);
                }else if(transaksi.getStatusTransaksi().equals("Batal")){
                    tvTanggalKembali.setText("-");
                    chipDetailStatus.setChipBackgroundColorResource(R.color.black);
                }else if(transaksi.getStatusTransaksi().equals("Peminjaman Berlangsung")){
                    tvTanggalKembali.setText("Belum Kembali");
                    chipDetailStatus.setChipBackgroundColorResource(R.color.pinjam);
                }

                tvNamaMobil.setText(transaksi.getNamaMobil());
                tvNoPlat.setText(transaksi.getPlatNomor());
                Glide.with(v.getContext())
                        .load(TransaksiAPI.GET_PICTURE + transaksi.getGambarMobil())
                        .placeholder(R.drawable.camera)
                        .into(ivGambarMobil);

                tvTotalBiayaDriver.setText(" " + rupiahFormat.format(transaksi.getTotalBiayaDriver()));
                if(transaksi.getStatusPembayaran() == 0){
                    chipDetailStatusPembayaran.setText("Belum Lunas");
                    chipDetailStatusPembayaran.setChipBackgroundColorResource(R.color.ditolak);
                }else{
                    chipDetailStatusPembayaran.setText("Lunas");
                    chipDetailStatusPembayaran.setChipBackgroundColorResource(R.color.selesai);
                }

                if(transaksi.getRateDriver() == null){
                    ratingDriver.setRating(0);
                }else{
                    ratingDriver.setRating(transaksi.getRateDriver());
                }
                if(transaksi.getPerformaDriver() == null){
                    performaDriver.setText("Belum Ada");
                }else{
                    performaDriver.setText(transaksi.getPerformaDriver());
                }

                builder.setView(dialog).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog newDialog = builder.create();
                newDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }
}
