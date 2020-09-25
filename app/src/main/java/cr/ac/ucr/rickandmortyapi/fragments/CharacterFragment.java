package cr.ac.ucr.rickandmortyapi.fragments;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cr.ac.ucr.rickandmortyapi.R;
import cr.ac.ucr.rickandmortyapi.adapters.CharactersAdapter;
import cr.ac.ucr.rickandmortyapi.api.CharacterService;
import cr.ac.ucr.rickandmortyapi.api.RetrofitBuilder;
import cr.ac.ucr.rickandmortyapi.models.Character;
import cr.ac.ucr.rickandmortyapi.models.CharacterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CharacterFragment extends Fragment {

    private AppCompatActivity activity;
    private ArrayList<Character> characters;

    private static final String TAG = "CharacterFragment";
    private CharactersAdapter charactersAdapter;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public static CharacterFragment newInstance() {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ToDo: inicializar variables que no depende de la vista(layout)
        characters = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //ToDo: Inicializar viariables que no depende de la vista. Todos los componentes visuales (textView,Btn, EditsText)


        View view = inflater.inflate(R.layout.fragment_character, container, false);

        RecyclerView rvCharacters = view.findViewById(R.id.rv_characters);

        //ArrayList --> Adapter <-- RecyclerView

        charactersAdapter = new CharactersAdapter(activity);
        rvCharacters.setAdapter(charactersAdapter);
        rvCharacters.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);

        rvCharacters.setLayoutManager(linearLayoutManager);

        charactersAdapter.addCharacters(characters);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ToDo: se hace la lógica
        getCharactersInfo();
    }

    private void getCharactersInfo() {
        CharacterService characterService = RetrofitBuilder.CreateService(CharacterService.class);

        Call<CharacterResponse> response = characterService.getCharacter();

        response.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, @Nullable Response<CharacterResponse> response) {
                if (response.isSuccessful()){

                    CharacterResponse characterResponse = response.body();

                    ArrayList<Character> characters = characterResponse.getResults();
                    for(Character character: characters){
                        Log.i(TAG, "character: " + character.getName());
                    }
                    charactersAdapter.addCharacters(characters);

                }else {
                    Log.e(TAG,"onError " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, @Nullable  Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;

    }
}