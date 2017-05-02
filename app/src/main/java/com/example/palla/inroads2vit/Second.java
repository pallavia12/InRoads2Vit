package com.example.palla.inroads2vit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.TextView;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    TextView tv;
    String email;
    ProgressDialog pd;
    String name;
    Long rank;
    Long day;
    String id;
    String branch;
    int f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        String s;
        TextView tv = (TextView) findViewById(R.id.tv);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            tv.setText(email);
        }
        f=0;
        pd = new ProgressDialog(Second.this);
        pd.setMessage("checking for validity of user");
        pd.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference childRef = myRef.child("users");
        Log.d("s","entered Background----------------------------------------------------------------------------");
        childRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String mail = (String) messageSnapshot.child("email").getValue();
                    Log.d("email",mail);
                    if(mail.equalsIgnoreCase(email))
                    {
                        name= (String) messageSnapshot.child("username").getValue();
                        rank=(Long) messageSnapshot.child("rank").getValue();
                        id=(String) messageSnapshot.getKey();
                        day=(Long) messageSnapshot.child("day").getValue();
                        branch=(String) messageSnapshot.child("branch").getValue();
                        pd.dismiss();
                        f=1;
                        //     Toast.makeText(getBaseContext(),"authorised",Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(Second.this, fourth.class);
                        myIntent.putExtra("email",mail);
                        myIntent.putExtra("name",name);
                        myIntent.putExtra("rank",rank);
                        myIntent.putExtra("day",day);
                        myIntent.putExtra("id",id);
                        myIntent.putExtra("branch",branch);
                        Second.this.startActivity(myIntent);

                        break;

                    }

                }
                if(f==0)
                {
                    Toast.makeText(getBaseContext(),"unauthorised",Toast.LENGTH_LONG).show();
                    pd.dismiss();

                }
            }
            @Override public void onCancelled(DatabaseError databaseError) {
                Log.d("error","error");

            } });

    }










 /*   private  class Load extends AsyncTask<Void,Void,String>{
        String s=" ";


        @Override
        protected String doInBackground(Void... voids) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            DatabaseReference childRef = myRef.child("users");
            Log.d("s","entered Background----------------------------------------------------------------------------");
            childRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String mail = (String) messageSnapshot.child("email").getValue();
                        Log.d("email",mail);
                        if(mail.equalsIgnoreCase(email))
                        {
                            s= "success";
                            break;

                        }
                        else{
                           s= "unsuccessful";
                        }
                    }
                }
                @Override public void onCancelled(DatabaseError databaseError) {
                    Log.d("error","error");
                    s="error";
                } });
            Log.d("svalue",s);
            return s;
        }
        protected void onPostExecute(String ss) {
            super.onPostExecute(ss);
            Log.d("svalue",ss);
            pd.dismiss();

            if (ss.equalsIgnoreCase("success"))
            {
                Toast.makeText(getBaseContext(),email +" authorised",Toast.LENGTH_LONG).show();
              TextView t= (TextView) findViewById(R.id.tv);
                t.setText("authorised");
            }
            else
            {
                Toast.makeText(getBaseContext(),"unauthorised",Toast.LENGTH_LONG).show();
            }
        }


    }
*/
}
