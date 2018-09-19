package com.example.tangjibin.pyramid.yuyue;

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

import com.example.tangjibin.pyramid.Doctor.Doctor;
import com.example.tangjibin.pyramid.Doctor.DoctorAdapter;
import com.example.tangjibin.pyramid.Doctor.Doctorimformation;
import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.Main.homeActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class yuyue2 extends AppCompatActivity {
    public static String DoctorID,DoctorName;
    private static final int UPDATE_TEXT1=1;
    private static final String ACTIVITY_TAG = "Demo";
    public static Doctor doctor=new Doctor("ID","Name","address","tel","email","zhuzhi");
    private List<Doctor> lists1 = new ArrayList<>();
    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE_TEXT1:
                    DoctorAdapter adapter1 = new DoctorAdapter(yuyue2.this,R.layout.doctor_item,lists1);
                    ListView listView1 = findViewById(R.id.listview5);
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
        setContentView(R.layout.activity_yuyue2);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyue2.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyue2.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yuyue2.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql="select * from doc_info";

                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet1=statement.executeQuery(sql);

                        while(rSet1.next()){
                            doctor.setID(rSet1.getString("ID"));
                            doctor.setName(rSet1.getString("Name"));
                            doctor.setAddress(rSet1.getString("address"));
                            doctor.setTel(rSet1.getString("tel"));
                            doctor.setEmail(rSet1.getString("email"));
                            doctor.setMajor(rSet1.getString("zhuzhi"));
                            Doctor doctor2=new Doctor(doctor.getID(),doctor.getName(),doctor.getAddress(),doctor.getTel(),doctor.getEmail(),doctor.getMajor());
                            lists1.add(doctor2); }

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
        ListView listView1 = findViewById(R.id.listview5);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor doctor=lists1.get(position);
                Intent intent1=new Intent();
                intent1.setClass(yuyue2.this,querenyuyue.class);
                startActivity(intent1);
                DoctorID=doctor.getID();
                DoctorName=doctor.getName();

            }
        });

    }
}
