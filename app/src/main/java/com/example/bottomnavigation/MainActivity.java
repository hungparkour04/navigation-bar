package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bottomnavigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hiển thị HomeFragment mặc định khi khởi động ứng dụng
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        // Thiết lập sự kiện chọn cho BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.shorts) {
                selectedFragment = new ShortsFragment();
            } else if (item.getItemId() == R.id.subscriptions) {
                selectedFragment = new SubscriptionFragment();
            } else if (item.getItemId() == R.id.library) {
                selectedFragment = new LibraryFragment();
            }

            // Thay thế fragment hiện tại bằng fragment mới
            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null); // Cho phép quay lại fragment trước đó bằng nút quay lại
        fragmentTransaction.commit();
    }
}
