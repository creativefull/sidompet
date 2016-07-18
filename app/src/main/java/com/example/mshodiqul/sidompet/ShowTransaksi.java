package com.example.mshodiqul.sidompet;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mshodiqul on 7/16/16.
 */
public class ShowTransaksi extends DialogFragment {
    private String keterangan;
    private String amount;
    private String tanggal;
    public ShowTransaksi() {

    }
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        keterangan = getArguments().getString("keterangan");
        amount = getArguments().getString("amount");
        tanggal = getArguments().getString("tanggal");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_transaksi, container);
        TextView show_keterangan = (TextView) view.findViewById(R.id.show_keterangan);
        TextView show_amount = (TextView) view.findViewById(R.id.show_amount);
        TextView show_tanggal = (TextView) view.findViewById(R.id.show_tanggal);

        show_keterangan.setText(keterangan);
        show_amount.setText(amount);
        show_tanggal.setText(tanggal);

        getDialog().setTitle("Lihat Transaksi");

        return view;
    }
}
