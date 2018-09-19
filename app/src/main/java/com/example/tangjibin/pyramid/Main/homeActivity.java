package com.example.tangjibin.pyramid.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

public class homeActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int msgKey1 = 1;
    private static final int UPDATE_TEXT1=1;
    public static Connection conn;
    private TextView mTime,Name;
    int count1=0;
    public static int day,month,hour,minute,year;
    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT1:
                    TextView textView1=findViewById(R.id.lastOldText);
                    textView1.setText(" "+Integer.toString(count1)+" ");
                    break;
                default:
                    break;
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Name=findViewById(R.id.text_title);
        Name.setText("Hello, "+ MainActivity.accountword+" !");
        mTime = findViewById(R.id.time);
        new TimeThread().start();
        final Button button1=findViewById(R.id.gotohome);
        button1.setBackgroundResource(R.drawable.button11);
        final Button button2=findViewById(R.id.gotolist);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this,quwanchengActivity.class);
                startActivity(intent);
                button2.setBackgroundResource(R.drawable.button21);
                button1.setBackgroundResource(R.drawable.button1);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button1.setBackgroundResource(R.drawable.button1);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeActivity.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button1.setBackgroundResource(R.drawable.button1);
            }
        });
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Log.v(ACTIVITY_TAG,"驱动成功");
        }catch (ClassNotFoundException e){
            Log.e(ACTIVITY_TAG,"驱动失败");
            return;
        }
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
               String ip = "tangjibin.mysql.rds.aliyuncs.com";
                int port = 3306;
                String dbName = "ex1";
                String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
                String user = "tangjibin";
                String password = "Jiaohua98";

                conn=null;

                try {
                    conn = DriverManager.getConnection(url, user, password);
                    Log.i(ACTIVITY_TAG,"连接成功");
                } catch (SQLException e) {
                    Log.e(ACTIVITY_TAG,e.toString());
                    Log.e(ACTIVITY_TAG,"连接失败");
                }

                if(conn!=null){
                    String sql="select * from basic_info where Flag=0 and Carer='"+MainActivity.accountword+"'";

                    try{
                        java.sql.Statement statement=conn.createStatement();
                        ResultSet rSet1=statement.executeQuery(sql);
                        while (rSet1.next()){
                            count1++;
                        }

                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error");
                    }


                    Message message1=new Message();
                    message1.what=UPDATE_TEXT1;
                    handler1.sendMessage(message1);
                }
            }
        });
        thread3.start();
    }
    public void zhuxiao(View view) {
        Intent intent=new Intent(homeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void quwancheng(View view) {
        Intent intent=new Intent(homeActivity.this,quwanchengActivity.class);
        startActivity(intent);
    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }


    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    Calendar c = Calendar.getInstance();
                    c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                    month = c.get(Calendar.MONTH) + 1;
                    day = c.get(Calendar.DAY_OF_MONTH);
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                    year=c.get(Calendar.YEAR);
                    if(hour<10&&minute<10){
                        mTime.setText(Html.fromHtml("<big>"+"<big>"+"0"+hour+":"+"0"+minute+"</big>"+"</big>"
                                +"<br/>"+"<small>"+"<small>"+month+"月"+day+"日"+"</small>"+"</small>")); }
                    else if(hour<10&&minute>=10){
                        mTime.setText(Html.fromHtml("<big>"+"<big>"+"0"+hour+":"+minute+"</big>"+"</big>"
                                +"<br/>"+"<small>"+"<small>"+month+"月"+day+"日"+"</small>"+"</small>")); }
                    else if(hour>=10&&minute<10){
                        mTime.setText(Html.fromHtml("<big>"+"<big>"+hour+":"+"0"+minute+"</big>"+"</big>"
                                +"<br/>"+"<small>"+"<small>"+month+"月"+day+"日"+"</small>"+"</small>")); }
                    else {
                        mTime.setText(Html.fromHtml("<big>" + "<big>" + hour + ":" + minute + "</big>" + "</big>"
                                + "<br/>" + "<small>" + "<small>" + month + "月" + day + "日" + "</small>" + "</small>")); }
                    break;
                default:
                    break;
            }
        }
    };



}
