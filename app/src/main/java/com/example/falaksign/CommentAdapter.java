package com.example.falaksign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    List<Comment> commentList;
    List<User> userList;

    public CommentAdapter(Context context, List<Comment> commentList, List<User> userList) {
        this.context = context;
        this.commentList = commentList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);

        for (User user : userList) {
            if (user.getId().equals(comment.getUserId())) {
                holder.userName.setText(user.getFname() + " " + user.getLname());
                if (user.getImage().isEmpty() || user.getImage() == null) {
                    holder.userImage.setImageResource(R.drawable.proicon);
                } else {
                    Picasso.get().load(user.getImage()).into(holder.userImage);
                }
                break;
            }
        }

        holder.userComment.setText(comment.getComment() + "");


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView userImage;
        TextView userName, userComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userComment = itemView.findViewById(R.id.userComment);

        }
    }
}
