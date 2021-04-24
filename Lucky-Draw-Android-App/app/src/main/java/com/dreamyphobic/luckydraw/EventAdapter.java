package com.dreamyphobic.luckydraw;

/*
  Created by Harsh Gupta on 24/04/2021.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dreamyphobic.luckydraw.models.EventModel;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ItemViewHolder> {
    private final ArrayList<EventModel> dataList;
    private final LayoutInflater mInflater;
    private final Context context;
    private EventClickListener clicklistener = null;

    public EventAdapter(Context ctx, ArrayList<EventModel> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView time;
        private final TextView reward;
        private final TextView winner;
        private final ImageView image;
        private final Button join;

        public ItemViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            reward = (TextView) itemView.findViewById(R.id.reward);
            join = (Button) itemView.findViewById(R.id.join);
            image = (ImageView) itemView.findViewById(R.id.image1);
            winner = (TextView) itemView.findViewById(R.id.winner);
        }

        public void bind(EventModel eventModel){
            if(eventModel.getStatus()==0){
                winner.setVisibility(View.GONE);
                join.setVisibility(View.VISIBLE);
                image.setImageResource(R.drawable.lucky_draw);
            }
            else{
                winner.setVisibility(View.VISIBLE);
                join.setVisibility(View.GONE);
                image.setImageResource(R.drawable.trophy);
                winner.setText("Winner: "+eventModel.getWinner().getName());
            }
            reward.setText(eventModel.getReward());
            time.setText(String.valueOf(eventModel.getTime()));
            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicklistener != null) {
                        clicklistener.itemClicked(v, eventModel);
                    }
                }
            });

        }
    }

    public void setClickListener(EventClickListener listener) {
        this.clicklistener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event2, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
