package com.example.latest_instagram_cloning_1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class userTab extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {


    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private ProgressBar progressBar;
    public userTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_tab, container, false);

        final ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        listView=view.findViewById(R.id.listView);
        progressBar=view.findViewById(R.id.progressBar);
        arrayList=new ArrayList();
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null)
                {
                    if(objects.size()>0)
                    {
                        for(ParseUser user: objects)
                        {
                            arrayList.add(user.getUsername());

                        }
                        listView.setAdapter(arrayAdapter);
                        progressBar.animate().alpha(0).setDuration(2000);
                        listView.setVisibility(View.VISIBLE);

                    }

                }
            }
        });

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(),userPosts.class);
        intent.putExtra("username",arrayList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereEqualTo("username",arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(user!=null && e==null)
                {
                    final PrettyDialog prettyDialog=new PrettyDialog(getContext());
                    prettyDialog.setTitle(user.getUsername()+"'s Info").setMessage(user.get("userProfession")+"\n"+
                            user.get("userHobbies")+"\n"+user.get("userSports")+"\n"+
                            user.get("userBio")+"").setIcon(R.drawable. person).addButton("OK",
                            R.color.pdlg_color_white,
                            R.color.colorPrimary,
                            new PrettyDialogCallback() {
                                @Override
                                public void onClick() {
                                    prettyDialog.dismiss();
                                }
                            }).show();

                }
            }
        });

        return true;
    }
}
