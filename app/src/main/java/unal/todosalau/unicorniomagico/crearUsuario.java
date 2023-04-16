package unal.todosalau.unicorniomagico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class crearUsuario extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView crearusuario;
    private Button btnCrearUsuario;
    private TextInputEditText edtNombre;
    private TextInputEditText edtCorreo;
    private TextInputEditText edtContrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        mAuth = FirebaseAuth.getInstance();
        btnCrearUsuario = findViewById(R.id.botonLogin);
        crearusuario = findViewById(R.id.textLogin);

        //Cosas que hace al presionar el boton
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNombre = findViewById(R.id.txtInputCrearUsuario);
                edtCorreo = findViewById(R.id.txtInputCrearCorreo);
                edtContrasena = findViewById(R.id.txtInputCrearContrasena);
                CheckBox chkTerminos = findViewById(R.id.checkBox);
                CheckBox chkUsoDatos = findViewById(R.id.checkBox2);

                String nombre = edtNombre.getText().toString();
                String correo = edtCorreo.getText().toString();
                String contrasena = edtContrasena.getText().toString();
                boolean terminos = chkTerminos.isChecked();
                boolean usoDatos = chkUsoDatos.isChecked();

                // Verificamos que se hayan aceptado los términos y el uso de datos
                if (!terminos || !usoDatos) {
                    Toast.makeText(crearUsuario.this, "Debe aceptar los términos y el uso de datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener(crearUsuario.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Si la creación de usuario es exitosa, muestra un mensaje al usuario
                                    Toast.makeText(crearUsuario.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                                    finish(); // Cierra la actividad de creación de usuario y regresa a la pantalla de inicio de sesión
                                } else {
                                    // Si la creación de usuario falla, muestra un mensaje de error al usuario
                                    Toast.makeText(crearUsuario.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        //cosas que hace cuando presiono el texto volver a login
        crearusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearusuario = new Intent(crearUsuario.this, MainActivity.class);
                startActivity(crearusuario);
            }
        });
    }
}