package com.example.myprozhepayanterm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<RecyclerItem> listItem;
    private Context mcontext;
    public MyAdapter(List<RecyclerItem> listItem,Context mcontext) {
        this.mcontext = mcontext;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RecyclerItem item = listItem.get(position);
        holder.txt.setText(item.getName());
        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop  = new PopupMenu(mcontext,holder.txt1);
                pop.inflate(R.menu.menu_for_recyleview);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.delete_rec:

                                // برا حذف
listItem.remove(position);
notifyDataSetChanged();

                                break;
                                case R.id.edit_rec:

                                // برا حذف


                                break;

                        }
                        return false;
                    }
                });
                pop.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

ImageButton txt1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
            txt1 = itemView.findViewById(R.id.imageButton_rec);

        }
    }
}
