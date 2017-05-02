package com.example.palla.inroads2vit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference branch=myRef.child("branch");

    ArrayList<String> numberOfSeats = new ArrayList<>();
    int cse,mech,it;
    ListView list;
    ListAdapter adapter;
    Long days;
    Long ranks;
    String names;
    String today;
    Map<Long,String> map;
    Long prev_ranks;
    String branchee;
    String id;
    String name;
    int f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_seats);
        Log.d("create display","----------------------------------------------");
        map=new HashMap<Long, String>();
        map.put(Long.valueOf(1),"2-May-2017");
        map.put(Long.valueOf(2),"3-May-2017");
        map.put(Long.valueOf(3),"4-May-2017");
        Bundle extras = getIntent().getExtras();
        ranks = extras.getLong("rank");
        today = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        days = extras.getLong("day");
        id=extras.getString("id");
        name=extras.getString("name");
        f=0;
        list = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, R.layout.branch, R.id.nf, numberOfSeats);
        //adapter = ArrayAdapter<Integer>(this, R.layout.branch, R.id.number,numberOfSeats);


        branch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                cse = dataSnapshot.child("CSE").getValue(Integer.class);
                mech = dataSnapshot.child("MECH").getValue(Integer.class);
                it = dataSnapshot.child("IT").getValue(Integer.class);
                prev_ranks=dataSnapshot.child("rankcheck").getValue(Long.class);
                numberOfSeats.clear();
                numberOfSeats.add("CSE " + cse);
                numberOfSeats.add("IT " + it);
                numberOfSeats.add("MECH " + mech);


                Log.d("Palllllllaaaaviiiii", "yes");

                list.setAdapter(adapter);

                if ((today.equalsIgnoreCase(map.get(days))) && (ranks == prev_ranks + 1)) {

                    f=1;
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            if (position == 0 && cse!=0) {
                                cse--;
                                branch.child("CSE").setValue(cse);
                                branch.child("rankcheck").setValue(++prev_ranks);
                                branchee="CSE";
                                updateUser("CSE");
                            } else if (position == 1 && it!=0) {
                                it--;
                                branch.child("IT").setValue(it);
                                branch.child("rankcheck").setValue(++prev_ranks);
                                branchee="IT";
                                updateUser("IT");
                            } else if (position == 2 && mech!=0) {
                                mech--;
                                branch.child("MECH").setValue(mech);
                                branch.child("rankcheck").setValue(++prev_ranks);
                                branchee="MECH";
                                updateUser("MECH");
                            }
                            else {
                                Log.d("zeroth","zrothuser )))))))))))))))))))))))))))))");
                                Toast.makeText(getBaseContext(),"No seats available for the selected branch ",Toast.LENGTH_LONG).show();
                            }


                        }

                    });
                }
                if(f==0){

                    Toast.makeText(getBaseContext(),"your turn has not come yet",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //int cse=Integer.parseInt(branch.child("CSE").getValue());





    }
    void updateUser(String s){
        DatabaseReference user=myRef.child("users").child(id);
        user.child("branch").setValue(s);
        Intent dntent = new Intent(this,Final.class);
        dntent.putExtra("name",name);
        dntent.putExtra("rank",ranks);
        dntent.putExtra("id",id);
        dntent.putExtra("branch",branchee);
        this.startActivity(dntent);

    }

}



//public void showDialog(){



