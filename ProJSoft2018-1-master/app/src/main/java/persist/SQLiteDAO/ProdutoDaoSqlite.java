package persist.SQLiteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import persist.DAO.ProdutoDAO;

public class ProdutoDaoSqlite extends GenericDaoSqlite implements ProdutoDAO {

    public void teste(){



    }



    @Override
    public long salvar(Produto p) {
        SQLiteDatabase db = getWritebleDB();
        long id;
        List<String> myArray = new ArrayList<>();
        myArray = this.listarTodos();

        if(myArray.contains(p.getDescricao())){
            id =-1;
        }else{
            ContentValues values = new ContentValues();
            values.put("descricao",p.getDescricao());
            values.put("valor", p.getValor());
            values.put("estoque", p.getEstoque());
            id = db.insert("produto",null,values);
        }
        return id;
    }

    @Override
    public void criar(Produto p) {
    }

    @Override
    public Produto buscar(String d) {
        SQLiteDatabase db = getReadableDB();
        Cursor resultSet = db.rawQuery("Select * From produto where descricao = '" + d + "'",null);
        Produto p = new Produto();
        if(resultSet!= null){
            if(resultSet.moveToFirst()) {
                do {
                    p.setDescricao(resultSet.getString(1));
                    p.setValor(Float.valueOf(resultSet.getString(2)));
                    p.setEstoque(Float.valueOf(resultSet.getString(3)));
                }while(resultSet.moveToNext());
            }
        }
        return p;
    }

    @Override
    public long alterar(Produto p,Produto p2) {
            SQLiteDatabase db = getWritebleDB();
            ContentValues values = new ContentValues();
            values.put("descricao",p2.getDescricao());
            values.put("valor", p2.getValor());
            values.put("estoque", p2.getEstoque());
            String where = "descricao = ?";
            String argumentos[] = {p.getDescricao()};
            long id = db.update("produto",values,where,argumentos);
        return id;
    }

    @Override
    public long excluir(Produto p) {
        SQLiteDatabase db = getWritebleDB();
        String where = "descricao = '" + p.getDescricao() + "'";
        long id = db.delete("produto" ,where, null);
        return id;
    }

    @Override
    public List<String> listarTodos() {
        List<String> myArray = new ArrayList<>();
        SQLiteDatabase db = getReadableDB();
        Cursor resultSet = db.rawQuery("Select descricao From produto",null);
        if(resultSet!= null){
            if(resultSet.moveToFirst()){
                do {
                    String descricao = resultSet.getString(0);
                    myArray.add(descricao);
                }while(resultSet.moveToNext());
            }
        }
        return myArray;
    }
}
