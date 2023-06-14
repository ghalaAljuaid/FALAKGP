package com.example.falaksign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<PostItem> postItemList;
    List<User> userList;
    public static PostItem postItem;

    public PostAdapter(Context context, List<PostItem> postItemList, List<User> userList) {
        this.context = context;
        this.postItemList = postItemList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostItem item = postItemList.get(position);

        for (User user : userList) {
            if (user.getId().equals(item.getId())) {
                holder.usernameTV.setText(user.getFname() + " " + user.getLname());
                break;
            }
        }

        Picasso.get().load(item.getImage()).into(holder.postImage);

        holder.likeNumberTV.setText(item.getLikeNumber() + "");
        holder.commentNumberTV.setText(item.getCommentNumber() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postItem = item;
                context.startActivity(new Intent(context, viewpost.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView postImage;
        TextView usernameTV, likeNumberTV,
                commentNumberTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.imageView4);
            usernameTV = itemView.findViewById(R.id.textView13);
            likeNumberTV = itemView.findViewById(R.id.textView12);
            commentNumberTV = itemView.findViewById(R.id.textView11);

        }
    }
}
