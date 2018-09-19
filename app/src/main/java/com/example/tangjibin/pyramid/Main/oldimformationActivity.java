package com.example.tangjibin.pyramid.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.example.tangjibin.pyramid.chakanyuyue.yuyuelist;
import com.example.tangjibin.pyramid.older.oldersimformation;
import com.example.tangjibin.pyramid.yuyue.yuyue2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class oldimformationActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private TextView xingming,xingbie,bornyear,chuanghao,zhuzhiyisheng,lianxi1,lianxi2,fuzehugong,fengxianyinsu,fengxiandengji,zonghexinxi;
    private static final int UPDATE_TEXT=1;
    private static final int UPDATE_TEXT2=1;
    private String yizhut="";
    public static  oldersimformation oneolder=new oldersimformation();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    xingming.setText(oneolder.getoldersName());
                    xingbie.setText(oneolder.getSex());
                    bornyear.setText(oneolder.getbornyear());
                    chuanghao.setText(oneolder.getBednumber());
                    zhuzhiyisheng.setText(oneolder.getDoctor());
                    fuzehugong.setText(oneolder.getcarer());
                    lianxi1.setText(oneolder.getTel1());
                    lianxi2.setText(oneolder.getTel2());
                    fengxianyinsu.setText(oneolder.getRisk_factor());
                    fengxiandengji.setText(oneolder.getRisk_rating());
                    zonghexinxi.setText(oneolder.getBednumber()+"床 "+oneolder.getoldersName()+" "+oneolder.getSex());
                    break;
                default:
                    break;
            }
        }
    };

    private Handler handler2=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    yizhut+="病情："+oneolder.getPast_conditon()+"\n";
                    yizhut+="医嘱："+oneolder.getPast_advice()+"\n";
                    yizhut+="药品名称："+oneolder.getPast_prescription()+"\n";
                    yizhut+="药品用量："+oneolder.getPast_dosage();
                    break;
                default:
                    break;
            }
        }
    };

        public void returnto(View view) {
        Intent intent=new Intent(oldimformationActivity.this,quwanchengActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldimformation);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(oldimformationActivity.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(oldimformationActivity.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(oldimformationActivity.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        xingming=findViewById(R.id.xingming);
        xingbie=findViewById(R.id.xingbie);
        bornyear=findViewById(R.id.nianling);
        chuanghao=findViewById(R.id.chuanghao);
        zhuzhiyisheng=findViewById(R.id.zhuzhiyisheng);
        fuzehugong=findViewById(R.id.fuzehugong);
        lianxi1=findViewById(R.id.lianxi1);
        lianxi2=findViewById(R.id.lianxi2);
        fengxianyinsu=findViewById(R.id.fengxianyinsu);
        fengxiandengji=findViewById(R.id.fengxiandengji);
        zonghexinxi=findViewById(R.id.zonghexinxi);


        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    if(homeActivity.conn!=null){
                        String sql="SELECT * FROM basic_info where ID ='" + QrActivity.oldersID+"'";
                        try{
                            java.sql.Statement statement=homeActivity.conn.createStatement();
                            ResultSet rSet=statement.executeQuery(sql);
                            while(rSet.next()){
                                oneolder.setIDNum(rSet.getString("ID"));
                                oneolder.setolderName(rSet.getString("Name"));
                                oneolder.setSex(rSet.getString("Sex"));
                                oneolder.setbornyear(rSet.getString("Born_year"));
                                oneolder.setDoctor(rSet.getString("Doctor"));
                                oneolder.setTel1(rSet.getString("Tel1"));
                                oneolder.setTel2(rSet.getString("Tel2"));
                                oneolder.setBednumber(rSet.getString("Bed_num"));
                                oneolder.setRisk_rating(rSet.getString("Risk_rating"));
                                oneolder.setCarer(rSet.getString("carer"));
                                oneolder.setRisk_factor(rSet.getString("Risk_factor"));
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
                        }
                    }

            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(homeActivity.conn!=null){
                    String sql="SELECT * FROM import_info where ID ='" + QrActivity.oldersID+"'";
                    try{
                        java.sql.Statement statement=homeActivity.conn.createStatement();
                        ResultSet rSet=statement.executeQuery(sql);
                        while(rSet.next()){
                            oneolder.setPast_conditon(rSet.getString("Past_conditon"));
                            oneolder.setPast_advice(rSet.getString("Past_advice"));
                            oneolder.setPast_prescription(rSet.getString("Past_prescription"));
                            oneolder.setPast_dosage(rSet.getString("Past_dosage"));

                            rSet.close();
                        }
                    }catch (SQLException e){
                        Log.e(ACTIVITY_TAG,"createStatement error1");
                    }
                    Message message2=new Message();
                    message2.what=UPDATE_TEXT2;
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
                    }
                }

            }
        });
        thread.start();
        thread2.start();
        }

    public void chakanyizhu(View view){
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("今日医嘱");
        localBuilder.setIcon(R.mipmap.ic_launcher);
        localBuilder.setMessage(yizhut);
        localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
            }
        });

        localBuilder.setCancelable(false).create();

        localBuilder.show();
    }

    public void luruxinxi(View view) {
        Intent intent =new Intent(oldimformationActivity.this,luru.class);
        startActivity(intent);
    }

    public void yuyueYS(View view) {
            Intent intent=new Intent(oldimformationActivity.this,yuyue2.class);
            startActivity(intent);

    }

}

