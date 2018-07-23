package com.example.sandeco.meuprimeirodb.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import adapter.ProdutoAdapter;
import model.Produto;
import persist.DAO.FabricaDAO;
import persist.DAO.ProdutoDAO;
import persist.DatabaseHelper;


import com.example.sandeco.meuprimeirodb.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityLista extends AppCompatActivity {

    List<Produto> produtos;
    ProdutoAdapter adapter;
    ListView listView;

    private DatabaseHelper db;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        setContentView(listView);

        produtoDAO = FabricaDAO.criarProdutoDao();
        produtos = new ArrayList<>();
        produtos = produtoDAO.listarTodos();

        adapter = new ProdutoAdapter(this, produtos);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produto produto = (Produto)adapterView.getItemAtPosition(position);
                Intent i = new Intent(ActivityLista.this,ActivityCadastrar.class);
                i.putExtra("op","update");
                i.putExtra("Produto", produto);
                startActivity(i);
                ActivityLista.this.finish();
            }
        });
    }



}

