package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.heavy.sqliteexample.entidades.Mascota;
import com.heavy.sqliteexample.entidades.Usuario;
import com.heavy.sqliteexample.utilidades.Utils;

import java.util.ArrayList;

public class ConsultarMascotasActivity extends AppCompatActivity {

    ListView listViewMascotas;
    ArrayList<Mascota> listaMascotas;
    ArrayList<String> listaMascotaFactory;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_mascotas);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewMascotas = findViewById(R.id.listViewMascotas);

        mConsutarListaMascotas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaMascotaFactory);
        listViewMascotas.setAdapter(adapter);

        listViewMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),listaMascotas.get(position).getNombreMascota().toString(), Toast.LENGTH_LONG).show();
                Mascota mascotaFactory = listaMascotas.get(position);
                Intent myIntentC = new Intent(ConsultarMascotasActivity.this, DetalleMascotaActivity.class);
                Bundle myBundleC = new Bundle();
                myBundleC.putSerializable("mascota",mascotaFactory);
                myIntentC.putExtras(myBundleC);
                startActivity(myIntentC);

            }
        });
    }

    private void mConsutarListaMascotas(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Mascota mascota = null;
        listaMascotas = new ArrayList<Mascota>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utils.TABLA_MASCOTA+" ", null);

        while (cursor.moveToNext()){
            mascota = new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));
            listaMascotas.add(mascota);
        }
        mObtenerLista();

    }

    private void mObtenerLista(){
        listaMascotaFactory = new ArrayList<String>();
        for(Mascota m: listaMascotas){
            listaMascotaFactory.add(m.getIdMascota()+" - "+m.getNombreMascota());
        }
    }
}
