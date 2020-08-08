package com.example.puspendra.contact;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_contact extends AppCompatActivity {
EditText et1,et2,et3;
Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        et1=findViewById(R.id.et_add_c_name);
        et2=findViewById(R.id.et_add_c_no);
        et3=findViewById(R.id.et_add_c_email);
        b1=findViewById(R.id.b_add_c_save);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
                db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
                String query="insert into Contact_list values ('"+et1.getText().toString()+"',"+et2.getText().toString()+",'"+et3.getText().toString()+"')";
                db.execSQL(query);
                db.close();
                Toast.makeText(Add_contact.this, "Data Save", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}
