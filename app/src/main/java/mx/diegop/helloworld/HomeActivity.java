package mx.diegop.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AlumnosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle args = getIntent().getExtras();
        String usuario = args.getString("usuario");

        adapter = new AlumnosAdapter();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ApiImpl api = new ApiImpl();
        api.getAlumnos(new ApiImpl.ApiCallback<AlumnosResponse>() {
            @Override
            public void onSuccess(AlumnosResponse response) {
                adapter.setAlumnos(response.alumnos);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class AlumnosAdapter extends RecyclerView.Adapter<AlumnoViewHolder> {

        List<Alumno> alumnosList;

        public AlumnosAdapter() {
            alumnosList = new ArrayList<>();
        }

        @Override
        public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.alumno_item, parent, false);
            return new AlumnoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AlumnoViewHolder holder, int position) {
            holder.bind(alumnosList.get(position));
        }

        @Override
        public int getItemCount() {
            return alumnosList.size();
        }

        public void setAlumnos(List<Alumno> alumnos) {
            this.alumnosList = alumnos;
            notifyDataSetChanged();
        }
    }

    private class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView alumnoNombre;
        TextView alumnoEdad;

        public AlumnoViewHolder(View itemView) {
            super(itemView);
            alumnoNombre = itemView.findViewById(R.id.alumno_nombre);
            alumnoEdad = itemView.findViewById(R.id.alumno_edad);
        }

        public void bind(Alumno alumno) {
            alumnoNombre.setText(alumno.nombre);
            alumnoEdad.setText(String.valueOf(alumno.edad) + getAdapterPosition());
        }
    }

    public class Alumno {
        @SerializedName("nombre")
        String nombre;
        @SerializedName("edad")
        int edad;
    }

}
