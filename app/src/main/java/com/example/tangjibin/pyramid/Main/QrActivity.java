package com.example.tangjibin.pyramid.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.tangjibin.pyramid.Doctor.yuyue;
import com.example.tangjibin.pyramid.R;
import com.example.tangjibin.pyramid.Tips.Tips;
import com.yzq.zxinglibrary.android.CaptureActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.yzq.zxinglibrary.encode.CodeCreator;
import com.google.zxing.WriterException;

public class QrActivity extends AppCompatActivity {

    private int REQUEST_CODE_SCAN=111;
    public static String oldersID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        final Button button2=findViewById(R.id.gotolist);
        final Button button1=findViewById(R.id.gotohome);
        button2.setBackgroundResource(R.drawable.button21);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QrActivity.this,homeActivity.class);
                startActivity(intent);
                button1.setBackgroundResource(R.drawable.button11);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button3=findViewById(R.id.gotoyuyue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QrActivity.this,yuyue.class);
                startActivity(intent);
                button3.setBackgroundResource(R.drawable.button31);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });
        final Button button4=findViewById(R.id.gototips);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QrActivity.this,Tips.class);
                startActivity(intent);
                button4.setBackgroundResource(R.drawable.button41);
                button2.setBackgroundResource(R.drawable.button2);
            }
        });

        Button button=findViewById(R.id.buttontoqr);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QrActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);
            }
        });

        Button Create=findViewById(R.id.create);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText contentEt=findViewById(R.id.creatqrEt) ;
                String contentEtString = contentEt.getText().toString().trim();
                if (TextUtils.isEmpty(contentEtString)) {
                    Toast.makeText(QrActivity.this, "contentEtString不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap = null;
                try {
                    Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    bitmap = CodeCreator.createQRCode(contentEtString, 400, 400, logo);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                ImageView imageView=(ImageView)findViewById(R.id.image_view);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });




    }

    public void chaxunID(View view) {
        EditText chaxun=findViewById(R.id.creatqrEt);
        oldersID=chaxun.getText().toString();
        Intent intent2=new Intent(QrActivity.this,oldimformationActivity.class);
        startActivity(intent2);
    }
}
