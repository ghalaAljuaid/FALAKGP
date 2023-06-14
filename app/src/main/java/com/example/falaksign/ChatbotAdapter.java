package com.example.falaksign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatbotAdapter extends RecyclerView.Adapter<ChatbotAdapter.ViewHolder> {

    public static final int MSG_TYPE_QUESTION = 0;
    public static final int MSG_TYPE_ANSWER = 1;

    private Context context;
    private List<ChatBotItem> chatBotList;

    int viewType2;

    public ChatbotAdapter(Context context, List<ChatBotItem> chatBotList) {
        this.context = context;
        this.chatBotList = chatBotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewType2 = viewType;
        View view;
        if (viewType == MSG_TYPE_QUESTION) {
            view = LayoutInflater.from(context).inflate(R.layout.bot_msg_rv_item, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.user_msg_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ChatBotItem item = chatBotList.get(position);

    holder.messageChatbotTV.setText(item.getMessage());

    }

    @Override
    public int getItemCount() {
        return chatBotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView messageChatbotTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messageChatbotTV = itemView.findViewById(R.id.messageChatbotTV);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (chatBotList.get(position).getType().equals("Question")) {
            return MSG_TYPE_QUESTION;
        } else {
            return MSG_TYPE_ANSWER;
        }
    }

    public void filterList(List<ChatBotItem> chatBotItemList) {
        chatBotList = chatBotItemList;
        notifyDataSetChanged();
    }

}
