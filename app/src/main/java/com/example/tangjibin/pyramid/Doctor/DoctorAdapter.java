package com.example.tangjibin.pyramid.Doctor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.tangjibin.pyramid.R;

import java.util.List;
public class DoctorAdapter extends ArrayAdapter<Doctor> {
    private int resourceID;
    public DoctorAdapter(Context context, int textViewResourceId, List<Doctor> objects){
        super(context,textViewResourceId,objects);
        resourceID=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Doctor doctor=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView doctorName=view.findViewById(R.id.Name001);
        TextView doctortel=view.findViewById(R.id.tel001);
        doctorName.setText(doctor.getName()+"医生，主治：");
        doctortel.setText(doctor.getMajor());

        return view;
    }

}
