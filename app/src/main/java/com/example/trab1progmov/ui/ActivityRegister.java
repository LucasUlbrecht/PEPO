package com.example.trab1progmov.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trab1progmov.MainActivity;
import com.example.trab1progmov.R;
import com.example.trab1progmov.ui.AdminMainActivity;
import com.example.trab1progmov.ORM.Database.AppDatabase;
import com.example.trab1progmov.ORM.Entity.TipoAcesso;
import com.example.trab1progmov.ORM.Entity.User;
import com.example.trab1progmov.ORM.Dao.UserDao;
import com.example.trab1progmov.UserSession;
import com.example.trab1progmov.crypto.CryptoUtil;

public class ActivityRegister extends AppCompatActivity {

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDao = AppDatabase.getInstance(getApplicationContext()).userDao();

        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    registerUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void registerUser() throws Exception {
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        String encryptedPassword = CryptoUtil.encrypt(password);

        User user = new User(null, email, password, null, null, null, 0, null, null);
        user.setEmail(email);
        user.setSenha(encryptedPassword);

        if (email.startsWith("$")) {
            showAdminPasswordPrompt(user);
        } else {
            user.setAcesso(TipoAcesso.PUBLIC);
            insertUser(user);
            navigateToMainActivity();
        }
    }

    private void showAdminPasswordPrompt(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Senha do Administrador");

        // Set up the input
        final EditText input = new EditText(this);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            String password = input.getText().toString();
            if (password.equals("senha")) {
                user.setAcesso(TipoAcesso.ADMIN);
                insertUser(user);
                navigateToAdminMainActivity();
            } else {
                Toast.makeText(ActivityRegister.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.cancel();
            navigateToMainActivity();
        });

        builder.show();
    }

    private void insertUser(User user) {
        new Thread(() -> {
            userDao.insert(user);
        }).start();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToAdminMainActivity() {
        Intent intent = new Intent(this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}
