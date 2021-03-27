package htmlprogrammer.labs.lab4_2;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import htmlprogrammer.labs.lab4_2.fragments.OneFragment;
import htmlprogrammer.labs.lab4_2.fragments.ThreeFragment;
import htmlprogrammer.labs.lab4_2.fragments.TwoFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    private class Adapter extends FragmentStateAdapter {
        private List<Fragment> list = new ArrayList<>();

        public Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public void addFragment(Fragment fragment){
            list.add(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return list.get(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tab);

        Adapter adapter = new Adapter(this);
        adapter.addFragment(new OneFragment());
        adapter.addFragment(new TwoFragment());
        adapter.addFragment(new ThreeFragment());
        viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab " + (position + 1));
            }
        });

        mediator.attach();
    }
}
