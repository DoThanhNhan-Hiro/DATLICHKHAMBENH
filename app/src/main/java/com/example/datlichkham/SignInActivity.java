package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.datlichkham.admin.AdminActivity;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    TextInputEditText edtUsername;
    TextInputEditText edtPassword;
    TextInputLayout tilUsername;
    TextInputLayout tilPassword;
    MaterialButton btnSignIn;
    AccountDAO accountDAO;
    MaterialCheckBox chkRememberPass;
    ArrayList<AccountDTO> listAcc;
    SharedPreferences sharedPreferences;
//    ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            save();
//        }
//    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtUsername = (TextInputEditText) findViewById(R.id.edt_username);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password);
        tilUsername = (TextInputLayout) findViewById(R.id.til_username);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        btnSignIn = (MaterialButton) findViewById(R.id.btn_sign_in);
        chkRememberPass = (MaterialCheckBox) findViewById(R.id.chk_remember_pass);
        accountDAO = new AccountDAO(this);
        listAcc = accountDAO.getAll();
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME", "");
        String pass = sharedPreferences.getString("PASSWORD", "");
        Boolean rem = sharedPreferences.getBoolean("REMEMBER", false);

        edtUsername.setText(user);
        edtPassword.setText(pass);
        chkRememberPass.setChecked(rem);
        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUsername.getText().toString().trim();
                String passWord = edtPassword.getText().toString().trim();
                boolean checkLogin = accountDAO.checkLogin(userName, passWord);
                if (userName.isEmpty() || passWord.isEmpty()) {
                    tilUsername.setError("Vui lòng không để trống");
                    tilPassword.setError("Vui lòng không để trống");
                    ErrorAnimaton(tilUsername);
                    ErrorAnimaton(tilPassword);
//                    ErrorAnimaton2(tilPassword,100);
                } else {
                    tilUsername.setError("");
                    tilPassword.setError("");
                    if (checkLogin == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("getIdUser", MODE_PRIVATE);
                        String fullname = sharedPreferences.getString("fullname", "");
                        rememberUser(userName, passWord, chkRememberPass.isChecked());
                        tilUsername.setError("");
                        tilPassword.setError("");
                        String checkRole = sharedPreferences.getString("role", "");
                        if (checkRole.equalsIgnoreCase("Admin")) {
                            Intent intent = new Intent(SignInActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else if (checkRole.equalsIgnoreCase("User")) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("fullname", fullname);
                            startActivity(intent);
                        }
                    }else{
                        ErrorAnimaton2(tilPassword,100);
                        tilPassword.setError("Sai mật khẩu!");
                    }
                }
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        read();
//    }

    public void save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("username", tilUsername.getEditText().getText().toString());
        String a = tilPassword.getEditText().getText().toString();
        editor.putString("password", a);
        editor.putBoolean("check", chkRememberPass.isChecked());
        editor.commit();
    }

    public void read() {
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean a = sharedPreferences.getBoolean("check", false);
        tilUsername.getEditText().setText(user);
        if (a == true) {
            tilPassword.getEditText().setText(pass);
            chkRememberPass.setChecked(true);
        }
    }

    public void rememberUser(String user, String pass, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status){
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }

    public void ErrorAnimaton(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.amin_error);
        view.setAnimation(animation);
    }
    public void ErrorAnimaton2(View view,long delay ){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.amin_error);
        view.setAnimation(animation);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startSignIn = new Intent(Intent.ACTION_MAIN);
        startSignIn.addCategory(Intent.CATEGORY_HOME);
        startActivity(startSignIn);
        finish();
    }
}