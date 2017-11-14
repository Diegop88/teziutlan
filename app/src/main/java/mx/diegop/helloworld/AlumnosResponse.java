package mx.diegop.helloworld;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlumnosResponse {
    @SerializedName("alumnos")
    List<HomeActivity.Alumno> alumnos;
}
