package com.example.falaksign.Planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.falaksign.R;
import com.example.falaksign.ShowDialog;

public class Jupiter extends _SwipeActivityClass {

    private FrameLayout mFragmentContainer;

    private boolean isOpen = false;

    private JupiterFragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private ImageView mJupiterView;
    private ImageView mEuropaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jupiter);

        showToast();

        mFragmentContainer = (FrameLayout) findViewById(R.id.jupiter_fragment_container);

        mJupiterView = (ImageView) findViewById(R.id.jupiter_button);
        Glide.with(this).asGif().load(R.drawable.jupiter).into(mJupiterView);//library to use the gif
        mJupiterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen) {
                    new ShowDialog(Jupiter.this).showPlanetsDialog();

                    /*
                    String jupiterString = getResources().getString(R.string.jupiter); //gets the string from the strings.xml
                    openFragment(jupiterString, R.string.jupiter);

                     */
                }else{
                    closeFragment();
                }
            }
        });

        mEuropaView = (ImageView) findViewById(R.id.europa_button);
        Glide.with(this).asGif().load(R.drawable.eeuropa2).into(mEuropaView);//library to use the gif
        mEuropaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen) {
                    new ShowDialog(Jupiter.this).showPlanetsDialog();

                    /*
                    String europaString = getResources().getString(R.string.europa); //gets the string from the strings.xml
                    openFragment(europaString, R.string.europa);

                     */
                }else{
                    closeFragment();
                }
            }
        });

    }

    private void openFragment(String text, int ID) {
        isOpen = true;
        fragment = JupiterFragment.newInstance(text);
        addText(ID, fragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_from_bottom, R.anim.earth_to_bottom);//in order to add a button or something than add both again
        transaction.addToBackStack(null);//in order to close only the fragment and not the whole activity
        transaction.add(R.id.jupiter_fragment_container, fragment, "fragment_jupiter").commit();
    }

    private void closeFragment() {
        isOpen = false;
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_to_bottom, R.anim.earth_from_bottom);
        transaction.remove(fragment).commit();
    }

    private void addText(int ID, JupiterFragment fragment) {
        fragment.setmTextView(ID);
    }

    @Override
    protected void onSwipeRight() {
        openMars();
    }

    @Override
    protected void onSwipeLeft() {
        openSaturn();
    }

    private void openMars(){
        Intent intent = new Intent(this, Mars.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_out,R.anim.push_right_in);
    }

    private void openSaturn(){
        Intent intent = new Intent(this, Saturn.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    private void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Jupiter");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void showDialogStarsOnClick(View view) {
        new ShowDialog(Jupiter.this).showStarsDialog();
    }

}