package com.example.sandeco.meuprimeirodb.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sandeco.meuprimeirodb.R;

import model.Produto;
import persist.DAO.FabricaDAO;
import persist.DAO.ProdutoDAO;
import persist.DatabaseHelper;

public class ActivityCadastrar extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper db;
    private ProdutoDAO produtoDAO;

    EditText edtNome;
    EditText edtValor;
    EditText edtEstoque;
    String op;
    String descricaoProduto;
    Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Intent it = getIntent();
        op = it.getStringExtra("op");
        descricaoProduto = it.getStringExtra("descricao");

        produtoDAO = FabricaDAO.criarProdutoDao();
        edtNome = (EditText)findViewById(R.id.textDescricao);
        edtValor = (EditText)findViewById(R.id.textValor);
        edtEstoque = (EditText)findViewById(R.id.textEstoque);

        Button btn1 = (Button) findViewById(R.id.btnSalvar);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btnExcluir);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btnVoltar);
        btn3.setOnClickListener(this);

        switch (op) {
            case "update":
                produto = produtoDAO.buscar(descricaoProduto);
                edtNome.setText(produto.getDescricao());
                edtValor.setText(String.valueOf(produto.getValor()));
                edtEstoque.setText(String.valueOf(produto.getEstoque()));
        }
        switch (op) {
            case "insert":
                btn2.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSalvar:
                switch (op){
                    case "insert":
                        Produto p = new Produto();
                        if(edtNome.getText().length()== 0) {
                            Toast.makeText(this,"O campo descrição está vazio, favor preencher!",Toast.LENGTH_SHORT).show();
                        }else{
                            p.setDescricao(edtNome.getText().toString());
                            if(edtValor.getText().length()==0){
                                p.setValor(0.f);
                            }else{
                                p.setValor(Float.valueOf(edtValor.getText().toString()));
                            }
                            if(edtEstoque.getText().length()==0){
                                p.setEstoque(0.f);
                            }else{
                                p.setEstoque(Float.valueOf(edtEstoque.getText().toString()));
                            }
                            long id = produtoDAO.salvar(p);
                            if(id!=-1){
                                Toast.makeText(this,"Produto salvo com sucesso",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(this, "Erro ao gravar o produto", Toast.LENGTH_SHORT).show();
                            }
                            Intent it = new Intent(this,ActivityMenu.class);
                            startActivity(it);
                            this.finish();
                        }
                    break;

                    case "update":
                        Produto p2 = new Produto();
                        p2.setDescricao(edtNome.getText().toString());
                        p2.setValor(Float.valueOf(edtValor.getText().toString()));
                        p2.setEstoque(Float.valueOf(edtEstoque.getText().toString()));

                        long id2 = produtoDAO.alterar(produto,p2);
                        if(id2!=-1){
                            Toast.makeText(this,"Produto atualizado com sucesso" ,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "Erro ao atualizar o produto", Toast.LENGTH_SHORT).show();
                        }
                        Intent it2 = new Intent(this,ActivityMenu.class);
                        startActivity(it2);
                        this.finish();
                    break;
                }
            case R.id.btnVoltar:
                Intent it = new Intent(this,ActivityMenu.class);
                startActivity(it);
                this.finish();
            break;

            case R.id.btnExcluir:
                Produto p = new Produto();
                p.setDescricao(edtNome.getText().toString());

                long id3 = produtoDAO.excluir(p);
                if(id3!=-1){
                    Toast.makeText(this,"Produto excluido com sucesso" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Erro ao excluir o produto", Toast.LENGTH_SHORT).show();
                }

                Intent it3 = new Intent(this,ActivityMenu.class);
                startActivity(it3);
                this.finish();
                break;
        }
    }
}
