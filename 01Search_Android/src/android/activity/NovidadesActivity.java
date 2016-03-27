package android.activity;

import java.util.ArrayList;
import conexao.Conexao;
import controle.Cache;
import Modelos.Codigobarras;
import Modelos.Estabelecimento;
import Modelos.Produto;
import android.activity.R;
import layouts.NovidadesGeral;
import layouts.NovidadesProduto;
import layouts.NovidadesProdutos;
import layouts.NovidadesSupermercado;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.RelativeLayout.LayoutParams;

public class NovidadesActivity extends Activity {

	private static final String TAG = "NovidadesActivity";
	private ArrayList<Produto> lista_produto = new ArrayList<Produto>();
	private ArrayList<Codigobarras> lista_codigobarras = new ArrayList<Codigobarras>();
	private ArrayList<Estabelecimento> lista_estabelecimento;
    private RelativeLayout.LayoutParams paramSupermercado;
	private LayoutParams paramProduto1;
	private LayoutParams paramProduto2;
	private LayoutParams paramProdutos;
	private TableLayout tabela;
	private TableRow linha;
	private NovidadesGeral geral;
	private NovidadesProdutos produtos;
	private NovidadesSupermercado supermercado;
	private int tamanho = 215;
	private int idBelow;	
	private int idProdutos;
	private int idSupermercados;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.novidades);
		 
		inicializaObjetos();
		geraNovidades();
	}
		
	private void preencheProdutos(Estabelecimento estabelecimento){
			
		lista_produto.clear();
		lista_codigobarras.clear();
		
		lista_produto = Conexao.getReference().buscarDestaque(estabelecimento);

		
		for(Produto produto: lista_produto){
			lista_codigobarras.add(Cache.get().getCodigoBarras(this, produto.getCodigobarras().getIdCodigoBarras()));
		}
	}
		
	private void inicializaObjetos(){
		lista_estabelecimento = Conexao.getReference().buscarEstabelecimento();
		lista_codigobarras = new ArrayList<Codigobarras>();
		tabela = (TableLayout) findViewById(R.id.tabelaNovidades);		
	}
		 
    private void geraNovidades(){
    	
        for (int a = 1; a <= lista_estabelecimento.size(); a++){
        	idSupermercados = a;      	
        	idBelow = idSupermercados;
        	
        	criaSupermercado(idSupermercados, lista_estabelecimento.get(a- 1));
        	criaGeral();
        	preencheProdutos(lista_estabelecimento.get(a - 1));
        	
        	for (int b = 0; b < lista_codigobarras.size(); b++){
        		if(b%2 == 0){
        			idProdutos = Integer.parseInt(String.format("%d%d%d", a, b, b));
	        		
            		criaProdutos(idProdutos, idBelow);
        			
            		NovidadesProduto novPro = criaProduto1(lista_codigobarras.get(b));
        			produtos.addView(novPro, paramProduto1);
        			
        			if (b == (lista_codigobarras.size() - 1))
            			geral.addView(produtos, paramProdutos);        			
        		}else{        			
        			NovidadesProduto novPro = criaProduto2(lista_codigobarras.get(b));
        			produtos.addView(novPro, paramProduto2);
        			
        			geral.addView(produtos, paramProdutos);        			
        			
        			idBelow = idProdutos;
        		}
        	}
        	
        	linha = new TableRow(this);
        	linha.addView(geral);        	
        	
        	tabela.addView(linha);        	
        }
    }
    
    private void criaGeral(){
        geral = new NovidadesGeral(this);
        geral.addView(supermercado, paramSupermercado);             
    }
    
    private NovidadesProduto criaProduto1(Codigobarras codigoBarras){
        paramProduto1 = new RelativeLayout.LayoutParams(tamanho, tamanho);
        paramProduto1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramProduto1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        
        Log.d(TAG, "criaProduto1 "+codigoBarras.getNome());
        return new NovidadesProduto(this, codigoBarras);
    }
    
    private NovidadesProduto criaProduto2(Codigobarras codigoBarras){
        paramProduto2 = new RelativeLayout.LayoutParams(tamanho, tamanho);
        paramProduto2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        paramProduto2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        
        Log.d(TAG, "criaProduto2 "+codigoBarras.getNome());
        return new NovidadesProduto(this, codigoBarras);
    }
    
    private void criaProdutos(int id, int idBelow){
        paramProdutos = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        paramProdutos.addRule(RelativeLayout.BELOW, idBelow);
        paramProdutos.setMargins(0,10,0,0);
        
        produtos = new NovidadesProdutos(this);
        produtos.setId(id);       
    }
    
    private void criaSupermercado(int id, Estabelecimento estabelecimento){
    	//supermercado
        paramSupermercado = new RelativeLayout.LayoutParams(445, 175);       
        paramSupermercado.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramSupermercado.setMargins(0,17,0,0);
        
        supermercado = new NovidadesSupermercado(this, estabelecimento);
        supermercado.setId(id);
    }
}
