package com.developersths.notes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Notes> notes_list;

    public RecyclerViewAdapter(Context context, ArrayList<Notes> notes_list) {
        this.context = context;
        this.notes_list = notes_list;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_bg,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Notes notes = notes_list.get(position);
        holder.title_text.setText(notes.getTitle());
        holder.date_text.setText(notes.getDate());
        holder.time_text.setText(notes.getTime());

        Random random = new Random();
        int i = random.nextInt(9);
        switch (i){
            case 1:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_1));
                break;
            case 2:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_2));
                break;
            case 3:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_3));
                break;
            case 4:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_4));
                break;
            case 5:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_5));
                break;
            case 6:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_6));
                break;
            case 7:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_7));
                break;
            case 8:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_8));
                break;
            case 0:
                holder.card_bg.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color_5));
                break;
        }

        holder.dlt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete Note")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.dlt_click));
                                dbHandler db = new dbHandler(context);
                                db.dlt_note(notes.get_id());
                                notes_list.remove(notes_list.get(holder.getAdapterPosition()));
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();

                builder.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title_text,date_text,time_text;
        private CardView card_bg;
        private ImageView dlt_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_text = itemView.findViewById(R.id.title_text);
            date_text = itemView.findViewById(R.id.date_text);
            time_text = itemView.findViewById(R.id.time_text);
            card_bg = itemView.findViewById(R.id.card_bg);
            dlt_btn = itemView.findViewById(R.id.dlt_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.dlt_click));
                    int position = getAdapterPosition();
                    Notes note = notes_list.get(position);
                    Intent intent = new Intent(context, Note_details.class);
                    intent.putExtra(params.KEY_ID, note.get_id());
                    intent.putExtra(params.KEY_TITLE, note.getTitle());
                    intent.putExtra(params.KEY_CONTENT, note.getContent());
                    context.startActivity(intent);
                }
            });

        }
    }
}
