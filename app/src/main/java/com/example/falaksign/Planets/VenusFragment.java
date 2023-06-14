package com.example.falaksign.Planets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.falaksign.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EarthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VenusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final int TEXT = 0;
    private static final String TEXT = "text";

    // TODO: Rename and change types of parameters
    private String mText;
    private static TextView mTextView;
    private int mID;

    public VenusFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VenusFragment newInstance(String text) {
        VenusFragment fragment = new VenusFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mText = getArguments().getString(TEXT);
            //mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_earth, container, false);
        mTextView = (TextView) view.findViewById(R.id.earth_text); //IMPORTANT to set the TextView from the xml to show the text that set in the next line
        mTextView.setText(mID); // set the text to textView with the (int) id of the string text
        return view;
    }

    //just to change between the moon and the earth text string ID
    public void setmTextView(int ID){
        mID = ID;
    }
}