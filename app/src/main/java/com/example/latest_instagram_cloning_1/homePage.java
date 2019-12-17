package com.example.latest_instagram_cloning_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class homePage extends AppCompatActivity implements View.OnClickListener {

    private TextView signupText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //signing up
        signupText=findViewById(R.id.signupText);
        signupText.setOnClickListener(this);
        //login button
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
        {
            //ParseUser.getCurrentUser().logOut();
            transferToSocialActivity();

        }

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.language);
        spinner.setItems("English (United State)", "Arabic", "Urdu", "Bangali", "Turkish");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {



            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }


        });

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signupText:
                Intent intent=new Intent(homePage.this,sign_up.class);
                startActivity(intent);

                break;

            case R.id.loginButton:
                EditText username,password;
                username=findViewById(R.id.loginUsername);
                password=findViewById(R.id.loginPassword);
                if(username.getText().toString().equals("")|| password.getText().toString().equals(""))
                {
                    FancyToast.makeText(homePage.this,
                            "Must enter username and password",
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
                else {
                    ParseUser.logInInBackground(username.getText().toString(),
                            password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(homePage.this,
                                        user.get("username") + " logged in:",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transferToSocialActivity();

                            } else {
                                FancyToast.makeText(homePage.this,
                                        "Password or username not match",
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }


                break;
        }

    }


    public void rootLayout(View v)
    {
        try
        {
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e)
        {

        }

    }
    public void transferToSocialActivity()
    {
        Intent intent=new Intent(homePage.this,socialActivity.class);
        startActivity(intent);
        finish();
    }
}
