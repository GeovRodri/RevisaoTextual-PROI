package adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sandeco.meuprimeirodb.R;

import java.util.List;

import model.Produto;

public class ProdutoAdapter extends BaseAdapter {
    Context ctx;
    List<Produto> produtos;

    public ProdutoAdapter(Context ctx, List<Produto> produtos){
        this.ctx = ctx;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        Produto produto = produtos.get(position);
        View linha = LayoutInflater.from(ctx).inflate(R.layout.activity_lista,parent, false);
        TextView txtDescricao = (TextView)linha.findViewById(R.id.txtDescricao);
        Resources res = ctx.getResources();
        txtDescricao.setText(produto.getDescricao());
        return linha;
    }
}
