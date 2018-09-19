package com.example.tangjibin.pyramid.Main;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.List.quwanchengActivity;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;

import java.sql.DriverManager;
import java.sql.SQLException;

import github.ishaan.buttonprogressbar.ButtonProgressBar;

public class luru extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "Demo";
    private static final int UPDATE_TEXT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luru);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        final ButtonProgressBar bar = (ButtonProgressBar) findViewById(R.id.today_inf);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.startLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar.stopLoader();
                    }
                }, 1666);
                luru();
            }
        });
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(luru.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(luru.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(luru.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });


        final EditText blood_p1=findViewById(R.id.blood_p1);
        final EditText blood_p2=findViewById(R.id.blood_p2);
        final EditText tempr=findViewById(R.id.temp);
        final int MIN_MARK=0;
        final int MAX_MARK=250;
        blood_p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1)
                {
                    if (MIN_MARK != -1 && MAX_MARK != -1)
                    {
                        double num = Double.parseDouble(s.toString());
                        if (num > MAX_MARK)
                            blood_p1.setText("");
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s != null && !s.equals(""))
                {
                    if (MIN_MARK != -1 && MAX_MARK != -1)
                    {
                        double markVal = 0;
                        try
                        {
                            markVal = Double.parseDouble(s.toString());
                        }
                        catch (NumberFormatException e)
                        {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK)
                        {
                            Toast.makeText(getBaseContext(), "请确认输入数值", Toast.LENGTH_SHORT).show();
                            blood_p1.setText("");
                        }
                        return;
                    }
                }
            }
        });
        blood_p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1)
                {
                    if (MIN_MARK != -1 && MAX_MARK != -1)
                    {
                        Double num = Double.parseDouble(s.toString());
                        if (num > MAX_MARK)
                            blood_p2.setText("");
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s != null && !s.equals(""))
                {
                    if (MIN_MARK != -1 && MAX_MARK != -1)
                    {
                        double markVal = 0;
                        try
                        {
                            markVal = Double.parseDouble(s.toString());
                        }
                        catch (NumberFormatException e)
                        {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK)
                        {
                            Toast.makeText(getBaseContext(), "请确认输入数值", Toast.LENGTH_SHORT).show();
                            blood_p2.setText("");
                        }
                        return;
                    }
                }
            }
        });
        final int MIN_TEP=35;
        final int MAX_TMP=42;
        tempr.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1)
                {
                    if (MIN_TEP != -1 && MAX_TMP != -1)
                    {
                        double num = Double.parseDouble(s.toString());
                        if (num > MAX_TMP)
                            tempr.setText("");
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s != null && !s.equals(""))
                {
                    if (MIN_TEP != -1 && MAX_TMP != -1)
                    {
                        double markVal = 0;
                        try
                        {
                            markVal = Double.parseDouble(s.toString());
                        }
                        catch (NumberFormatException e)
                        {
                            markVal = 0;
                        }
                        if (markVal > MAX_TMP)
                        {
                            Toast.makeText(getBaseContext(), "请确认输入数值", Toast.LENGTH_SHORT).show();
                            tempr.setText("");
                        }
                        return;
                    }
                }
            }
        });



    }

    public void luru(/*View view*/) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                EditText blood_p1 = findViewById(R.id.blood_p1);
                EditText blood_p2 = findViewById(R.id.blood_p2);
                EditText tempr = findViewById(R.id.temp);
                EditText defecation = findViewById(R.id.defecation);
                RatingBar ratingBar1 = findViewById(R.id.diet);
                RatingBar ratingBar2 = findViewById(R.id.slumber);
                EditText other = findViewById(R.id.otherinf);
                String blood1 = blood_p1.getText().toString();
                String blood2 = blood_p2.getText().toString();
                String temprature = tempr.getText().toString();
                String defecation1 = defecation.getText().toString();
                float diet = ratingBar1.getRating();
                float slumber = ratingBar2.getRating();
                String otherinf = other.getText().toString();
                String ID = QrActivity.oldersID;
                int day = homeActivity.day;
                int month = homeActivity.month;
                int year = homeActivity.year;
                if (homeActivity.conn != null) {
                    String sql = "insert into import_info (Date,ID,Blood_p1,Blood_p2,Temp,Defecation,Diet,Slumber,Other_info) VALUES ('" + year + '年' + month + '月' + day + '日' + "','" + ID + "','" + blood1 + "','" + blood2 + "','" + temprature + "','" + defecation1 + "','" + diet + "','" + slumber + "','" + otherinf + "')";
                    String sql1 = "update basic_info set Flag = 1 where ID ='" + ID + "'";
                    try {
                        java.sql.Statement statement = homeActivity.conn.createStatement();
                        statement.executeUpdate(sql);
                        statement.executeUpdate(sql1);

                    } catch (SQLException e) {
                        Log.e(ACTIVITY_TAG, "createStatement error" + e);
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


        });
        thread.start();
       // Intent intent=new Intent(luru.this,quwanchengActivity.class);
       // startActivity(intent);
    }

    public void returnto(View view) {
        Intent intent=new Intent(luru.this,quwanchengActivity.class);
        startActivity(intent);
    }
}
