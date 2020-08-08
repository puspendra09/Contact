package com.example.puspendra.contact;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
Button b1,b2;
EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        b1=findViewById(R.id.edit_b1);
        b2=findViewById(R.id.edit_b2);
        e1=findViewById(R.id.edit_t1);
        e2=findViewById(R.id.edit_t2);
        e3=findViewById(R.id.edit_t3);

        SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
        String query="select * from Contact_list";
        Cursor c=db.rawQuery(query,null);
        if (c.moveToFirst())
        {
            e1.setText(c.getString(0));
            e2.setText(c.getString(1));
            e3.setText(c.getString(2));
        }
        else {
            Toast.makeText(EditContact.this, "Invalide Roll no", Toast.LENGTH_SHORT).show();
        }
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
            db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
            String query="update Contact_list set name='"+e1.getText().toString()+"',phone_no='"+e2.getText().toString()+"',email='"+e3.getText().toString()+"'";
            db.execSQL(query);
            db.close();
            Toast.makeText(EditContact.this, "Data Update", Toast.LENGTH_SHORT).show();
            finish();

        }
    });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
