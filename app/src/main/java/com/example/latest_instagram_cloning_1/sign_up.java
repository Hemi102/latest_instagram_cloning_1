package com.example.latest_instagram_cloning_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class sign_up extends AppCompatActivity  implements View.OnClickListener{

    private Button saveData;
    private EditText name,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.signupUsername);
        email=findViewById(R.id.signupEmail);


        if(ParseUser.getCurrentUser()!=null )
        {
            //ParseUser.getCurrentUser().logOut();
            transferToSocialActivity();
        }


        password=findViewById(R.id.signupPassword);
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    onClick(saveData);
                }
                return false;
            }
        });
        saveData=findViewById(R.id.signupButton);

        saveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signupButton:
                if(name.getText().toString().equals("")||
                        email.getText().toString().equals("")||
                        password.getText().toString().equals(""))
                {
                    FancyToast.makeText(sign_up.this,
                            "Username email and password required",
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
                else {
                    final ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(name.getText().toString());
                    parseUser.setEmail(email.getText().toString());
                    parseUser.setPassword(password.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("signing up " + name.getText().toString());
                    progressDialog.show();
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(sign_up.this, parseUser.get("username") + " signed in:", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transferToSocialActivity();
                            } else {
                                FancyToast.makeText(sign_up.this, e.getMessage() + " ", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                            progressDialog.dismiss();
                        }
                    });

                }

                break;

        }

    }

    public void rootLayout(View v)
    {
        try {
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void transferToSocialActivity()
    {
        Intent intent=new Intent(sign_up.this,socialActivity.class);
        startActivity(intent);
        finish();
    }


}
