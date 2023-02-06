package com.example.buttonbeautificationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText et_input;
    private Button btn_empty;
    private Button btn_length;
    private Button btn_digits;
    private Button btn_ellipzise;
    private TextView tv_result;
    private ScrollView sv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = (EditText) findViewById(R.id.et_input);

        btn_empty = (Button) findViewById(R.id.btn_empty);
        btn_length = (Button) findViewById(R.id.btn_length);
        btn_digits = (Button) findViewById(R.id.btn_digits);
        btn_ellipzise = (Button) findViewById(R.id.btn_ellipzise);

        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_empty.setOnClickListener(this);
        btn_length.setOnClickListener(this);
        btn_digits.setOnClickListener(this);
        btn_ellipzise.setOnClickListener(this);

        sv_test = (ScrollView) findViewById(R.id.sv_test);
        sv_test.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sv_test.post(new Runnable() {
                    @Override
                    public void run() {
                        sv_test.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_empty) {
            boolean isEmpty = TextUtils.isEmpty(et_input.getText());
            String desc = String.format("输入框的文本%s空的\n", isEmpty ? "是":"不是");
            //tv_result.setText(desc);
            tv_result.append(desc);
        }
        else if (view.getId() == R.id.btn_length) {
            int length = TextUtils.getTrimmedLength(et_input.getText());
            String desc = String.format("输入框的文本去掉左右空格后的长度是%d\n", length);
            //tv_result.setText(desc);
            tv_result.append(desc);
        }
        else if (view.getId() == R.id.btn_digits) {
            boolean isDigits = TextUtils.isDigitsOnly(et_input.getText());
            String desc = String.format("输入框的文本%s纯数字\n", isDigits ? "是":"不是");
            //tv_result.setText(desc);
            tv_result.append(desc);
        }
        else if (view.getId() == R.id.btn_ellipzise) {
            float f = et_input.getTextSize() * 10;
            CharSequence charSequence = TextUtils.ellipsize(et_input.getText(), et_input.getPaint(), f, TextUtils.TruncateAt.END);
            String desc = String.format("输入框的文本加省略号的样式为：%s \n", charSequence);
            //tv_result.setText(desc);
            tv_result.append(desc);
        }
    }
}