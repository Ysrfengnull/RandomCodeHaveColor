package com.wangy.randomcodehavecolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText dode;
    private ImageView iv_showCode;
    private String realcode;
    //设置图片大小
    private static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }
    private void initview(){
        button = (Button) findViewById(R.id.but_forgetpass_toSetCodes);
        dode = (EditText)findViewById(R.id.et_phoneCodes);
        iv_showCode = (ImageView)findViewById(R.id.iv_showCode);
        iv_showCode.setImageBitmap(CodeBitmap.getInstance().cratebitmap(width,height));
        realcode = CodeBitmap.getInstance().getcode().toLowerCase();
        iv_showCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_showCode.setImageBitmap(CodeBitmap.getInstance().cratebitmap(width,height));
                realcode = CodeBitmap.getInstance().getcode().toLowerCase();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editcode=dode.getText().toString().trim();
                if (editcode.equals(realcode)){
                    Toast.makeText(MainActivity.this,"验证码输入正确！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"验证码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
