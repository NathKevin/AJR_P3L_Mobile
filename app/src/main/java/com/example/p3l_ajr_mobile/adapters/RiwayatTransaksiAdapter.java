package com.example.p3l_ajr_mobile.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p3l_ajr_mobile.LoginActivity;
import com.example.p3l_ajr_mobile.MainActivity;
import com.example.p3l_ajr_mobile.R;
import com.example.p3l_ajr_mobile.RateDriverActivity;
import com.example.p3l_ajr_mobile.RiwayatActivity;
import com.example.p3l_ajr_mobile.apis.MobilAPI;
import com.example.p3l_ajr_mobile.apis.TransaksiAPI;
import com.example.p3l_ajr_mobile.models.Transaksi;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class RiwayatTransaksiAdapter extends RecyclerView.Adapter<RiwayatTransaksiAdapter.ViewHolder>{
    private List<Transaksi> transaksiList;
    private Context context;

    public RiwayatTransaksiAdapter(List<Transaksi> transaksiList, Context context) {
        this.transaksiList = transaksiList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdTransaksi, tvTanggalSewa, tvTanggalSelesai;
        Chip chipStatus;
        Button btnRate;

        MaterialCardView cvTransaksi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdTransaksi = itemView.findViewById(R.id.tv_idTransaksi);
            tvTanggalSewa = itemView.findViewById(R.id.tv_tanggalSewa);
            tvTanggalSelesai = itemView.findViewById(R.id.tv_tanggalSelesai);
            chipStatus = itemView.findViewById(R.id.chip_status);
            btnRate = itemView.findViewById(R.id.btn_rateDriver);

            cvTransaksi = itemView.findViewById(R.id.cv_transaksi_item);
        }
    }

    @NonNull
    @Override
    public RiwayatTransaksiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_transaksi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTransaksiAdapter.ViewHolder holder, int position) {
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

        if(transaksi.getStatusTransaksi().equals("Selesai")){
            if(transaksi.getIdDriver() != null){
                holder.btnRate.setVisibility(View.VISIBLE);
                if(transaksi.getRateDriver() == null){
                    holder.btnRate.setClickable(true);
                    holder.btnRate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent moveRate = new Intent(context, RateDriverActivity.class);
                            moveRate.putExtra("data", transaksi);
                            context.startActivity(moveRate);
                        }
                    });
                }else{
                    holder.btnRate.setClickable(false);
                    holder.btnRate.setText("Done");
                }
            }else{
                holder.btnRate.setVisibility(View.GONE);
            }
        }else{
            holder.btnRate.setVisibility(View.GONE);
        }

        holder.cvTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View dialog = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_detil_transaksi, null);

                DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                        .getCurrencyInstance(new Locale("in", "ID"));

                //Transaksi
                TextView tvTanggalKembali, tvTanggalTransaksi, tvDetailIdTransaksi, tvDetailTanggalSewa, tvDetailTanggalSelesai;
                Chip chipDetailStatus;
                //Mobil
                TextView tvNamaMobil, tvNoPlat, tvHargaSewaMobil;
                ImageView ivGambarMobil;
                //Driver
                TextView tvNamaDriver, tvHargaSewaDriver;
                ImageView ivGambarDriver;
                RatingBar rateDriver;
                //Pembayaran
                TextView tvMetodePembayaran, tvJenisPromo, tvTotalBiayaMobil, tvTotalBiayaDriver, tvTotalPromo, tvBesarPromo, tvDendaPeminjaman, tvTotalBiaya;
                Chip chipDetailStatusPembayaran;

                LinearLayout hiddenDriver;

                tvDetailIdTransaksi = dialog.findViewById(R.id.tv_detailIdTransaksi);
                tvDetailTanggalSewa = dialog.findViewById(R.id.tv_detilTanggalSewa);
                tvDetailTanggalSelesai = dialog.findViewById(R.id.tv_detilTanggalSelesai);
                tvTanggalTransaksi = dialog.findViewById(R.id.tv_detailTanggalTransaksi);
                tvTanggalKembali = dialog.findViewById(R.id.tv_detilTanggalKembali);
                chipDetailStatus = dialog.findViewById(R.id.chip_detailStatus);

                tvNamaMobil = dialog.findViewById(R.id.tv_detilNamaMobil);
                tvNoPlat = dialog.findViewById(R.id.tv_detilNoPlat);
                tvHargaSewaMobil = dialog.findViewById(R.id.tv_detilHargaSewaMobil);
                ivGambarMobil = dialog.findViewById(R.id.iv_detilGambarMobil);

                tvNamaDriver = dialog.findViewById(R.id.tv_detilNamaDriver);
                tvHargaSewaDriver = dialog.findViewById(R.id.tv_detilHargaSewaDriver);
                ivGambarDriver = dialog.findViewById(R.id.iv_detilGambarDriver);
                rateDriver = dialog.findViewById(R.id.rating_detailDriver);

                tvMetodePembayaran = dialog.findViewById(R.id.tv_detilMetodePembayaran);
                tvJenisPromo = dialog.findViewById(R.id.tv_detilJenisPromo);
                tvTotalBiayaMobil = dialog.findViewById(R.id.tv_detilTotalBiayaMobil);
                tvTotalBiayaDriver = dialog.findViewById(R.id.tv_detilTotalBiayaDriver);
                tvTotalPromo = dialog.findViewById(R.id.tv_detilTotalPromo);
                tvDendaPeminjaman = dialog.findViewById(R.id.tv_detilDendaPeminjaman);
                tvBesarPromo = dialog.findViewById(R.id.tv_detilBesarPromo);
                tvTotalBiaya = dialog.findViewById(R.id.tv_detilTotalBiaya);
                chipDetailStatusPembayaran = dialog.findViewById(R.id.chip_detailStatusPembayaran);

                hiddenDriver = dialog.findViewById(R.id.linearDetil2);

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
                tvHargaSewaMobil.setText(" " + rupiahFormat.format(transaksi.getHargaSewaMobil()));
                Glide.with(v.getContext())
                        .load(TransaksiAPI.GET_PICTURE + transaksi.getGambarMobil())
                        .placeholder(R.drawable.camera)
                        .into(ivGambarMobil);

                if(transaksi.getIdDriver() != null){
                    tvNamaDriver.setText(transaksi.getNamaDriver());
                    tvHargaSewaDriver.setText(" " + rupiahFormat.format(transaksi.getHargaSewaDriver()));
                    Glide.with(v.getContext())
                            .load(TransaksiAPI.GET_PICTURE + transaksi.getFotoDriver())
                            .placeholder(R.drawable.camera)
                            .into(ivGambarDriver);
                    if(transaksi.getRateDriver() == null){
                        rateDriver.setRating(0);
                    }else{
                        rateDriver.setRating(transaksi.getRateDriver());
                    }
                    hiddenDriver.setVisibility(View.VISIBLE);
                }else{
                    hiddenDriver.setVisibility(View.GONE);
                }

                tvMetodePembayaran.setText(transaksi.getMetodePembayaran());
                if(transaksi.getIdPromo() != null){
                    tvJenisPromo.setText(transaksi.getJenisPromo());
                    Float temp = transaksi.getBesarPromo() * 100;
                    Integer temp2 = Math.round(temp);
                    tvBesarPromo.setText(temp2 + "%");
                }else{
                    tvJenisPromo.setText("Tidak Pakai");
                    tvBesarPromo.setText("0%");
                }
                tvTotalBiayaMobil.setText(" " + rupiahFormat.format(transaksi.getTotalBiayaMobil()));
                tvTotalBiayaDriver.setText(" " + rupiahFormat.format(transaksi.getTotalBiayaDriver()));
                tvTotalPromo.setText(" " + rupiahFormat.format(transaksi.getTotalPromo()));
                tvDendaPeminjaman.setText(" " + rupiahFormat.format(transaksi.getDendaPeminjaman()));
                tvTotalBiaya.setText(" " + rupiahFormat.format(transaksi.getTotalBiaya()));
                if(transaksi.getStatusPembayaran() == 0){
                    chipDetailStatusPembayaran.setText("Belum Lunas");
                    chipDetailStatusPembayaran.setChipBackgroundColorResource(R.color.ditolak);
                }else{
                    chipDetailStatusPembayaran.setText("Lunas");
                    chipDetailStatusPembayaran.setChipBackgroundColorResource(R.color.selesai);
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
