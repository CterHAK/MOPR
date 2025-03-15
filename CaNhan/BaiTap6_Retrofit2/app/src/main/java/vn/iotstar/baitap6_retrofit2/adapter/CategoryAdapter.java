package vn.iotstar.baitap6_retrofit2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.baitap6_retrofit2.R;
import vn.iotstar.baitap6_retrofit2.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (categoryList == null || categoryList.isEmpty()) {
            Log.e("CategoryAdapter", "Danh sách category rỗng!");
            return;
        }

        Category cate = categoryList.get(position);
        holder.textV.setText(cate.getName());
        //load ảnh với Glide
        Glide.with(context)
                .load(cate.getImage())
                .into(holder.imgV);
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgV;
        public TextView textV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV = (ImageView) itemView.findViewById(R.id.image_cate);
            textV = (TextView) itemView.findViewById(R.id.textViewNameCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Ban da chon category: "+ textV.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
