package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.heavy.sqliteexample.utilidades.Utils;

public class RegistrarUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edTxtId, edTxtName, edTxtTelephone;
    Button btnRegistrarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        edTxtId = findViewById(R.id.edTxtId);
        edTxtName = findViewById(R.id.edTxtName);
        edTxtTelephone = findViewById(R.id.edTxtTelephone);
        btnRegistrarUser = findViewById(R.id.btnRegistrarUser);

        btnRegistrarUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mRegistrarUsuarios();
        //mRegistarUsuariosSQL();
    }

    private void mRegistrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",  null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.ID, edTxtId.getText().toString());
        values.put(Utils.NOMBRE, edTxtName.getText().toString());
        values.put(Utils.TELEFONO, edTxtTelephone.getText().toString());

        Long idResultante = db.insert(Utils.TABLA_USUARIO, Utils.ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro "+idResultante,Toast.LENGTH_LONG).show();
        db.close();
    }

    private void mRegistarUsuariosSQL(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",  null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO "+Utils.TABLA_USUARIO+" ("+Utils.ID+", "+Utils.NOMBRE+","+Utils.TELEFONO+" ) " +
                "VALUES ("+edTxtId.getText().toString()+", '"+edTxtName.getText().toString()+"', '"+edTxtTelephone.getText().toString()+"')";

        db.execSQL(insert);

        Toast.makeText(getApplicationContext(), "Id Registro "+edTxtId.getText().toString(),Toast.LENGTH_LONG).show();
        db.close();
    }
}
