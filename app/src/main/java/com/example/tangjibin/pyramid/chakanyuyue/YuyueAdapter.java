package com.example.tangjibin.pyramid.chakanyuyue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.tangjibin.pyramid.R;
import java.util.List;

public class YuyueAdapter extends ArrayAdapter<Yuyueinfo> {
    private int resourceID;
    public YuyueAdapter(Context context, int textViewResourceId, List<Yuyueinfo> objects){
        super(context,textViewResourceId,objects);
        resourceID=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Yuyueinfo yuyueinfo=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView doctorName=view.findViewById(R.id.OName);
        TextView doctortel=view.findViewById(R.id.DName);
        doctorName.setText("ID："+yuyueinfo.getOldersName()+" 预约医生：");
        doctortel.setText(yuyueinfo.getDoctorName());
        return view;
    }

}
