package mx.diegop.helloworld;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiImpl {

    private ApiService apiService;

    public ApiImpl() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/diegop88/demo/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getAlumnos(final ApiCallback<AlumnosResponse> callback) {
        Call<AlumnosResponse> call = apiService.getAlumnos();
        call.enqueue(new Callback<AlumnosResponse>() {
            @Override
            public void onResponse(Call<AlumnosResponse> call, Response<AlumnosResponse> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Ups"));
                }
            }

            @Override
            public void onFailure(Call<AlumnosResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(Throwable t);
    }

}
