package unal.todosalau.unicorniomagico.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unal.todosalau.unicorniomagico.Modelos.Articulos;
import unal.todosalau.unicorniomagico.R;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {
    private Context mContext;
    private List<Articulos> mArticulos;

    public ArticuloAdapter(Context context, List<Articulos> articulos) {
        mContext = context;
        mArticulos = articulos;
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_articulo, parent, false);
        return new ArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
        Articulos articuloActual = mArticulos.get(position);

        holder.tvNombre.setText(articuloActual.getNombre());
        holder.tvMarca.setText(articuloActual.getMarca());
        holder.tvModelo.setText(articuloActual.getModelo());
        holder.tvPrecio.setText(articuloActual.getPrecio());
        holder.tvStock.setText(articuloActual.getStock());
    }

    @Override
    public int getItemCount() {
        return mArticulos.size();
    }

    public class ArticuloViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre, tvMarca, tvModelo, tvPrecio, tvStock;

        public ArticuloViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreArticulo);
            tvMarca = itemView.findViewById(R.id.tvMarcaArticulo);
            tvModelo = itemView.findViewById(R.id.tvModeloArticulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecioArticulo);
            tvStock = itemView.findViewById(R.id.tvStockArticulo);
        }
    }
}
