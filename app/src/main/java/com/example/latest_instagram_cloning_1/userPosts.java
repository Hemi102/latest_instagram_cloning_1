package com.example.latest_instagram_cloning_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class userPosts extends AppCompatActivity {


    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);
        linearLayout=findViewById(R.id.linearLayout);
        Intent receivedObject=getIntent();
        final String receivedUserName=receivedObject.getStringExtra("username");
        FancyToast.makeText(this,receivedUserName, Toast.LENGTH_SHORT,
                FancyToast.SUCCESS,true).show();
        setTitle(receivedUserName+"'s posts");
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("photos1");
        parseQuery.whereEqualTo("username",receivedUserName);
        parseQuery.orderByDescending("createdAt");
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        try
        {
            parseQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if(objects.size()>0 && e==null)
                    {
                        for(ParseObject users:objects)
                        {

                                final TextView imageDes=new TextView(userPosts.this);
                                imageDes.setText(users.get("pic_des")+"");
                            ParseFile postPicture;

                            try
                            {

                                postPicture=(ParseFile) users.get("picture");
                                if(postPicture!= null)
                                {
                                    postPicture.getDataInBackground(new GetDataCallback() {
                                        @Override
                                        public void done(byte[] data, ParseException e) {
                                            if(e==null && data!=null)
                                            {
                                                Bitmap bitmap=null;
                                                try
                                                {
                                                    bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                                }
                                                catch (OutOfMemoryError e1)
                                                {
                                                    bitmap=null;
                                                    e1.printStackTrace();
                                                }

                                                if(bitmap!=null)
                                                {
                                                    ImageView postImageView=new ImageView(userPosts.this);
                                                    LinearLayout.LayoutParams imgParms=
                                                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                                                    imgParms.setMargins(0,0,0,0);
                                                    postImageView.setLayoutParams(imgParms);
                                                    postImageView.setScaleType(ImageView.ScaleType.FIT_START);
                                                    postImageView.setImageBitmap(bitmap);

                                                    LinearLayout.LayoutParams desParms=
                                                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                                                    desParms.setMargins(0,0,0,0);
                                                    imageDes.setLayoutParams(desParms);
                                                    imageDes.setGravity(Gravity.CENTER);
                                                    imageDes.setTextSize(22f);

                                                    linearLayout.addView(postImageView);
                                                    linearLayout.addView(imageDes);
                                                }
                                            }
                                            else
                                            {
                                                FancyToast.makeText(userPosts.this,e.getMessage()+"",
                                                        Toast.LENGTH_SHORT,
                                                        FancyToast.SUCCESS,true).show();
                                            }

                                        }
                                    });

                                }
                            }
                            catch (Exception ed)
                            {
                                postPicture=null;
                                ed.printStackTrace();
                            }


                        }
                    }
                    else
                    {
                        FancyToast.makeText(userPosts.this,"user have no post!",
                                Toast.LENGTH_SHORT,
                                FancyToast.SUCCESS,true).show();
                        finish();
                    }
                    progressDialog.dismiss();

                }
            });
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
        }


    }
}
