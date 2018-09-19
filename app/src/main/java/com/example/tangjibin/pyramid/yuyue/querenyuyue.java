package com.example.tangjibin.pyramid.yuyue;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.Main.MainActivity;
import com.example.tangjibin.pyramid.Main.QrActivity;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.yuyueinfo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class querenyuyue extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querenyuyue);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(querenyuyue.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(querenyuyue.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(querenyuyue.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
    }

    public void fanhui111(View view) {
        Intent intent=new Intent(querenyuyue.this,yuyue2.class);
        startActivity(intent);

    }

    public void shuruyuyue(View view) {
        final EditText describe=findViewById(R.id.describe);
        final String des=describe.getText().toString();
        int day = homeActivity.day;
        int month = homeActivity.month;
        int hour=homeActivity.hour;
        int minute=homeActivity.minute;
        final String date=String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(hour)+":"+String.valueOf(minute);
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                if (homeActivity.conn != null) {
                    try {
                        String sql1="insert into order_info (OlderID,DoctorID,Date,causes,Carer,DName) VALUES ('"+ QrActivity.oldersID+"','"+ yuyue2.DoctorID+ "','" + date+ "','" + des+ "','" + MainActivity.accountword+"','"+yuyue2.DoctorName +"')";
                            java.sql.Statement Statement=homeActivity.conn.createStatement();
                            Statement.executeUpdate(sql1);
                            Intent intent=new Intent(querenyuyue.this,yuyue2.class);
                            startActivity(intent);


                    } catch (SQLException e) {
                        Log.e(ACTIVITY_TAG, "createStatement error"+e);
                    }
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(ACTIVITY_TAG, "驱动成功");
                    } catch (ClassNotFoundException e) {
                        Log.e(ACTIVITY_TAG, "驱动失败");
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
                        Log.e(ACTIVITY_TAG, "远程连接成功！");
                    } catch (SQLException e) {
                        Log.e(ACTIVITY_TAG, "远程连接失败!");
                    }
                }
            }
        });thread.start();
    }
}
