package cr.ac.ucr.rickandmortyapi.api;

import cr.ac.ucr.rickandmortyapi.models.Episode;
import cr.ac.ucr.rickandmortyapi.models.EpisodeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeService {

    @Headers("Content-Type: application/json")
    @GET("episode")
    Call<EpisodeResponse> getEpisode(@Query("page") int page);

    @Headers("Content-Type: application/json")
    @GET("episode/{id}")
    Call<Episode> getEpisodesInfo(@Path("id") int id);
}
