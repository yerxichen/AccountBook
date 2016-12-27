package com.example.accountbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_excute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_add= (Button) findViewById(R.id.btn_acc_addacc);
        btn_excute= (Button) findViewById(R.id.btn_acc_acclist);
        btn_add.setOnClickListener(this);
        btn_excute.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_acc_addacc:
                startActivity(new Intent(this,AccountEntryActivity.class));
                break;
            case R.id.btn_acc_acclist:
                startActivity(new Intent(this,AccountListActivity.class));
                break;
        }
    }
}
