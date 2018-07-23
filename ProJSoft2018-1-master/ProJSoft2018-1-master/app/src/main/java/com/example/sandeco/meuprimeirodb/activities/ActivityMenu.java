package com.example.sandeco.meuprimeirodb.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sandeco.meuprimeirodb.R;

import persist.DAO.FabricaDAO;
import persist.DAO.ProdutoDAO;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btncadastrar = (Button) findViewById(R.id.btncadastrar);
        btncadastrar.setOnClickListener(this);
        Button btnconsultar = (Button) findViewById(R.id.btnconsultar);
        btnconsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnconsultar:
                Intent it1 = new Intent(this,ActivityLista.class);
                it1.putExtra("op","update");
                startActivity(it1);
                this.finish();
                break;
            case R.id.btncadastrar:
                Intent it2 = new Intent(this,ActivityCadastrar.class);
                it2.putExtra("op","insert");
                startActivity(it2);
                this.finish();
            break;
        }
    }

    /*
    public void lista() {

        produtoDAO = FabricaDAO.criarProdutoDao();
        final CharSequence[] items = produtoDAO.listarTodos().toArray(new CharSequence[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Produtos Cadastrados");
        builder.setItems(items, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int item) {
                Intent it = new Intent(getApplicationContext(),ActivityCadastrar.class);
                it.putExtra("op","update");
                it.putExtra("descricao",items[item]);
                startActivity(it);
                //onPause();
            }
        });
        builder.create().show();
    }
*/

    public void onPause() {
        super.onPause();
        this.finish();
    }
}
