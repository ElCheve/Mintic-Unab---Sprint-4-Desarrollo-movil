package com.example.helados.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helados.Catalogo;
import com.example.helados.DBFirebase.DBFirebase;
import com.example.helados.Entidades.Producto;
import com.example.helados.Form;
import com.example.helados.Informacion;
import com.example.helados.R;

import java.util.ArrayList;

public class AdaptadorProducto extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> arrayProductos;

    public AdaptadorProducto(Context context, ArrayList<Producto> arrayProductos) {
        this.context = context;
        this.arrayProductos = arrayProductos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Producto> getArrayProductos() {
        return arrayProductos;
    }

    public void setArrayProductos(ArrayList<Producto> arrayProductos) {
        this.arrayProductos = arrayProductos;
    }

    @Override
    public int getCount() {
        return arrayProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater =LayoutInflater.from(this.context);
        view =layoutInflater.inflate(R.layout.producto_template, null);

        //Producto de la clase producto_template
        Producto producto = arrayProductos.get(i);

        //Imágenes del producto
        ImageView productImageView = (ImageView) view.findViewById(R.id.ProductImageView);

        //Información del producto
        TextView nameProductTextView = (TextView) view.findViewById(R.id.NameProductTextView);
        TextView descriptionProductTextView = (TextView) view.findViewById(R.id.DescriptionProductTextView);
        TextView priceProductTextView = (TextView) view.findViewById(R.id.PriceProductTextView);

        //Botones
        Button BtnDeleteProduct = (Button) view.findViewById(R.id.BtnDeleteProduct);
        Button BtnEditProduct = (Button) view.findViewById(R.id.BtnEditProduct);

        //Se obtiene el valor de los TexView de la parte de diseño y se le introducen los
        //valores de la clase de producto
        productImageView.setImageResource(R.drawable.paletaamarilla); // Se introduce una imágen en la ImageView del diseño producto_template
        nameProductTextView.setText(producto.getName());
        descriptionProductTextView.setText(producto.getDescription());
        priceProductTextView.setText(String.valueOf(producto.getPrice()));

        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Informacion.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("name", producto.getDescription());
                intent.putExtra("name", String.valueOf(producto.getPrice()).toString());
                context.startActivity(intent);
            }
        });

        //Evento para eliminar producto
        BtnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFirebase dbFirebase = new DBFirebase();
                dbFirebase.deleteData(producto.getId());
                Intent intent = new Intent(context, Catalogo.class);
                context.startActivity(intent);
            }
        });
        BtnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Form.class);
                intent.putExtra("edit", true);
                intent.putExtra("id", producto.getId());
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", String.valueOf(producto.getPrice()));
                intent.putExtra("image", producto.getImage());
                intent.putExtra("longitud", producto.getLongitud());
                intent.putExtra("latitud", producto.getLatitud());
                context.startActivity(intent);
            }
        });

        //Retorna una vista
        return view;
    }
}
