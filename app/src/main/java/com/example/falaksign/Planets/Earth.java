package com.example.falaksign.Planets;


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


public class Earth extends _SwipeActivityClass {

    private ImageView mEarthView;
    private ImageView mMoonView;

    private boolean isOpen = false;
    private FrameLayout mFragmentContainer;
    private EarthFragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //next three lines are for setting full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_2);

        //call for custom toast method
        showToast();

        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        //bind mEarthView to its id in layout.xml
        mEarthView = (ImageView) findViewById(R.id.earth_button);
        Glide.with(this).asGif().load(R.drawable.earth_rotation3).into(mEarthView);//library to use the gif
        mEarthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {//if the fragment isn't open
                   new ShowDialog(Earth.this).showPlanetsDialog();

                    /*
                    String earthString = getResources().getString(R.string.earth); //gets the string from the strings.xml
                    openFragment(earthString, R.string.earth);

                     */
                } else {//if the fragment is open
                    closeFragment();
                }
            }
        });

        mMoonView = (ImageView) findViewById(R.id.moon_button);
        Glide.with(this).asGif().load(R.drawable.small_moon_button6).into(mMoonView);//library to use the gif
        mMoonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    new ShowDialog(Earth.this).showMoonDialog();

                    /*
                    String moonString = getResources().getString(R.string.moon);
                    openFragment(moonString, R.string.moon);

                     */
                } else {
                    closeFragment();
                }
            }
        });

    }
    //calls the swipe method
    @Override
    protected void onSwipeRight() {
        openVenus();
    }

    @Override
    protected void onSwipeLeft() {
        openMars();
    }

    //method to open the fragment
    private void openFragment(String text, int ID) {
        isOpen = true; //opens the fragment
        fragment = EarthFragment.newInstance(text); //creates new instance from the EarthFragment
        addText(ID, fragment); //calls a method to add the text of the fragment, probably doesn't have to be separate
        fragmentManager = getSupportFragmentManager(); //new for android, needs to use support fragment manager to set the transaction of the fragment
        transaction = fragmentManager.beginTransaction();//starts the transactions, the fragment is ready
        transaction.setCustomAnimations(R.anim.earth_from_bottom, R.anim.earth_to_bottom);//in order to add a button or something than add both again
        transaction.addToBackStack(null);//in order to close only the fragment and not the whole activity
        transaction.add(R.id.fragment_container, fragment, "fragment_earth").commit();//binds the fragment id in layout with the earthFragment we created and starts it
    }

    private void closeFragment() {
        isOpen = false;//closes the fragment
        transaction = fragmentManager.beginTransaction();//the fragment is ready to close
        transaction.setCustomAnimations(R.anim.earth_to_bottom, R.anim.earth_from_bottom);//animation for closing
        transaction.remove(fragment).commit();//remove the fragment from screen
    }

    private void addText(int ID, EarthFragment fragment) {
        fragment.setmTextView(ID);
    }

    private void openMars() {
        Intent intent = new Intent(this, Mars.class);//creates the second activity
        startActivity(intent);//starts the second activity
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);//adds slide from the right animation
    }

    private void openVenus() {
        Intent intent = new Intent(this, Venus.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);//adds slide from the left animation
    }

    //toast maker
    private void showToast() {
        //***THIS WHOLE METHOD IS FOR CUSTOM TOAST, REGULAR TOAST IS SIMPLER. LOOK DOC FOR MORE***
        LayoutInflater inflater = getLayoutInflater();//needs to use inflater for the View
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));//sets the view and binds it to the custom toast layout

        TextView text = (TextView) layout.findViewById(R.id.text);//binds the text of the toast
        text.setText("Earth");

        Toast toast = new Toast(getApplicationContext());//creates the toast itself
        toast.setGravity(Gravity.BOTTOM, 0, 50); //sets the location of the toast, probably can do without it with the default location
        toast.setDuration(Toast.LENGTH_SHORT);//sets the length of the toast
        toast.setView(layout);//sets the whole view from the beginning of the method
        toast.show();//shows the toast
    }

    public void showDialogStarsOnClick(View view) {
        new ShowDialog(Earth.this).showStarsDialog();
    }

}