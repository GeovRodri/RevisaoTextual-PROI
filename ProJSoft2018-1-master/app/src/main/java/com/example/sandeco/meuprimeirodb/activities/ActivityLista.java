package com.example.sandeco.meuprimeirodb.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;
import persist.DAO.FabricaDAO;
import persist.DAO.ProdutoDAO;
import persist.DatabaseHelper;


import com.example.sandeco.meuprimeirodb.R;

import java.util.List;


public class ActivityLista extends AppCompatActivity {
    private DatabaseHelper db;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listas);
        produtoDAO = FabricaDAO.criarProdutoDao();
        final CharSequence[] items = produtoDAO.listarTodos().toArray(new CharSequence[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Produtos Cadastrados");
        builder.setItems(items, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),ActivityCadastrar.class);
                it.putExtra("op","update");
                startActivity(it);
                return;
            }

        });
        builder.create().show();
    }
}
