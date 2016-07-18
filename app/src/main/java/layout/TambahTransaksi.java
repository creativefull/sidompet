package layout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mshodiqul.sidompet.R;
import com.example.mshodiqul.sidompet.Transaksi;
import com.example.mshodiqul.sidompet.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class TambahTransaksi extends Fragment {
    EditText amountText;
    EditText keteranganText;
    Spinner typeElement;
    Transaksi transaksi;
    Transaksi dataTransaksi = new Transaksi();
    private final String dbName = "sidompet";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tambah_transaksi, container, false);
        // SET DATA TYPE TRANSAKSI
        Spinner spinner = (Spinner) rootView.findViewById(R.id.type_transaksi);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.array_type_trx, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        amountText = (EditText) rootView.findViewById(R.id.input_amount);
        keteranganText = (EditText) rootView.findViewById(R.id.input_keterangan);
        transaksi = new Transaksi(getActivity());
        typeElement = (Spinner) rootView.findViewById(R.id.type_transaksi);

        View btn = rootView.findViewById(R.id.btnSimpanTransaksi);
        View btnBack = rootView.findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keterangan = keteranganText.getText().toString();
                String type =typeElement.getSelectedItem().toString();

                if (keterangan.equals("") || amountText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Masih Ada Data Yang Kosong", Toast.LENGTH_LONG).show();
                }
                else {
                    Integer amount = Integer.parseInt(amountText.getText().toString());
                    dataTransaksi.keteragan = keterangan;
                    dataTransaksi.amount = amount;
                    dataTransaksi.type = type;
                    transaksi.insertTable(dataTransaksi);

                    User dataUser = new User(getActivity());
                    JSONObject user = dataUser.getUser();

                    Integer balance = 100;
                    try {
                        if (type.equals("Pengeluaran")) {
                            balance = user.getInt("amount") - amount;
                        }
                        else {
                            balance = amount + user.getInt("amount");
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Balance", balance.toString());
                    dataUser.updateBalance(balance);

                    amountText.setText("");
                    keteranganText.setText("");
                    amountText.requestFocus();
                    Toast.makeText(getActivity().getApplicationContext(), "Transaksi " + type + " Anda Sudah Disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DompetFragment df = new DompetFragment();
                ft.replace(R.id.frame_content, df);
                ft.commit();
            }
        });
        return rootView;
    }
}
