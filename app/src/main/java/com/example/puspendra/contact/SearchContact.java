package com.example.puspendra.contact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContact extends AppCompatActivity {
EditText et1;
    TextView t;
LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        et1=findViewById(R.id.search_et1);
        ll=findViewById(R.id.search_ll);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                SQLiteDatabase db=  openOrCreateDatabase("Students",MODE_PRIVATE,null);
                db.execSQL("create table if not exists Contact_list(name varchar (30), phone_no int, email varchar (30))");
                String query="select * from Contact_list where name like '"+et1.getText().toString()+"%' ";
                Cursor c=db.rawQuery(query,null);
                if (c.moveToFirst()){
                    do {
                        String name=c.getString(0);
                         t=new TextView(SearchContact.this);
                        t.setText(name);
                        ll.removeAllViews();
                        ll.addView(t);
                    }while (c.moveToNext());

                }
                else {
                    Toast.makeText(SearchContact.this, "empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
