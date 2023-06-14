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

public class Sun extends _SwipeActivityClass {

    private ImageView mSunGif;

    private boolean isOpen = false;
    private FrameLayout mFragmentContainer;
    private SunFragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sun);

        showToast();

        mFragmentContainer = (FrameLayout) findViewById(R.id.sun_fragment_container);

        mSunGif = (ImageView) findViewById(R.id.sun_button);
        Glide.with(this).asGif().load(R.drawable.sun2).into(mSunGif);//library to use the gif
        mSunGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    new ShowDialog(Sun.this).showSunDialog();

                    /*
                    String sunString = getResources().getString(R.string.sun); //gets the string from the strings.xml
                    openFragment(sunString, R.string.sun);

                     */
                } else {
                    closeFragment();
                }
            }
        });
    }

    private void openFragment(String text, int ID) {
        isOpen = true;
        fragment = SunFragment.newInstance(text);
        addText(ID, fragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_from_bottom, R.anim.earth_to_bottom);//in order to add a button or something than add both again
        transaction.addToBackStack(null);//in order to close only the fragment and not the whole activity
        transaction.add(R.id.sun_fragment_container, fragment, "fragment_sun").commit();
    }

    private void closeFragment() {
        isOpen = false;
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_to_bottom, R.anim.earth_from_bottom);
        transaction.remove(fragment).commit();
    }

    private void addText(int ID, SunFragment fragment) {
        fragment.setmTextView(ID);
    }

    @Override
    protected void onSwipeRight() {
        openPluto();
    }

    @Override
    protected void onSwipeLeft() {
        openMercury();
    }

    private void openPluto(){
        Intent intent = new Intent(this, Pluto.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_out,R.anim.push_right_in);
    }

    private void openMercury(){
        Intent intent = new Intent(this, Mercury.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    private void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Sun");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void showDialogStarsOnClick(View view) {
        new ShowDialog(Sun.this).showStarsDialog();
    }

}