package com.example.puspendra.contact;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
LinearLayout ll;
    TextView mytext;
     TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll=findViewById(R.id.ll);

        SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
        String query="select * from Contact_list";
        Cursor c=db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                String name=c.getString(0);
                tv=new TextView(MainActivity.this);
                tv.setText(name);
                tv.setTextSize(28);
                ll.addView(tv);
                registerForContextMenu(tv);
            }while (c.moveToNext());
        }
        else
            Toast.makeText(MainActivity.this, "Contact list Empty", Toast.LENGTH_SHORT).show();

        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.add_c1)
        {
            Intent in= new Intent(MainActivity.this,Add_contact.class);
            startActivity(in);
        }

        if (item.getItemId()== R.id.add_c2)
        {
            Intent in= new Intent(MainActivity.this,SearchContact.class);
            startActivity(in);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menucontain,menu);
        mytext=(TextView)v;

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mc_detail)
        {
            Intent in= new Intent(MainActivity.this,contact_detail.class);
            startActivity(in);

        }

        if (item.getItemId()==R.id.mc_edit)
        {
            Intent in= new Intent(MainActivity.this,EditContact.class);
            startActivity(in);
        }

        if (item.getItemId()==R.id.mc_delete)
        {
            AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
            b.setTitle("Confirm");
            b.setMessage("Are you Confirm to Deleted");
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
                    db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
                    Toast.makeText(MainActivity.this, ""+tv.getText(), Toast.LENGTH_SHORT).show();
                    String query="delete from contact_list where name='"+tv.getText()+"'";
                    db.execSQL(query);
                    db.close();
                    ll.removeView(mytext);

                   Toast.makeText(MainActivity.this, "You have click Yes", Toast.LENGTH_SHORT).show();
                }
            });
            b.show();
        }


        return super.onContextItemSelected(item);
    }
}
