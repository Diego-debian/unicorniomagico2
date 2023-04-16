package unal.todosalau.unicorniomagico;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import unal.todosalau.unicorniomagico.Modelos.Articulos;

public class CrearArticulo extends AppCompatActivity {

    private EditText editTextNombre, editTextMarca, editTextModelo, editTextPrecio, editTextStock, editTextLinkImagen;
    private Button btnGuardar;

    // Referencia a la base de datos
    private DatabaseReference mDatabase;
    // Obtener una referencia a la base de datos
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nivelavanzadoappmoviles-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_articulo);

        mDatabase = databaseRef.child("articulos");

        // Obtener los elementos de la interfaz
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextMarca = findViewById(R.id.editTextMarca);
        editTextModelo = findViewById(R.id.editTextModelo);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        editTextStock = findViewById(R.id.editTextStock);
        editTextLinkImagen = findViewById(R.id.editTextLinkImagen);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configurar el evento clic del botón "Guardar"
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarArticulo();
            }
        });
    }

    private void guardarArticulo() {
        String nombre = editTextNombre.getText().toString().trim();
        String marca = editTextMarca.getText().toString().trim();
        String modelo = editTextModelo.getText().toString().trim();
        String precio = editTextPrecio.getText().toString().trim();
        String stock = editTextStock.getText().toString().trim();
        String linkImagen = editTextLinkImagen.getText().toString().trim();

        if (nombre.isEmpty()) {
            editTextNombre.setError("El nombre es requerido");
            editTextNombre.requestFocus();
            return;
        }

        if (marca.isEmpty()) {
            editTextMarca.setError("La marca es requerida");
            editTextMarca.requestFocus();
            return;
        }

        if (modelo.isEmpty()) {
            editTextModelo.setError("El modelo es requerido");
            editTextModelo.requestFocus();
            return;
        }

        if (precio.isEmpty()) {
            editTextPrecio.setError("El precio es requerido");
            editTextPrecio.requestFocus();
            return;
        }

        if (stock.isEmpty()) {
            editTextStock.setError("El stock es requerido");
            editTextStock.requestFocus();
            return;
        }

        // Crear un nuevo objeto Articulo
        String id = mDatabase.push().getKey();
        Articulos articulo = new Articulos(id, nombre, marca, modelo, precio, stock, linkImagen);

        // Guardar el objeto Articulo en la base de datos
        mDatabase.child(id).setValue(articulo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Artículo agregado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent crearArticulo = new Intent(CrearArticulo.this, LoginSuccesfull.class);
                    startActivity(crearArticulo);
                } else {
                    Toast.makeText(getApplicationContext(), "Error al agregar el artículo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
