package cr.ac.ucr.rickandmortyapi.api;

import cr.ac.ucr.rickandmortyapi.models.Character;
import cr.ac.ucr.rickandmortyapi.models.CharacterResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterService {

    @Headers("Content-Type: application/json")
    @GET("character")
    //TOOD: parametro de numero de pagina
    Call<CharacterResponse> getCharacter(@Query("page") int page);

    @Headers("Content-Type: application/json")
    @GET("character/{id}")
    Call<Character> getCharacterInfo(@Path("id") int id);

}
