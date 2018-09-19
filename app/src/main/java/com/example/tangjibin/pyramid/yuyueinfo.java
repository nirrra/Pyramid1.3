package com.example.tangjibin.pyramid;

import android.content.Intent;
import android.os.Handler;
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
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.chakanyuyue.chakanName;
import com.example.tangjibin.pyramid.chakanyuyue.yuyuelist;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class yuyueinfo extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int UPDATE_TEXT=1;
    EditText shuruID1;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    shuruID1.setText("ID不存在，请确认输入");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyueinfo);
        shuruID1=findViewById(R.id.yuyueID);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final Button button3=findViewById(R.id.gotoyuyue);
        final Button button4=findViewById(R.id.gototips);
        button3.setBackgroundResource(R.drawable.button31);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyueinfo.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyueinfo.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyueinfo.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });
    }

    public void fanhui11(View view) {
        Intent intent=new Intent(yuyueinfo.this, yuyue.class);
        startActivity(intent);
    }

    public void shuruyuyue(View view) {
        final EditText shuruID=findViewById(R.id.yuyueID);
        final EditText describe=findViewById(R.id.describe);
        final String oldID=shuruID.getText().toString();
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
                    String sql="SELECT * FROM basic_info where ID='"+ oldID +"'";
                    try {
                        java.sql.Statement statement = homeActivity.conn.createStatement();
                        String sql1="insert into order_info (OlderID,DoctorID,Date,causes,Carer,DName) VALUES ('"+oldID+"','"+ yuyue.DoctorID+ "','" + date+ "','" + des+ "','" + MainActivity.accountword+"','"+yuyue.DoctorName +"')";
                        ResultSet rSet=statement.executeQuery(sql);
                        if(rSet.next()){
                            java.sql.Statement Statement2=homeActivity.conn.createStatement();
                            Statement2.executeUpdate(sql1);
                            Intent intent=new Intent(yuyueinfo.this,yuyue.class);
                            startActivity(intent);
                        }
                        else {
                            Message message=new Message();
                            message.what=UPDATE_TEXT;
                            handler.sendMessage(message);
                        }


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

    public void chakanyuyue(View view) {
        Intent intent=new Intent(yuyueinfo.this,yuyuelist.class);
        startActivity(intent);
    }
}
