package com.example.softwise.mytestutil;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Domain;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Isbn;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Or;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @NotEmpty(message = "不能为空")
    @Email(message = "邮箱格式错误")
    private EditText ed_email_empty;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_MIXED_CASE,message = "请输入大于6位的数字和字母组合")
    private EditText ed_password;

    @ConfirmPassword(message = "重新输入的密码错误，请重新确认！")
    private EditText ed_passwordConfirm;
    @Checked(message = "你必须点确定！！！")
    private CheckBox cb_agree;

    private Button btn_chech;
    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件绑定
        ed_email_empty= (EditText) findViewById(R.id.et_email_empty);
        ed_password= (EditText) findViewById(R.id.et_password);
        ed_passwordConfirm= (EditText) findViewById(R.id.et_passwordConfirm);
        cb_agree= (CheckBox) findViewById(R.id.cb_agree);
        btn_chech= (Button) findViewById(R.id.btn_commit);
        //验证
        validator=new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                Toast.makeText(MainActivity.this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error:errors){
                    View view=error.getView();
                    String message=error.getCollatedErrorMessage(MainActivity.this);

                    if (view instanceof EditText){
                        ((EditText) view).setError(message);
                    }else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //提交
        btn_chech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }
}
