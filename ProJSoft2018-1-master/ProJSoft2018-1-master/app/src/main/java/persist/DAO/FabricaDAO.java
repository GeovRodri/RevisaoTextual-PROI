package persist.DAO;

import persist.SQLiteDAO.ProdutoDaoSqlite;

public class FabricaDAO {

    public static ProdutoDAO criarProdutoDao(){
        return new ProdutoDaoSqlite();
    }

}
