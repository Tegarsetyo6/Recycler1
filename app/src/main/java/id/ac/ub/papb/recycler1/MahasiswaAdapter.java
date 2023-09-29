package id.ac.ub.papb.recycler1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    LayoutInflater inflater;
    Context _context;
    ArrayList<Mahasiswa> data;

    public MahasiswaAdapter(Context _context, ArrayList<Mahasiswa> data) {
        this._context = _context;
        this.data = data;
        this.inflater = LayoutInflater.from(this._context);
    }


    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mhs = data.get(position);
        Log.d(MainActivity.TAG,"data "+position);
        Log.d(MainActivity.TAG,"nim "+mhs.nim);
        Log.d(MainActivity.TAG,"nama "+mhs.nama);

        holder.tvNim.setText(mhs.nim);
        holder.tvNama.setText(mhs.nama);

        final int itemPosition = position;


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(_context);
                builder.setTitle("Konfirmasi Hapus");
                builder.setMessage("Anda yakin ingin menghapus data ini?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            data.remove(adapterPosition);
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(MainActivity.TAG,"Jumlah data "+data.size());
        return data.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim;
        TextView tvNama;
        MaterialButton btnDelete;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
            btnDelete = itemView.findViewById(R.id.btnDelete); // Menghubungkan dengan tombol delete di layout row.xml
        }
    }
}