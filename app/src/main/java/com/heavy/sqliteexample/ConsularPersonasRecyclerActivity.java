package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.heavy.sqliteexample.adaptadores.ListaPersonasAdpater;
import com.heavy.sqliteexample.entidades.Usuario;
import com.heavy.sqliteexample.utilidades.Utils;

import java.util.ArrayList;

public class ConsularPersonasRecyclerActivity extends AppCompatActivity {

    ArrayList<Usuario> listaUsuario;
    RecyclerView recycler;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consular_personas_recycler);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        listaUsuario=new ArrayList<>();
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        consultarListaPersonas();

        ListaPersonasAdpater adapter = new ListaPersonasAdpater(listaUsuario);
        recycler.setAdapter(adapter);

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utils.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuario.add(usuario);
        }
    }
}
