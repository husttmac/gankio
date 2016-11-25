package com.example.tmac.gankio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tmac.gankio.bean.AndroidBean;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.tmac.gankio.R.id.image;

/**
 * Created by Tmac on 2016/11/24.
 */

public class MyAdapter extends BaseAdapter {
    private List<AndroidBean> list;

    @Override
    public int getViewTypeCount() {
        return 3;
    }


    @Override
    public int getItemViewType(int position) {
        int type = 0;
        AndroidBean bean = list.get(position);
        if(bean.images == null || bean.images.size() == 0){
            type =0 ;
        }else if(bean.images.size() == 1){
            type = 1;
        }else{
            type = 2;
        }
        return  type;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item1,null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView who = (TextView) convertView.findViewById(R.id.who);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        title.setText(list.get(position).desc);
        who.setText(list.get(position).who);
        time.setText(list.get(position).publishedAt);
//            switch (getItemViewType(position)){
//                case 0:
//
//                    break;
////                case 1:
////                    break;
//                default:
//                    convertView = inflater.inflate(R.layout.item2,null);
//                    ImageView imageView = (ImageView) convertView.findViewById(image);
//                    TextView title1 = (TextView) convertView.findViewById(R.id.title);
//                    TextView who1 = (TextView) convertView.findViewById(R.id.who);
//                    TextView time1 = (TextView) convertView.findViewById(R.id.time);
//                    title1.setText(list.get(position).desc);
//                    who1.setText(list.get(position).who);
//                    time1.setText(list.get(position).publishedAt);
//                    Glide.with(parent.getContext())
//                            .load(list.get(position).images.get(0))
//                            .into(imageView);
//
//                    break;
//            }
//        }else{
//            switch (getItemViewType(position)){
//                case 0:
//                    TextView title = (TextView) convertView.findViewById(R.id.title);
//                    TextView who = (TextView) convertView.findViewById(R.id.who);
//                    TextView time = (TextView) convertView.findViewById(R.id.time);
//                    title.setText(list.get(position).desc);
//                    who.setText(list.get(position).who);
//                    time.setText(list.get(position).publishedAt);
//                    break;
////                case 1:
////                    break;
//                default:
//                    ImageView imageView = (ImageView) convertView.findViewById(image);
//                    TextView title1 = (TextView) convertView.findViewById(R.id.title);
//                    TextView who1 = (TextView) convertView.findViewById(R.id.who);
//                    TextView time1 = (TextView) convertView.findViewById(R.id.time);
//                    title1.setText(list.get(position).desc);
//                    who1.setText(list.get(position).who);
//                    time1.setText(list.get(position).publishedAt);
//                    Glide.with(parent.getContext())
//                            .load(list.get(position).images.get(0))
//                            .into(imageView);
//
//                    break;
//            }
//
//
//        }
        return convertView;
    }

    public void setData(List<AndroidBean> list){
        if(this.list == null){
            this.list =list;
        }else{
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }
}
