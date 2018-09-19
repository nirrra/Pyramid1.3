package com.example.tangjibin.pyramid.Tips;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.chakanyuyue.Yuyueinfo;

import java.util.List;
public class TipsAdapter extends ArrayAdapter<info> {
    private int resourceID;
    public TipsAdapter(Context context, int textViewResourceId, List<info> objects){
        super(context,textViewResourceId,objects);
        resourceID=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        info info1=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView doctortel=view.findViewById(R.id.tips);
        doctortel.setText(info1.getInfo());
        return view;
    }
    }

