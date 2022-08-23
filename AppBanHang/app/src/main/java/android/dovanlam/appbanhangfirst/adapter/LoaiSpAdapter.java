package android.dovanlam.appbanhangfirst.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.dovanlam.appbanhangfirst.model.LoaiSp;
import android.os.Bundle;

import android.dovanlam.appbanhangfirst.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LoaiSpAdapter extends BaseAdapter {

    List<LoaiSp> array;
    Context context;

    public LoaiSpAdapter(List<LoaiSp> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHodel{
        TextView textNameSp;
        ImageView imgImage;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHodel viewHodel = null;
        if(view == null){
            viewHodel = new ViewHodel();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_product, null);
            viewHodel.textNameSp = view.findViewById(R.id.nameProduct);
            viewHodel.imgImage = view.findViewById(R.id.item_img);
            view.setTag(viewHodel);
        }
        else{
            viewHodel = (ViewHodel) view.getTag();
            viewHodel.textNameSp.setText(array.get(i).getNameSp());
            Glide.with(context).load(array.get(i).getImg()).into(viewHodel.imgImage);
        }

        return view;
    }
}