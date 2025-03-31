package vn.iotstar.fragment_tablayout_viewpager2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayoutMediator;

import vn.iotstar.fragment_tablayout_viewpager2.adapter.ViewPager2Adapter;
import vn.iotstar.fragment_tablayout_viewpager2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ViewPager2Adapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ViewPager2Adapter(this);
        binding.viewPager2.setAdapter(adapter);

        //Tạo tab và lướt viewPaper2 thì tab cũng luot theo _ Liên kết tab với viewpaper2
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đơn mới");
                    break;
                case 1:
                    tab.setText("Đang xử lý");
                    break;
                case 2:
                    tab.setText("Đã hoàn thành");
                    break;
            }
        }).attach();
    }
}