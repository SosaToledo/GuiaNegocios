package com.thinco.guianegocios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView view;
    private EditText text;
    private Button enviar;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.tvNombre);
        text = (EditText) findViewById(R.id.etNombre);
        enviar = (Button) findViewById(R.id.btnEnviar);
        database = FirebaseDatabase.getInstance().getReference("negocios");



        ValueEventListener neglistener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                negocio negocio= dataSnapshot.getValue(negocio.class);
                view.setText(negocio.getNombre());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        database.child("casaFrank").addValueEventListener(neglistener);

    }

    public void enviarDatos(View view) {
        String nombre = text.getText().toString();
        database.child("casaFrank").child("nombre").setValue(nombre);
    }
}
