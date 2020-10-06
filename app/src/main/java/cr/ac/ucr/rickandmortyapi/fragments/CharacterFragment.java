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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

    boolean canLoad = true;
    int limit = 0;
    int page = 1;
    private ProgressBar pb_loading;
    private RecyclerView rvCharacters;


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

        pb_loading = view.findViewById(R.id.pb_loading);
        rvCharacters = view.findViewById(R.id.rv_characters);

        //ArrayList --> Adapter <-- RecyclerView

        charactersAdapter = new CharactersAdapter(activity);
        rvCharacters.setAdapter(charactersAdapter);
        rvCharacters.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);

        rvCharacters.setLayoutManager(linearLayoutManager);

        charactersAdapter.addCharacters(characters);

        setupRVScrollListener(rvCharacters, linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ToDo: se hace la lógica
        getEpisodesInfo(page);
    }

    private void getEpisodesInfo(int page) {

        canLoad = false;

        CharacterService characterService = RetrofitBuilder.createService(CharacterService.class);

        Call<CharacterResponse> response = characterService.getCharacter(page);

        response.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, @Nullable Response<CharacterResponse> response) {
                if (response.isSuccessful()){

                    CharacterResponse characterResponse = response.body();

                    ArrayList<Character> characters = characterResponse.getResults();

                    charactersAdapter.addCharacters(characters);

                    showCharacters(true);

                }else {
                    Log.e(TAG,"onError " + response.errorBody());
                }
                canLoad = true;
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, @Nullable  Throwable t) {
                canLoad = true;
                throw new RuntimeException(t);
            }
        });
    }

    private void setupRVScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // dy = + -> personaje 0 a 20
                // dy = - -> personaje 20 a 0
                if (dy > 0) {
                    // Total de items
                    int totalItems = linearLayoutManager.getItemCount();

                    // Items que ya se mostraron
                    int pastItems = linearLayoutManager.findFirstVisibleItemPosition();

                    // items que se estan mostrando
                    int visibleItems = linearLayoutManager.getChildCount();

                    if(canLoad){
                        if((pastItems + visibleItems) >= totalItems ){
                            page++;

                            pb_loading.setVisibility(View.VISIBLE);

                            getEpisodesInfo(page);
                        }
                    }
                }

            }
        });
    }

    private void showCharacters(boolean setVisible){
        rvCharacters.setVisibility(setVisible ? View.VISIBLE :View.GONE);
        pb_loading.setVisibility(!setVisible ? View.GONE: View.GONE);
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