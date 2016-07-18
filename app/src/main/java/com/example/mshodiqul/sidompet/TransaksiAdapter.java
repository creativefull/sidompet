package com.example.mshodiqul.sidompet;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mshodiqul on 7/16/16.
 */
public class TransaksiAdapter extends ArrayAdapter<HashMap<String, String>> {
    private final ArrayList data;

    public TransaksiAdapter(Context context, ArrayList<HashMap<String, String>> trxList) {
        super(context, 0, trxList);
        data = new ArrayList();
        data.addAll(trxList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<String, String> tr = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_images, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        int bg = R.drawable.bg_green;
//        if (tr.get("type").equals("Pemasukan")) {
//            RelativeLayout drawerList = (RelativeLayout) convertView.findViewById(R.id.layout_row_images);
//            drawerList.setBackgroundResource(bg);
//        }

        TextView id = (TextView) convertView.findViewById(R.id.id_trx);
        TextView tglTrx = (TextView) convertView.findViewById(R.id.tglTrx);

        title.setText(tr.get("keterangan").toString().toUpperCase());
        Double am = Double.parseDouble(tr.get("amount"));
        amount.setText("Rp " + String.format("%,.2f", am));
        id.setText(tr.get("id"));
        tglTrx.setText(tr.get("date"));

        return convertView;
    }
}
