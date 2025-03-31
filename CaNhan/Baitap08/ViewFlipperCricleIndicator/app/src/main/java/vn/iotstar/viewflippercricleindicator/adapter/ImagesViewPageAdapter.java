package vn.iotstar.viewflippercricleindicator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import vn.iotstar.viewflippercricleindicator.R;
import vn.iotstar.viewflippercricleindicator.model.Images;

public class ImagesViewPageAdapter extends PagerAdapter {
    private List<Images> imageList;

    public ImagesViewPageAdapter(List<Images> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_images, container, false);
        ImageView imageView = view.findViewById(R.id.imgView);
        Images images = imageList.get(position);
        imageView.setImageResource(images.getImageId());
        //add view
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageList != null ? imageList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
