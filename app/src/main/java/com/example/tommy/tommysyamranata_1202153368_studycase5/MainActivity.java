package com.example.tommy.tommysyamranata_1202153368_studycase5;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //deklarasi variable
    database dtbase;
    RecyclerView rv;
    ListAdapter adapter;
    ArrayList<ListItem> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("To Do List");  //set title


        rv = findViewById(R.id.Recyclerview);      //mengakses recyclerview
        datalist = new ArrayList<>();             //membuat araylist
        dtbase = new database(this);       //membuat database
        dtbase.readdata(datalist);               //memanggil method readdata

        //melakukan inisiasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);


        adapter = new ListAdapter(this,datalist, color); //create adapter
        rv.setHasFixedSize(true);   //membuat fix sizenya supaya tidak gampang berubah
        rv.setLayoutManager(new LinearLayoutManager(this)); //melakukan set layout
        rv.setAdapter(adapter);  //inisiasi adapter untuk recycler view

        //melakukan penghapusan data
        hapusgeser();
    }

    //membuat method untuk menghapus item pada to do list
    public void hapusgeser(){
        //membuat touch helper
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ListItem current = adapter.getData(position);
                //melakukan swipe kearah kekiri
                if(direction==ItemTouchHelper.LEFT){
                    if(dtbase.removedata(current.getTodo())){ //remove item yang dipilih
                        adapter.deleteData(position); //menghapus data
                        Snackbar.make(findViewById(R.id.coor), "Data Terhapus", 1000).show();  //membuat snack bar
                    }
                }
            }
        };

        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);   //menentukan itemtouchhelper untuk recycler view
        touchhelp.attachToRecyclerView(rv);
    }

    //membuat acitvity pada menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();   //mendapatkan id dari item
        //jika melakukan action setting
        if (id==R.id.action_settings){
            //membuat intent baru dari list to do
            Intent intent = new Intent(MainActivity.this, Setting.class);
            //start intent
            startActivity(intent);
            //finish activity
            finish();
        }
        return true;
    }

    //jika mengklik akan melakukan method ini
    public void add(View view) {
        //intent dari list to do ke add to do
        Intent intent = new Intent(MainActivity.this, add_ToDo.class);
        //start intent
        startActivity(intent);
    }
}
