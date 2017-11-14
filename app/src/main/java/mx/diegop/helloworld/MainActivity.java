package mx.diegop.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

            //TALLER3
            //C0ngr3s02017

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView bienvenido = findViewById(R.id.bienvenido);
        final EditText nombre = findViewById(R.id.nombre);
        Button siguiente = findViewById(R.id.siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = nombre.getText().toString();
                abrirSegundaActividad(nombreUsuario);
            }
        });
    }

    public void abrirSegundaActividad(String nombreUsuario) {
        Intent segundaActividad = new Intent(this, HomeActivity.class);
        Bundle args = new Bundle();
        args.putString("usuario", nombreUsuario);
        segundaActividad.putExtras(args);
        startActivity(segundaActividad);
    }
}
