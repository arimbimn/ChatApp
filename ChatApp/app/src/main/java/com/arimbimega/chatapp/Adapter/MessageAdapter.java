package com.arimbimega.chatapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arimbimega.chatapp.Model.Message;
import com.arimbimega.chatapp.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ListViewHolder> {

    public static final int MSG_TYPE_lEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    ArrayList<Message> messageArrayList;
    String token;

    public MessageAdapter(ArrayList<Message> messageArrayList, String token) {
        this.messageArrayList = messageArrayList;
        this.token = token;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new ListViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new ListViewHolder(view);
        }
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

    @Override
    public int getItemViewType(int position) {
        if(messageArrayList.get(position).getSender().equals(token)){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_lEFT;
        }
    }
}
