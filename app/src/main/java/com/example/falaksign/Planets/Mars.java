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

public class Mars extends _SwipeActivityClass {

    private FrameLayout mFragmentContainer;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MarsFragment fragment;

    private boolean isOpen = false;

    private ImageView mMarsView;
    private ImageView mPhobosView;
    private ImageView mDiemosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mars);

        showToast();

        mFragmentContainer = (FrameLayout) findViewById(R.id.mars_fragment_container);

        mDiemosView = (ImageView) findViewById(R.id.diemos_button1);
        mMarsView = (ImageView) findViewById(R.id.mars_button);
        Glide.with(this).asGif().load(R.drawable.mars_rotation2).into(mMarsView);//library to use the gif
        mMarsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen) {
                    new ShowDialog(Mars.this).showPlanetsDialog();

                    /*
                    String marsText = getResources().getString(R.string.mars);
                    openFragment(marsText, R.string.mars);

                     */
                }else{
                    closeFragment();
                }
            }
        });
        mPhobosView = (ImageView) findViewById(R.id.phobos_button2);

    }
    private void openFragment(String text, int ID) {
        isOpen = true;
        fragment = MarsFragment.newInstance(text);
        addText(ID, fragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_from_bottom, R.anim.earth_to_bottom);//in order to add a button or something than add both again
        transaction.addToBackStack(null);//in order to close only the fragment and not the whole activity
        transaction.add(R.id.mars_fragment_container, fragment, "fragment_mars").commit();
    }

    private void closeFragment() {
        isOpen = false;
        transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.earth_to_bottom, R.anim.earth_from_bottom);
        transaction.remove(fragment).commit();
    }

    private void addText(int ID, MarsFragment fragment) {
        fragment.setmTextView(ID);
    }

    @Override
    protected void onSwipeRight() {
        openEarth();
    }

    @Override
    protected void onSwipeLeft() {
        openJupiter();
    }
    private void openEarth(){
        Intent intent = new Intent(this, Earth.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_out,R.anim.push_right_in);
    }
    private void openJupiter(){
        Intent intent = new Intent(this, Jupiter.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    private void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Mars");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void showDialogStarsOnClick(View view) {
        new ShowDialog(Mars.this).showStarsDialog();
    }

}