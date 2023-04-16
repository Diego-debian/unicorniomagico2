package unal.todosalau.unicorniomagico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import unal.todosalau.unicorniomagico.Modelos.Articulos;
import unal.todosalau.unicorniomagico.adaptadores.ArticuloAdapter;

public class LeerArticulo extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArticuloAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Articulos> mArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_articulo);

        // Obtener una referencia a la base de datos de Firebase
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("articulos");

        // Configurar el RecyclerView
        mRecyclerView = findViewById(R.id.rvArticulos);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear una lista vacía de artículos
        mArticulos = new ArrayList<>();

        // Crear un adaptador para el RecyclerView
        mAdapter = new ArticuloAdapter(this, mArticulos);

        // Asignar el adaptador al RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Leer los datos de Firebase
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mArticulos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Articulos articulo = dataSnapshot.getValue(Articulos.class);
                    mArticulos.add(articulo);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LeerArticulo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón de regresar
        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}