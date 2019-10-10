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

public class SlidingAdapter extends SliderViewAdapter<SlidingAdapter.SliderAdapterVH> {
    private Context context;

    public SlidingAdapter(Context context) {
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
                viewHolder.textViewDescription.setText("남윤철 교사");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_1)
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("최혜정 교사");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_2)
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("고창석 교사");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_3)
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                viewHolder.textViewDescription.setText("최덕하 학생");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_4)
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                viewHolder.textViewDescription.setText("정차웅 학생");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_5)
                        .into(viewHolder.imageViewBackground);
                break;
            case 5:
                viewHolder.textViewDescription.setText("양온유 학생");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_6)
                        .into(viewHolder.imageViewBackground);
                break;
            case 6:
                viewHolder.textViewDescription.setText("정현선 승무원");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_7)
                        .into(viewHolder.imageViewBackground);
                break;
            case 7:
                viewHolder.textViewDescription.setText("김기웅 직원");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_8)
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                viewHolder.textViewDescription.setText("박지영 승무원");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.images_9)
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 9;
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