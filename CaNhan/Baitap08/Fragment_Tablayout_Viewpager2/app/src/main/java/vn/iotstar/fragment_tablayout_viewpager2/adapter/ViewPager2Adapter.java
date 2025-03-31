package vn.iotstar.fragment_tablayout_viewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.iotstar.fragment_tablayout_viewpager2.CompletedOrderFragment;
import vn.iotstar.fragment_tablayout_viewpager2.NewOrderFragment;
import vn.iotstar.fragment_tablayout_viewpager2.ProcessingOderFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0: return new NewOrderFragment();
            case 1: return new ProcessingOderFragment();
            case 2: return new CompletedOrderFragment();
            default:return new NewOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
