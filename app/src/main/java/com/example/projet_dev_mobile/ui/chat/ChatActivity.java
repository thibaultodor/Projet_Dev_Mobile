package com.example.projet_dev_mobile.ui.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.BDD.DataBase;
import com.example.projet_dev_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<String> messages_user;
    private List<String> messages_sender;
    private List<String> messagelist;
    private  TestChatAdapter testChatAdapter;
    private ChatAdapter_User chatAdapterUser;
    private ChatAdapter_Sender chatAdapterSender;
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat_layout);

        messagelist = new ArrayList<>();
        testChatAdapter = new TestChatAdapter(messagelist);

        /*
        messages_user = new ArrayList<>();
        messages_sender = new ArrayList<>();
        chatAdapterUser = new ChatAdapter_User(messages_user);
        chatAdapterSender = new ChatAdapter_Sender(messages_sender);
         */

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> message = new ArrayList<>();
        ArrayList<Boolean> isUserSend = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        int id1 = extras.getInt("iduser1");
        int id2 = extras.getInt("iduser2");
        int idConv = DataBase.Conversation.getConversations().GetConversation(id1,id2,message,isUserSend);

        testChatAdapter.setUserSend(isUserSend);

        for (int i = 0; i < message.size(); i++) {
            chatRecyclerView.setAdapter(testChatAdapter);
            messagelist.add(message.get(i));
            testChatAdapter.notifyItemInserted(messagelist.size() - 1);
        }

        /*
        if(!conversation.noMessages()){
            ArrayList<String> message = new ArrayList<>();
            ArrayList<Boolean> isUserSend = new ArrayList<>();

            Bundle extras = getIntent().getExtras();

            testChatAdapter.setUserSend(isUserSend);
            //
            for (int i = 0; i < message.size(); i++) {
                if(isUserSend.get(i)){
                    chatRecyclerView.setAdapter(chatAdapterUser);
                    messages_user.add(message.get(i));
                    chatAdapterUser.notifyItemInserted(messages_user.size() - 1);
                }else{
                    chatRecyclerView.setAdapter(chatAdapterSender);
                    messages_sender.add(message.get(i));
                    chatAdapterSender.notifyItemInserted(messages_sender.size() - 1);
                }
            }
            //

            for (int i = 0; i < message.size(); i++) {
                chatRecyclerView.setAdapter(testChatAdapter);
                messagelist.add(message.get(i));
                testChatAdapter.notifyItemInserted(messagelist.size() - 1);
            }
        }

         */

        //chatRecyclerView.setAdapter(chatAdapterUser);
        //chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    messagelist.add(message);
                    DataBase.Conversation.getConversations().Add(idConv,message,id1);
                    DataBase d = DataBase.getInstance();
                    d.setJSonConversation(getApplicationContext());
                    testChatAdapter.newMsg();
                    testChatAdapter.notifyItemInserted(messagelist.size() - 1);
                    messageEditText.setText("");
                    chatRecyclerView.smoothScrollToPosition(messagelist.size() - 1);
                }
            }
        });
    }
}
