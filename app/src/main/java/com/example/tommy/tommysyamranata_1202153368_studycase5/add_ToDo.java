package com.example.tommy.tommysyamranata_1202153368_studycase5;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_ToDo extends AppCompatActivity {
    EditText ToDo, Description, Priority; //disini kita deklarasikan variabel yg akan digunakan
    database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        setTitle("Add To Do"); //selanjutnya set titlenya mendaji add to do

        //melakukan akses terhadap editText yang berada pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        dtbase = new database(this);
    }

    @Override
    public void onBackPressed() {
        //melakukan intent dari add to do menuju list to do
        Intent intent = new Intent(add_ToDo.this, MainActivity.class);
        //start internet
        startActivity(intent);
        //melakukan finish ketika internet sudah dijalankan
        this.finish();
    }

    //method yang dijalanan ketika tombol tambah to do di klik
    public void tambah(View view) {
       //jika melakukan hal dibawah akan memunculkan langkah selanjutnya
        if (dtbase.inputdata(new ListItem(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //disini kita menampilkan toast
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            //berpindah dari add to do ke list to do
            startActivity(new Intent(add_ToDo.this, MainActivity.class));
            //menutup aktivitas
            this.finish();
        }else {
            //ketika tidak melakukan if maka edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            //lakukan set text menjandi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }

}
