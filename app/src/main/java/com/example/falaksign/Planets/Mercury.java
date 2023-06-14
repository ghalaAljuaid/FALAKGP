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

public class Mercury extends _SwipeActivityClass {

    private ImageView mMercuryView;

    private boolean isOpen = false;
    private FrameLayout mFragmentContainer;
    private MercuryFragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mercury);

        showToast();

        mFragmentContainer = (FrameLayout) findViewById(R.id.mercury_fragment_container);

        mMercuryView = (ImageView) findViewById(R.id.mercury_button);
        Glide.with(this).asGif().load(R.drawable.mercury2).into(mMercuryView);//library to use the gif
        mMercuryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    new ShowDialog(Mercury.this).showPlanetsDialog();

                    /*
                    String mercuryString = getResources().getString(R.string.mercury); //gets the string from the strings.xml
                    openFragment(mercuryString, R.string.mercury);

                     */
                } else {
                    closeFragment();
                }
            }
        });

    }

    private void openFragment(String text, int ID) {
        isOpen = true;
        fragment = MercuryFragment.newInstance(text);
        addText(ID, fragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_from_bottom, R.anim.earth_to_bottom);//in order to add a button or something than add both again
        transaction.addToBackStack(null);//in order to close only the fragment and not the whole activity
        transaction.add(R.id.mercury_fragment_container, fragment, "fragment_mercury").commit();
    }

    private void closeFragment() {
        isOpen = false;
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_to_bottom, R.anim.earth_from_bottom);
        transaction.remove(fragment).commit();
    }

    private void addText(int ID, MercuryFragment fragment) {
        fragment.setmTextView(ID);
    }

    @Override
    protected void onSwipeRight() {
        openSun();
    }

    @Override
    protected void onSwipeLeft() {
        openVenus();
    }

    private void openSun(){
        Intent intent = new Intent(this, Sun.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_out,R.anim.push_right_in);
    }
    private void openVenus(){
        Intent intent = new Intent(this, Venus.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    private void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Mercury");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void showDialogStarsOnClick(View view) {
        new ShowDialog(Mercury.this).showStarsDialog();
    }

}