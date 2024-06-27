package com.example.trab1progmov.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.trab1progmov.R;
import com.example.trab1progmov.ORM.Database.AppDatabase;
import com.example.trab1progmov.ORM.Entity.User;
import com.example.trab1progmov.ORM.Entity.TipoAcesso;
import com.example.trab1progmov.ORM.Dao.UserDao;
import com.example.trab1progmov.crypto.CryptoUtil;
import com.example.trab1progmov.UserSession;
import com.example.trab1progmov.MainActivity;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDao = AppDatabase.getInstance(getApplicationContext()).userDao();

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loginUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        TextView registerTextView = findViewById(R.id.textViewRegister);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegister();
            }
        });
    }

    private void loginUser() throws Exception {
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        // Retrieve user in the background
        new Thread(() -> {
            User user = userDao.getUserByEmail(email);
            if (user != null) {
                try {
                    String decryptedPassword = CryptoUtil.decrypt(user.getSenha());
                    if (password.equals(decryptedPassword)) {
                        UserSession userSession = UserSession.getInstance();
                        userSession.setUserEmail(email);
                        userSession.setTipoAcesso(user.getAcesso());

                        if (user.getAcesso() == TipoAcesso.ADMIN || email.startsWith("$")) {
                            runOnUiThread(() -> showAdminPasswordPrompt());
                        } else {
                            userSession.setLoggedIn(true);
                            // Navegar para ActivityMain
                            navigateToMainActivity();
                        }
                    } else {
                        runOnUiThread(() -> Toast.makeText(ActivityLogin.this, "Senha incorreta", Toast.LENGTH_SHORT).show());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                runOnUiThread(() -> Toast.makeText(ActivityLogin.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void showAdminPasswordPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Senha do Administrador");

        // Set up the input
        final EditText input = new EditText(this);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            String password = input.getText().toString();
            UserSession userSession = UserSession.getInstance();
            if (userSession.setLoggedIn(true, password)) {
                // Navegar para AdminMainActivity
                navigateToAdminMainActivity();
            } else {
                userSession.setLoggedIn(true);
                // Navegar para MainActivity
                navigateToMainActivity();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
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
    private void navigateToRegister() {
        Intent intent = new Intent(this, ActivityRegister.class);
        startActivity(intent);
    }
}
