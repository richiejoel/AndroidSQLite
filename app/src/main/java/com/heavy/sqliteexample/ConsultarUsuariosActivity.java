package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.heavy.sqliteexample.utilidades.Utils;

public class ConsultarUsuariosActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edTxtId, edtTxtNombreConsulta, edtTxtTelefonoConsulta;
    Button btnBuscar, btnActualizar, btnEliminar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        edTxtId = findViewById(R.id.edTxtId);
        edtTxtNombreConsulta = findViewById(R.id.edtTxtNombreConsulta);
        edtTxtTelefonoConsulta = findViewById(R.id.edtTxtTelefonoConsulta);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnBuscar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBuscar:
                mConsultarUsuarios();
                //mConsultarUsuariosSQL();
                break;
            case R.id.btnActualizar:
                mActualizarUsuarios();
                break;
            case R.id.btnEliminar:
                mEliminarUsuarios();
                break;
        }

    }

    private void mConsultarUsuarios(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {edTxtId.getText().toString()};
        String[] campos = {Utils.NOMBRE, Utils.TELEFONO};

        try {
            Cursor cursor = db.query(Utils.TABLA_USUARIO,campos,Utils.ID+"=?",parametros, null, null, null);
            cursor.moveToFirst();
            edtTxtNombreConsulta.setText(cursor.getString(0));
            edtTxtTelefonoConsulta.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe.", Toast.LENGTH_LONG).show();
            mLimpiarCampos();
        }

    }

    private void mConsultarUsuariosSQL(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {edTxtId.getText().toString()};
        try{
            String query = "SELECT "+Utils.NOMBRE+", "+Utils.TELEFONO+" FROM "+Utils.TABLA_USUARIO+ " WHERE "+Utils.ID+" = ?";
            Cursor cursor = db.rawQuery(query, parametros);
            cursor.moveToFirst();
            edtTxtNombreConsulta.setText(cursor.getString(0));
            edtTxtTelefonoConsulta.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe.", Toast.LENGTH_LONG).show();
            mLimpiarCampos();
        }
    }

    private void mActualizarUsuarios(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {edTxtId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utils.NOMBRE,edtTxtNombreConsulta.getText().toString());
        values.put(Utils.TELEFONO,edtTxtTelefonoConsulta.getText().toString());

        db.update(Utils.TABLA_USUARIO,values,Utils.ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se actualizó correctamente",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void mEliminarUsuarios(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {edTxtId.getText().toString()};

        db.delete(Utils.TABLA_USUARIO,Utils.ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se eliminó correctamente",Toast.LENGTH_LONG).show();
        mLimpiarCampos();
        db.close();
    }

    private void mLimpiarCampos(){
        edTxtId.setText("");
        edtTxtNombreConsulta.setText("");
        edtTxtTelefonoConsulta.setText("");
    }
}
