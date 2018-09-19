package com.example.tangjibin.pyramid.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tangjibin.pyramid.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User>{
    private int resourcedId;
    public UserAdapter(Context context, int textViewResourceId,List<User> objects){
        super(context,textViewResourceId,objects);
        resourcedId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        User muser=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourcedId,parent,false);
        TextView userID=view.findViewById(R.id.Bed_num);
        TextView userName=view.findViewById(R.id.Name);
        TextView userSex=view.findViewById(R.id.Sex);
        userID.setText(muser.getBed_num()+"床");
        userName.setText(muser.getName());
        userSex.setText(muser.getSex());

        return view;
    }

}
