package cr.ac.ucr.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;


import java.util.ArrayList;

import cr.ac.ucr.rickandmortyapi.adapters.MainViewPagerAdapter;
import cr.ac.ucr.rickandmortyapi.fragments.CharacterFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager vPager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.t_toolbar);
        setSupportActionBar(toolbar);

        vPager = findViewById(R.id.vp_pager);

        setUpViewPager();
    }

    private void setUpViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        titles.add("CHARACTERS");
        titles.add("LOCATIONS");
        titles.add("EPISODES");

        fragments.add(CharacterFragment.newInstance());
        fragments.add(CharacterFragment.newInstance());
        fragments.add(CharacterFragment.newInstance());

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter (getSupportFragmentManager(), fragments, titles);

        vPager.setAdapter(mainViewPagerAdapter);
    }

}