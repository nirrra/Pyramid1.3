package com.example.tangjibin.pyramid.chakanyuyue;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.Main.MainActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.quxiaoyuyue;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class yuyuelist extends AppCompatActivity {
    private static final int UPDATE_TEXT1=1;
    private static final String ACTIVITY_TAG = "Demo";
    public static Yuyueinfo yuyueinfo=new Yuyueinfo("OName","DName");
    private List<Yuyueinfo> lists1 = new ArrayList<>();
    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT1:
                    YuyueAdapter adapter1 = new YuyueAdapter(yuyuelist.this,R.layout.yuyue_item,lists1);
                    ListView listView1 = findViewById(R.id.listview4);
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
        setContentView(R.layout.activity_yuyuelist);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final Button button3=findViewById(R.id.gotoyuyue);
        final Button button4=findViewById(R.id.gototips);
        button3.setBackgroundResource(R.drawable.button31);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyuelist.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyuelist.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyuelist.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button3.setBackgroundResource(R.drawable.button3);
            }
        });
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql="select * from order_info where Carer='"+ MainActivity.accountword+"'";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet1=statement.executeQuery(sql);

                        while(rSet1.next()){
                            yuyueinfo.setDoctorName(rSet1.getString("DName"));
                            yuyueinfo.setOldersName(rSet1.getString("OlderID"));
                            Yuyueinfo yuyueinfo2=new Yuyueinfo(yuyueinfo.getDoctorName(),yuyueinfo.getOldersName());
                            lists1.add(yuyueinfo2);
                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error");
                    }
                    Message message1=new Message();
                    message1.what=UPDATE_TEXT1;
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
        ListView listView1 = findViewById(R.id.listview4);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Yuyueinfo yuyueinfo=lists1.get(position);
                Intent intent1=new Intent();
                intent1.setClass(yuyuelist.this,quxiaoyuyue.class);
                startActivity(intent1);
                quxiaoyuyue.OldID=yuyueinfo.getOldersName();
                quxiaoyuyue.DocName=yuyueinfo.getDoctorName();
            }
        });
    }
}
