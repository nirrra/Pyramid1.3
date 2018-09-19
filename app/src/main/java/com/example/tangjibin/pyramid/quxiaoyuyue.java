package com.example.tangjibin.pyramid;

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
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.chakanyuyue.yuyuelist;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class quxiaoyuyue extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int UPDATE_TEXT=1;
    public static String OldID,DocName,Bed_num,OldName,condition=null;
    int Flag;
    private TextView name,bed,doctor,cond,zonghe;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    name.setText(OldName);
                    bed.setText(Bed_num);
                    doctor.setText(DocName);
                    cond.setText(condition);
                    zonghe.setText(OldName+" 预约医生:"+DocName);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quxiaoyuyue);
        name=findViewById(R.id.xingming10);
        bed=findViewById(R.id.chuanghao10);
        doctor=findViewById(R.id.yuyueyisheng);
        cond=findViewById(R.id.yuyuezhuangtai);
        zonghe=findViewById(R.id.zonghexinxi);

        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final Button button3=findViewById(R.id.gotoyuyue);
        final Button button4=findViewById(R.id.gototips);
        button3.setBackgroundResource(R.drawable.button31);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quxiaoyuyue.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quxiaoyuyue.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quxiaoyuyue.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                if(homeActivity.conn!=null){
                    String sql="SELECT * FROM basic_info where ID ='" + OldID +"'";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet=statement.executeQuery(sql);
                        while(rSet.next()){
                            OldName=rSet.getString("Name");
                            Bed_num=rSet.getString("Bed_num");

                        }
                        String sql1="SELECT * FROM order_info where OlderID ='" + OldID +"' and DName ='" + DocName+"'";
                        java.sql.Statement statement1=homeActivity.conn.createStatement();
                        ResultSet resultSet=statement.executeQuery(sql1);
                        while (resultSet.next()){
                            Flag=resultSet.getInt("Flag");
                            if(Flag==0){
                                condition="预约处理中";
                            }
                            else if(Flag==1){
                                condition="预约成功";
                            }
                            else if (Flag==2){
                                condition="预约失败";
                            }

                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error1"+e);
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

    public void returnTo(View view) {
        Intent intent=new Intent(quxiaoyuyue.this,yuyuelist.class);
        startActivity(intent);
    }

    public void quxiaoyuyue(View view) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {


                if(homeActivity.conn!=null){
                    String sql="DELETE FROM order_info where OlderID ='" + OldID +"' and DName ='" + DocName+"'";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        statement.executeUpdate(sql);
                        Intent intent=new Intent(quxiaoyuyue.this,yuyuelist.class);
                        startActivity(intent);

                    }catch (SQLException e ){
                        Log.e(ACTIVITY_TAG,"createStatement error2"+e);
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
        thread1.start();
    }
}
