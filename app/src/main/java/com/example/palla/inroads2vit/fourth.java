package com.example.palla.inroads2vit;

import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class fourth extends AppCompatActivity {

    // TextView txt;
    //Button b1;
    String day1, today;
    int rank = 3;
    int prev_rank = 2;
    Button b1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    //DatabaseReference rank = myRef.child("rank");
    Long days;
    Long ranks;
    String names;
    String id;
    String branch;
    Map<Long,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3);
        b1 = (Button)findViewById(R.id.branchc);
        map=new HashMap<Long, String>();
        map.put(Long.valueOf(1),"30-Apr-2017");
        map.put(Long.valueOf(2),"1-May-2017");
        map.put(Long.valueOf(3),"2-May-2017");
        Bundle extras = getIntent().getExtras();
        names = extras.getString("name");
        ranks = extras.getLong("rank");
        days = extras.getLong("day");
        id=extras.getString("id");
        branch=extras.getString("branch");
        final TextView name=(TextView) findViewById(R.id.nf);
        final TextView rankss=(TextView) findViewById(R.id.rank);
        TextView datesss=(TextView) findViewById(R.id.date);
        name.setText(names);
        rankss.setText(Long.toString(ranks));
        datesss.setText(Long.toString(days));




        //txt = (TextView)findViewById(R.id.text1);
        //txt.setText(day1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (branch.equalsIgnoreCase("na")) {
                    Intent in = new Intent(getApplicationContext(), DisplayActivity.class);

                    in.putExtra("rank", ranks);
                    in.putExtra("day", days);
                    in.putExtra("id", id);
                    in.putExtra("name", names);


                    startActivity(in);
                }
                else {
                    Intent myIntent = new Intent(fourth.this, Final.class);
                    myIntent.putExtra("name",names);
                    Log.d("names in fourth",names);
                    Log.d("Rank in final",Long.toString(ranks));
                    myIntent.putExtra("rank",ranks);
                    myIntent.putExtra("id",id);
                    myIntent.putExtra("branch",branch);
                    fourth.this.startActivity(myIntent);


                }
            }

        });

    }






}
