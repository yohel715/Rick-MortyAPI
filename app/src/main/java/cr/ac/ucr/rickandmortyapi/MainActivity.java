package cr.ac.ucr.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

import cr.ac.ucr.rickandmortyapi.adapters.MainViewPagerAdapter;
import cr.ac.ucr.rickandmortyapi.fragments.CharacterFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager vPager;

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

        //TODO: add fragments
        fragments.add(CharacterFragment.newInstance());

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter (getSupportFragmentManager(), fragments);

        vPager.setAdapter(mainViewPagerAdapter);
    }



}