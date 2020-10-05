package cr.ac.ucr.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cr.ac.ucr.rickandmortyapi.api.CharacterService;
import cr.ac.ucr.rickandmortyapi.api.RetrofitBuilder;
import cr.ac.ucr.rickandmortyapi.models.Character;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity {



    private ImageView ivCover;
    private Toolbar tToolbar;
    private TextView tvStatus;
    private TextView tvSpecie;
    private TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        tvStatus = findViewById(R.id.tv_status);
        tvSpecie = findViewById(R.id.tv_species);
        tvLocation = findViewById(R.id.tv_location);

        tToolbar = findViewById(R.id.t_toolbar);

        ivCover = findViewById(R.id.iv_cover);

        Intent intent = getIntent();

        if(intent != null){
            int characterId = intent.getIntExtra(getString(R.string.character_id),0);

            String characterName = intent.getStringExtra(getString(R.string.character_name));

            if(characterId > 0){

                tToolbar.setTitle(characterName);

                setSupportActionBar(tToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                getCharacterInfo(characterId);
            }
        }
    }

    private void getCharacterInfo(int characterId) {

        Glide.with(this)
                .load("https://rickandmortyapi.com/api/character/avatar/"+ characterId +".jpeg")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivCover);

        CharacterService characterService = RetrofitBuilder.createService(CharacterService.class);

        Call<Character> characterCall = characterService.getCharacterInfo(characterId);

        characterCall.enqueue(new Callback<Character>() {
            private String TAG = "error";

            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                    if(response.isSuccessful()){
                        Character character = response.body();

                        if(character != null){
                            tvStatus.setText(character.getStatus());
                            tvSpecie.setText(character.getSpecies());
                            tvLocation.setText(character.getLocation().getName());
                        }
                    }else{
                        Log.e(TAG, response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {

            }
        });
    }
}