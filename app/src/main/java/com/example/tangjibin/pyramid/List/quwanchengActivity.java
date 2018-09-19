package com.example.tangjibin.pyramid.List;

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

import com.example.tangjibin.pyramid.Main.MainActivity;
import com.example.tangjibin.pyramid.Main.QrActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.xiugai.olderimformationActivity2;
import com.example.tangjibin.pyramid.Main.oldimformationActivity;
import com.example.tangjibin.pyramid.Doctor.yuyue;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class quwanchengActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int UPDATE_TEXT1=1;
    private static final int UPDATE_TEXT2=1;
    public static User muser =new User("Bed_num","Name","Sex","ID");
    private List<User> lists1 = new ArrayList<>();
    private List<User> lists2 = new ArrayList<>();

    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT1:
                    UserAdapter adapter1 = new UserAdapter(quwanchengActivity.this,R.layout.user_item,lists1);
                    ListView listView1 = findViewById(R.id.listview1);
                    listView1.setAdapter(adapter1);
                    break;
                default:
                    break;
            }
        }
    };

    private Handler handler2=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT2:
                    UserAdapter adapter2 = new UserAdapter(quwanchengActivity.this,R.layout.user2_item,lists2);
                    ListView listView2 = findViewById(R.id.listview2);
                    listView2.setAdapter(adapter2);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quwancheng);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quwanchengActivity.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quwanchengActivity.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(quwanchengActivity.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });



        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql="select * from basic_info where Flag=0 and Carer='"+MainActivity.accountword+"'";
                    //String sql="select * from basic_info where Flag=0";

                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet1=statement.executeQuery(sql);

                        while(rSet1.next()){
                            muser.setBed_num(rSet1.getString("Bed_num"));
                            muser.setName(rSet1.getString("Name"));
                            muser.setSex(rSet1.getString("Sex"));
                            muser.setID(rSet1.getString("ID"));
                            User users = new User(muser.getBed_num(),muser.getName(),muser.getSex(),muser.getID());
                            lists1.add(users); }
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
                    }catch (ClassNotFoundException e){
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
                }
            }


                }
        });


        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql2="select * from basic_info where Flag=1 and Carer='"+MainActivity.accountword+"'";
                    //String sql2="select * from basic_info where Flag=1";

                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet2=statement.executeQuery(sql2);

                        while(rSet2.next()){
                            muser.setBed_num(rSet2.getString("Bed_num"));
                            muser.setName(rSet2.getString("Name"));
                            muser.setSex(rSet2.getString("Sex"));
                            muser.setID(rSet2.getString("ID"));
                            User users = new User(muser.getBed_num(),muser.getName(),muser.getSex(),muser.getID());
                            lists2.add(users);
                            Log.i(ACTIVITY_TAG,rSet2.getString("Name"));
                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error");
                    }
                    Message message2=new Message();
                    message2.what=UPDATE_TEXT1;
                    handler2.sendMessage(message2);


                }
                else {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(ACTIVITY_TAG,"驱动成功");
                    }catch (ClassNotFoundException e){
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
                        Log.e(ACTIVITY_TAG,e.toString());
                    }
                }
            }
        });
        thread1.start();
        thread2.start();

        ListView listView1 = findViewById(R.id.listview1);
        ListView listView2 = findViewById(R.id.listview2);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User muser=lists1.get(position);
                Intent intent1=new Intent();
                intent1.setClass(quwanchengActivity.this,oldimformationActivity.class);
                startActivity(intent1);
                QrActivity.oldersID=muser.getID();
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User muser=lists2.get(position);
                Intent intent2=new Intent();
                intent2.setClass(quwanchengActivity.this,olderimformationActivity2.class);
                startActivity(intent2);
                QrActivity.oldersID=muser.getID();
            }
        });

    }

}
