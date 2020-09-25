package cr.ac.ucr.rickandmortyapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private vPager vPager;

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
        fragments.add();

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter (getSupportFragmentManager(), fragments());

    }


}