package com.oblivion.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TagFlowLayout tagFlowLayout;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};
    private List<String> list = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("asdas");
        list.add("asdas");
        list.add("asdas");
        list.add("asdas");
        list.add("asdas");
        list.add("asdas");
        ImageView iv = (ImageView) findViewById(R.id.iv);
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
        Glide.with(this).load(R.mipmap.ic_launcher)
                .centerCrop()
                .transform(new CircleTransform(this))
                .into(iv);
        tagFlowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv,
                        tagFlowLayout, false);
                tv.setText(o);
                return tv;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                list.add("sdaswwwww");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "sddf", Toast.LENGTH_SHORT).show();
                        tagFlowLayout.getAdapter().notifyDataChanged();
                    }
                });
            }
        }).start();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TongxunuActivity.class));
            }
        });
    }
}
