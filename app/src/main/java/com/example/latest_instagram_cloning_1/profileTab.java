package com.example.latest_instagram_cloning_1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.

 */
public class profileTab extends Fragment {

    private EditText profileName,bio,profession,sports,hobbies;
    Button updateInfo;



    public profileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile_tab, container, false);
        profileName=view.findViewById(R.id.name);
        profession=view.findViewById(R.id.profession);
        hobbies=view.findViewById(R.id.profession);
        sports=view.findViewById(R.id.hobbies);
        bio=view.findViewById(R.id.bio);
        updateInfo=view.findViewById(R.id.updateInfo);
        final ParseUser parseUser=ParseUser.getCurrentUser();

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",profileName.getText().toString());
                parseUser.put("userProfession",profession.getText().toString());
                parseUser.put("userHobbies",hobbies.getText().toString());
                parseUser.put("userBio",bio.getText().toString());
                parseUser.put("userSports",sports.getText().toString());
                final ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("loading");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            FancyToast.makeText(getContext(),
                                    "Data Updated", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();
                        }
                        else
                        {
                            FancyToast.makeText(getContext(),
                                    "error to save data", FancyToast.LENGTH_LONG,
                                    FancyToast.WARNING, true).show();

                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });

        return view;

    }
}
