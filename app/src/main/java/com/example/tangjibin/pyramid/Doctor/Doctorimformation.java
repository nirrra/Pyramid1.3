package com.example.tangjibin.pyramid.Doctor;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.yuyueinfo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctorimformation extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private TextView xingming,bangongshi,dianhua,email,zonghe;
    private static final int UPDATE_TEXT=1;
    public static Doctorinfo onedoctor=new Doctorinfo();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    xingming.setText(onedoctor.getName());
                    bangongshi.setText(onedoctor.getAddress());
                    dianhua.setText(onedoctor.getTel());
                    email.setText(onedoctor.getEmail());
                    zonghe.setText(onedoctor.getName()+"医生");
                     break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorimformation);
        xingming=findViewById(R.id.xingming);
        bangongshi=findViewById(R.id.bangongshi);
        dianhua=findViewById(R.id.dianhua);
        email=findViewById(R.id.email);
        zonghe=findViewById(R.id.zonghexinxi);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final Button button3=findViewById(R.id.gotoyuyue);
        final Button button4=findViewById(R.id.gototips);
        button3.setBackgroundResource(R.drawable.button31);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Doctorimformation.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Doctorimformation.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Doctorimformation.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                if(homeActivity.conn!=null){
                    String sql="SELECT * FROM doc_info where ID ='" + yuyue.DoctorID+"'";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet=statement.executeQuery(sql);
                        while(rSet.next()){

                            onedoctor.setName(rSet.getString("Name"));
                            onedoctor.setAddress(rSet.getString("address"));
                            onedoctor.setEmail(rSet.getString("email"));
                            onedoctor.setTel(rSet.getString("tel"));
                            rSet.close();
                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error1");
                    }
                    Message message=new Message();
                    message.what=UPDATE_TEXT;
                    handler.sendMessage(message);
                }
                else {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(ACTIVITY_TAG,"驱动成功");
                    }catch (ClassNotFoundException e) {
                        Log.e(ACTIVITY_TAG,"驱动失败");
                        return;
                    }
                        String ip = "tangjibin.mysql.rds.aliyuncs.com";
                        int port = 3306;
                        String dbName = "ex1";
                        String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
                        String user = "tangjibin";
                        String password = "Jiaohua98";
                        try {
                            homeActivity.conn = DriverManager.getConnection(url, user, password);
                            run();
                        } catch (SQLException e) {
                        }
                    }


            }
        });
        thread.start();
    }


    public void returnto1(View view) {
        Intent intent=new Intent(Doctorimformation.this,homeActivity.class);
        startActivity(intent);
    }

    public void yuyueyisheng(View view) {
        Intent intent=new Intent(Doctorimformation.this,yuyueinfo.class);
        startActivity(intent);
    }
}

