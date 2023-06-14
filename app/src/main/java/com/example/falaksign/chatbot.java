package com.example.falaksign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chatbot extends AppCompatActivity {

    private ImageButton image;
    FloatingActionButton idFABSend;
    EditText idEditMessage;

    List<Question> dataList = new ArrayList<>();
    List<ChatBotItem> chatBotItemList = new ArrayList<>();
    boolean questionStatus;
    String answers = "";
    ChatbotAdapter adapter;

    RecyclerView chatbotRecyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        chatbotRecyclerView = findViewById(R.id.chatbotRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        chatbotRecyclerView.setLayoutManager(manager);

        adapter = new ChatbotAdapter(chatbot.this, chatBotItemList);
        chatbotRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getQuestionsAndAnswers();

        idEditMessage = findViewById(R.id.idEditMessage);

        image = (ImageButton) findViewById(R.id.imageButtonback2);
        image.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openHome();
            }
        });

        idFABSend = findViewById(R.id.idFABSend);
        idFABSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = idEditMessage.getText().toString().trim();
                if (!message.isEmpty() && message != null) {
                    idEditMessage.setText("");
                    sendMessage(message);
                } else {
                    Toast.makeText(chatbot.this, "Enter Your Question", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getQuestionsAndAnswers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Question");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    dataList.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendMessage(String message) {

        chatBotItemList.add(new ChatBotItem(message, "Question"));
        adapter.filterList(chatBotItemList);

        for (Question question : dataList) {
            if (question.getQuestion().toLowerCase().contains(message.toLowerCase())) {
                answers = question.getAnswer();
                questionStatus = true;
                break;
            } else {
                questionStatus = false;
            }
        }

        if (questionStatus) {
            chatBotItemList.add(new ChatBotItem(answers, "Answer"));
            chatbotRecyclerView.getLayoutManager().scrollToPosition(chatBotItemList.size() - 1);
            adapter.filterList(chatBotItemList);
        } else {
            chatBotItemList.add(new ChatBotItem("أعتذر للغاية, ولكنني لا اعلم ماهو الجواب حتى الان!", "Answer"));
            chatbotRecyclerView.getLayoutManager().scrollToPosition(chatBotItemList.size() - 1);
            adapter.filterList(chatBotItemList);
        }

    }

    public void openHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

}