package vn.iotstar.viewflippercricleindicator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.iotstar.viewflippercricleindicator.R;
import vn.iotstar.viewflippercricleindicator.model.Images;

public class ImagesViewPager2Adapter extends RecyclerView.Adapter<ImagesViewPager2Adapter.ImagesViewHolder> {
    private List<Images> imagesList;

    public ImagesViewPager2Adapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewPager2Adapter.ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images,parent,false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewPager2Adapter.ImagesViewHolder holder, int position) {
        //Set du lieu
        Images images = imagesList.get(position);
        if(images==null){
            return;
        }
        holder.imageView.setImageResource(images.getImageId());
    }

    @Override
    public int getItemCount() {
        return imagesList!=null ? imagesList.size() : 0;
    }
    public class ImagesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
