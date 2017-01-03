package com.example.accountbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_add;
    private TextView tv_excute;
    private TextView tv_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_add= (TextView) findViewById(R.id.tv_acc_addacc);
        tv_excute= (TextView) findViewById(R.id.tv_acc_acclist);
        tv_select= (TextView) findViewById(R.id.tv_acc_select);
        tv_add.setOnClickListener(this);
        tv_excute.setOnClickListener(this);
        tv_select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_acc_addacc:
                startActivity(new Intent(this,AccountEntryActivity.class));
                break;
            case R.id.tv_acc_acclist:
                startActivity(new Intent(this,AccountListActivity.class));
                break;
            case R.id.tv_acc_select:
                startActivity(new Intent(this,AccountAdminActivity.class));
        }
    }
}
