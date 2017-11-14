package mx.diegop.helloworld;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("db")
    Call<AlumnosResponse> getAlumnos();
}
