package com.example.anshulvanawat.onmytips;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anshul vanawat on 3/13/2017.
 */


public class customSwip extends PagerAdapter {

    public static String IMAGE_1_DESCRIPTION="Block Diagram";
    public static String IMAGE_2_DESCRIPTION="Home";
    public static String IMAGE_3_DESCRIPTION="Anshul vanawat";
    public static String IMAGE_4_DESCRIPTION="Archika singh";
    public static String IMAGE_5_DESCRIPTION="Laveena agarwal";
    public static String IMAGE_6_DESCRIPTION="khushal sharma";
    public static String IMAGE_7_DESCRIPTION="Kritima gupta";

    private int [] imageResources = {
            R.drawable.block_diagram,
            R.drawable.home,
            R.drawable.user_image,
            R.drawable.member_a,
            R.drawable.member_l,
            R.drawable.member_kh,
            R.drawable.member_kr,
            };

    private String [] imageDesc={IMAGE_1_DESCRIPTION,
    IMAGE_2_DESCRIPTION,
    IMAGE_3_DESCRIPTION,
    IMAGE_4_DESCRIPTION,
    IMAGE_5_DESCRIPTION,
    IMAGE_6_DESCRIPTION,
    IMAGE_7_DESCRIPTION};

    private Context ctx;
    private LayoutInflater layoutInflater;

    public customSwip(Context c) {
        ctx=c;
    }

    @Override
    public int getCount() {

        return imageResources.length;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.activity_custom_swip,container,false);
        ImageView imageView=(ImageView) itemView.findViewById(R.id.swip_image_view);
        TextView textView=(TextView) itemView.findViewById(R.id.imageDesc);
        imageView.setImageResource(imageResources[position]);
        textView.setText(imageDesc[position]);
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return  (view==object);
    }
}


