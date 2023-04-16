package unal.todosalau.unicorniomagico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSuccesfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_succesfull);
        Button btnCrear = findViewById(R.id.btnCrear);
        Button btnLeer = findViewById(R.id.btnLeer);

        //Llamar a la actividad de crear Articulo
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSuccesfull.this, CrearArticulo.class);
                startActivity(intent);
            }
        });

        //Llamar a la actividad de leer Articulos
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSuccesfull.this, LeerArticulo.class);
                startActivity(intent);
            }
        });


    }
}