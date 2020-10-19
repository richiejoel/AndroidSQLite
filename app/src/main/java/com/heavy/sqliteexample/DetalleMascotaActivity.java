package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.heavy.sqliteexample.entidades.Mascota;
import com.heavy.sqliteexample.utilidades.Utils;

public class DetalleMascotaActivity extends AppCompatActivity {

    TextView txtIdMascota, txtNombreMascota, txtRaza, txtIdDuenio, txtNombreDuenio, txtTelefonoDuenio;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        txtIdMascota = findViewById(R.id.txtIdMascota);
        txtNombreMascota = findViewById(R.id.txtNombreMascota);
        txtRaza = findViewById(R.id.txtRaza);
        txtIdDuenio = findViewById(R.id.txtIdDuenio);
        txtNombreDuenio = findViewById(R.id.txtNombreDuenio);
        txtTelefonoDuenio = findViewById(R.id.txtTelefonoDuenio);

        Bundle myBundle = getIntent().getExtras();
        Mascota mascota = null;

        if(myBundle != null){
            mascota = (Mascota) myBundle.getSerializable("mascota");
            txtIdMascota.setText(mascota.getIdMascota().toString());
            txtNombreMascota.setText(mascota.getNombreMascota());
            txtRaza.setText(mascota.getRaza());
            //txtIdDuenio.setText(mascota.getIdDuenio().toString());
            consultarPersona(mascota.getIdDuenio());
        }



    }

    private void consultarPersona(Integer idPersona) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={idPersona.toString()};
        String[] campos={Utils.NOMBRE,Utils.TELEFONO};
        Toast.makeText(getApplicationContext(),"El documento "+idPersona,Toast.LENGTH_LONG).show();
        try {
            Cursor cursor =db.query(Utils.TABLA_USUARIO,campos,Utils.ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            txtIdDuenio.setText(idPersona.toString());
            txtNombreDuenio.setText(cursor.getString(0));
            txtTelefonoDuenio.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            txtNombreDuenio.setText("");
            txtTelefonoDuenio.setText("");
        }

    }
}
