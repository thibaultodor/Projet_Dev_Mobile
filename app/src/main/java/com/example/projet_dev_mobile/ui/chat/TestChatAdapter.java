package com.example.projet_dev_mobile.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_dev_mobile.R;

import java.util.List;

public class TestChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_1 = 1;
    private static final int VIEW_TYPE_2 = 2;

    private List<String> messages;

    private List<Boolean> isUser;

    public TestChatAdapter(List<String> messages) {
        this.messages = messages;
    }

    public void setUserSend(List<Boolean> isUser){
        this.isUser = isUser;
    }

    public void newMsg(){
        this.isUser.add(true);
    }

    // Implement necessary methods such as getItemCount(), getItemViewType(), onCreateViewHolder(), and onBindViewHolder()

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Return the view type based on position
        // Determine the view type based on the position or your own logic
        if (!isUser.get(position)) {
            return VIEW_TYPE_1;
        } else {
            return VIEW_TYPE_2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;

        if (viewType == VIEW_TYPE_1) {
            itemView = inflater.inflate(R.layout.chat_item_sender, parent, false);
            return new ViewHolderType1(itemView);
        } else if (viewType == VIEW_TYPE_2) {
            itemView = inflater.inflate(R.layout.chat_item_user, parent, false);
            return new ViewHolderType2(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String message = messages.get(position);
        if (holder instanceof ViewHolderType1) {
            ((ViewHolderType1) holder).messageTextViewSender.setText(message);
        } else if (holder instanceof ViewHolderType2) {
            ((ViewHolderType2) holder).messageTextViewUser.setText(message);
        }
    }

    // ViewHolder classes
    public class ViewHolderType1 extends RecyclerView.ViewHolder {
        // ViewHolderType1 elements
        public TextView messageTextViewSender;
        public ViewHolderType1(View itemView) {
            super(itemView);
            messageTextViewSender = itemView.findViewById(R.id.chatText_sender);
            // Initialize ViewHolderType1 elements
        }
    }

    public class ViewHolderType2 extends RecyclerView.ViewHolder {
        // ViewHolderType2 elements
        public TextView messageTextViewUser;
        public ViewHolderType2(View itemView) {
            super(itemView);
            messageTextViewUser = itemView.findViewById(R.id.chatText_user);
            // Initialize ViewHolderType2 elements
        }
    }
}

