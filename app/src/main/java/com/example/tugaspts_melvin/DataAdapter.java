package com.example.tugaspts_melvin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<com.example.tugaspts_melvin.DataAdapter.ViewHolder> {

    private List<DataClass> dataList;
    private ViewHolder.OnNoteListener mOnNoteListener;
    public DataAdapter (List<DataClass>dataList, ViewHolder.OnNoteListener onNoteListener){ this .dataList=dataList;
    this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public com.example.tugaspts_melvin.DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.tugaspts_melvin.DataAdapter.ViewHolder holder, int position) {
        int res_icon= dataList.get(position).getIcon();
        String res_nama= dataList.get(position).getNama();
        String res_role = dataList.get(position).getRole();

        holder.setData(res_icon,res_nama,res_role);

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView textView;
        private TextView textviewrole;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.icon);
            textView=itemView.findViewById(R.id.nama);
            textviewrole=itemView.findViewById(R.id.role);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);


        }



        public void setData(int res_icon, String res_nama, String res_role) {

            imageView.setImageResource(res_icon);
            textView.setText(res_nama);
            textviewrole.setText(res_role);
        }


        @Override
        public void onClick(View view) {
            textView=itemView.findViewById(R.id.nama);
            textviewrole=itemView.findViewById(R.id.role);
            int resid = R.drawable.avatar1;




        onNoteListener.onNoteClick(getAdapterPosition(), ""+textView.getText(), ""+textviewrole.getText(), resid);
        }

        public interface  OnNoteListener{
            void onNoteClick(int position, String name, String role, int profile);


        }


    }
}
