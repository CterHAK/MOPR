package vn.iotstar.recyclerviewwithindicator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.recyclerviewwithindicator.R;
import vn.iotstar.recyclerviewwithindicator.model.IconModel;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
    private Context context;
    private List<IconModel> arrayList;

    public IconAdapter(Context context, List<IconModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public IconAdapter.IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_promotion, parent,false);
        return new IconHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAdapter.IconHolder holder, int position) {
        IconModel iconModel = arrayList.get(position);
        Glide.with(context).load(iconModel.getImgId()).into(holder.imgIcon);
        holder.tvIconName.setText(iconModel.getDesc());
    }

    @Override
    public int getItemCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    public void setListenerList(List<IconModel> newList) {
        this.arrayList = new ArrayList<>(newList); // Cập nhật danh sách mới
        notifyDataSetChanged(); // Cập nhật RecyclerView
    }

    public class IconHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView tvIconName;
        public IconHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.ivImgIcon);
            tvIconName = itemView.findViewById(R.id.tvIcon);
        }
    }
}
