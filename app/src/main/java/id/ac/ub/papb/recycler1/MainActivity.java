package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    EditText etNim;
    EditText etNama;
    Button bt1;

    public static String TAG = "RV1";

    private ArrayList<Mahasiswa> data;
    private MahasiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);
        bt1 = findViewById(R.id.bt1);


        data = getData();
        adapter = new MahasiswaAdapter(this, data);

        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nimInput = etNim.getText().toString();
                String namaInput = etNama.getText().toString();


                if (!nimInput.isEmpty() && !namaInput.isEmpty()) {

                    Mahasiswa mahasiswaBaru = new Mahasiswa();
                    mahasiswaBaru.nim = nimInput;
                    mahasiswaBaru.nama = namaInput;


                    data.add(mahasiswaBaru);


                    adapter.notifyDataSetChanged();


                    etNim.getText().clear();
                    etNama.getText().clear();


                    Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity.this, "NIM dan nama harus diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public ArrayList<Mahasiswa> getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG,"getData "+mhs.nim);
            data.add(mhs);
        }
        return data;
    }
}
