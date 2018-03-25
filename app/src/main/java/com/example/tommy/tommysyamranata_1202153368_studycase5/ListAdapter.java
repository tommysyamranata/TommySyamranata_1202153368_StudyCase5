package com.example.tommy.tommysyamranata_1202153368_studycase5;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    //deklarasi variable
    private Context cntx;
    private List<ListItem> list;
    int color;

    //konstruktor yg digunakan
    public ListAdapter(Context cntx, List<ListItem> list, int color) {
        this.cntx = cntx;
        this.list = list;
        this.color = color;
    }

    //menentukan viewholder pada recyclerview
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(cntx).inflate(R.layout.list_item, parent, false);
        ViewHolder hldr = new ViewHolder(view);
        return hldr;
    }

    //melakukan setting terhadap nilai
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(cntx.getResources().getColor(this.color));
    }

    //mengambil size list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mengambil posisi
    public ListItem getData(int position){
        return list.get(position);
    }

    //untuk mendelete data
    public void deleteData(int i){
        //melakukan remove terhadap data yg terpilih
        list.remove(i);
        //membuat notify untuk data yg diremove
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //deklarasi variabel data
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public ViewHolder(View itemView){
            super(itemView);

            //mengakses id text view di layout dan Cardview
            ToDo = itemView.findViewById(R.id.tdText);
            Description = itemView.findViewById(R.id.DescText);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.card);
        }
    }
}
