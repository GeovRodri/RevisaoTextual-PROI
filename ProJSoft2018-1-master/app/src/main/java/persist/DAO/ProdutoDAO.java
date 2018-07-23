package persist.DAO;

import java.util.List;

import model.Produto;

public interface ProdutoDAO {

    long    salvar(Produto p);
    void   criar(Produto p);
    Produto buscar(String p);
    long   alterar(Produto p, Produto p2);
    long   excluir(Produto p);

    List<String> listarTodos();
}
