package cr.ac.ucr.rickandmortyapi.api;

import cr.ac.ucr.rickandmortyapi.models.Location;
import cr.ac.ucr.rickandmortyapi.models.LocationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationService {

    //información general de todos
    @Headers("Content-Type: application/json")
    @GET("location")
    Call<LocationResponse> getLocations(@Query("page") int page);

    //información específica
    @Headers("Content-Type: application/json")
    @GET("location/{id}")
    Call<Location> getLocationInfo(@Path("id") int id);

}
