package com.example.tangjibin.pyramid.Tips;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.R;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tips extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int UPDATE_TEXT=1;
    public static info info1=new info("imformation");
    private List<info> lists1 = new ArrayList<>();
    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT:
                    TipsAdapter adapter1 = new TipsAdapter(Tips.this,R.layout.tips_item,lists1);
                    ListView listView1 = findViewById(R.id.info01);
                    listView1.setAdapter(adapter1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final Button button3=findViewById(R.id.gotoyuyue);
        final Button button4=findViewById(R.id.gototips);
        button4.setBackgroundResource(R.drawable.button41);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Tips.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button4.setBackgroundResource(R.drawable.button4);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Tips.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button4.setBackgroundResource(R.drawable.button4);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Tips.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button4.setBackgroundResource(R.drawable.button4);
            }
        });

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql="select * from tips";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet1=statement.executeQuery(sql);
                        while(rSet1.next()){
                            info1.setInfo(rSet1.getString("imformation"));
                            info info2=new info(info1.getInfo());
                            lists1.add(info2);
                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error");
                    }
                    Message message1=new Message();
                    message1.what=UPDATE_TEXT;
                    handler1.sendMessage(message1);
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
                        Log.i(ACTIVITY_TAG,"连接成功");
                    } catch (SQLException e) {
                        Log.e(ACTIVITY_TAG,e.toString());
                        Log.e(ACTIVITY_TAG,"连接失败");
                    }
                }
            }
        });
        thread1.start();

    }
}
