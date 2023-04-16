package unal.todosalau.unicorniomagico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText mEmailEditText;
    private TextInputEditText mPasswordEditText;
    private TextView crearUsuario;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * mAuth: es una instancia de FirebaseAuth que se utiliza para interactuar con la
         * autenticación de Firebase. Se utiliza para autenticar al usuario y gestionar su sesión.
         */
        mAuth = FirebaseAuth.getInstance();

        mEmailEditText = findViewById(R.id.inputCorreo);
        mPasswordEditText = findViewById(R.id.inputPassword);

        loginButton = findViewById(R.id.botonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                /**
                 * signInWithEmailAndPassword: es un método de FirebaseAuth que se utiliza para autenticar al usuario
                 * con su correo electrónico y contraseña ingresados. Este método devuelve un objeto Task que se utiliza
                 * para escuchar si la autenticación fue exitosa o no.
                 */
                mAuth.signInWithEmailAndPassword(email, password)
                        /**
                         * addOnCompleteListener: es un método de Task que se utiliza para escuchar si la tarea se completó
                         * correctamente o no. Si la tarea se completó correctamente, se puede obtener el resultado utilizando
                         * el método getResult().
                         */
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    /**
                                     * Intent: es una clase de Android que se utiliza para lanzar otras actividades o componentes
                                     * en la aplicación. En este caso, se utiliza para lanzar la actividad de pantalla de inicio
                                     * de sesión exitosa o la actividad de pantalla de creación de usuario, dependiendo de la acción del usuario.
                                     */
                                    // Si la autenticación es exitosa, redirige al usuario a la siguiente pantalla
                                    Intent loginSuccesfull = new Intent(MainActivity.this, LoginSuccesfull.class);
                                    /**
                                     * startActivity: es un método de Android que se utiliza para lanzar una actividad en la aplicación.
                                     * En este caso, se utiliza para lanzar la actividad de pantalla de inicio de sesión exitosa o la
                                     * actividad de pantalla de creación de usuario, dependiendo de la acción del usuario.
                                    * */
                                    startActivity(loginSuccesfull);
                                } else {
                                    /**
                                     * Toast: es una clase de Android que se utiliza para mostrar mensajes cortos al usuario en la
                                     * aplicación. En este caso, se utiliza para mostrar mensajes de error al usuario si la autenticación falla.
                                     */
                                    // Si la autenticación falla, muestra un mensaje de error al usuario
                                    Toast.makeText(MainActivity.this, "La autenticación ha fallado o no tienes una cuenta.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        //Si el usuario no tiene una cuenta lo envía a la actividad de crear cuenta
        crearUsuario = (TextView) findViewById(R.id.textView3);
        crearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearUsuario = new Intent(MainActivity.this, unal.todosalau.unicorniomagico.crearUsuario.class);
                startActivity(crearUsuario);
            }
        });
    }
}