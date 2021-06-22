package com.arimbimega.chatapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arimbimega.chatapp.Model.Message;
import com.arimbimega.chatapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ListViewHolder> {

    ArrayList<Message> messageArrayList;

    public MessageAdapter(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.ListViewHolder holder, int position) {

        Message message = messageArrayList.get(position);
        holder.showMessage.setText(message.getMessage());
        holder.showTime.setText(message.getTime_stamp());

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView showMessage,showTime;

        public ListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            showMessage = itemView.findViewById(R.id.show_message);
            showTime = itemView.findViewById(R.id.show_time);
        }
    }


}
