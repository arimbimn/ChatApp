package com.arimbimega.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arimbimega.chatapp.Model.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView show_message;
    Button btn_send;


    private static final String TAG = "MainActivity";
    DatabaseReference mDatabaseReference;
    public ArrayList<Message> messageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        show_message = findViewById(R.id.tv_pesan_masuk);
        btn_send = findViewById(R.id.btn_send_testing);

        //manggil realtime database
        mDatabaseReference = FirebaseDatabase.getInstance("https://chatapp-ee6e5-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "Failed Get Token: ", task.getException());
                            return;
                        }

                        //Get new FCM Token
                        String token = task.getResult();
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                    }
                });

        String topic = "news";
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if ( !task.isSuccessful()) {
                            Log.d(TAG, "Failed to subscribe topic: " + topic + task.getException());
                            return;
                        }

                        String msg = "Success to subscribe " + topic;
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToDatabase();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToDatabase();
            }
        });

        readFromDatabase();

    }

    public void sendMessageToDatabase() {

        DatabaseReference myRef = mDatabaseReference.child("Chats");

        //tanggal
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date_format = dateFormat.format(date);

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("sender", "Pradyanti");
        hashMap.put("receiver", "Arimbi");
        hashMap.put("message", "pesan masuk");
        hashMap.put("time_stamp", date_format);

        myRef.push().setValue(hashMap);

    }

    public void readFromDatabase() {

        Query messageQuery = mDatabaseReference.child("Chats").orderByChild("time_stamp");

        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                Message message = snapshot.getValue(Message.class);
                Log.d(TAG, "onChildAdded: " + message.getTime_stamp());
                Log.d(TAG, "onChildAdded: " + message.getSender());
                Log.d(TAG, "onChildAdded: " + message.getReceiver());
                Log.d(TAG, "onChildAdded: " + message.getMessage());

                messageArrayList.add(message);

                String pesan = "";
                for ( int i = 0; i < messageArrayList.size(); i ++) {
                    pesan += messageArrayList.get(i).getMessage() + " " + messageArrayList.get(i).getTime_stamp() + "\n";
                }

                show_message.setText(pesan);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}