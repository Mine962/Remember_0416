package com.dgsw.remember.SlideAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dgsw.remember.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SlidingAnother extends SliderViewAdapter<SlidingAnother.SliderAdapterVH> {
    private Context context;

    public SlidingAnother(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("[서울] 노란리본 운동");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slide)
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("[안산] 노란리본 운동");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slide2)
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("[대구] 노란리본 운동");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slide3)
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                viewHolder.textViewDescription.setText("[대구] 노란리본 운동");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slide4)
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        TextView textViewDescription;
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}