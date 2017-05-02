package com.example.palla.inroads2vit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Final extends AppCompatActivity {
    String names;
    Long ranks;
    String id;
    String branch;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Bundle extras = getIntent().getExtras();
        names = extras.getString("name");
        ranks = extras.getLong("rank");
        id=extras.getString("id");
        mAuth = FirebaseAuth.getInstance();
        branch=extras.getString("branch");
        String s=names+" "+ranks+" "+" "+id+" "+branch;
        Log.d("s",s);
       // TextView t=(TextView) findViewById(R.id.t);
        Button b=(Button) findViewById(R.id.logout);
        Button m=(Button) findViewById(R.id.button3);
        TextView name=(TextView) findViewById(R.id.nf);
        TextView branchs=(TextView) findViewById(R.id.df);
        TextView rank=(TextView) findViewById(R.id.rf);
        name.setText(names);
        //rank.setText(Long.toString(ranks));
        branchs.setText(branch);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"logging out",Toast.LENGTH_SHORT).show();
                Intent dntent = new Intent(Final.this,MainActivity.class);
                Final.this.startActivity(dntent);

            }
        });
        m.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:47.4925,19.0513"));
                Intent chooser = Intent.createChooser(i, "Launch Maps");
                startActivity(chooser);
            }
        });


    }

}
