package layouts;

import Modelos.Codigobarras;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import conexao.Conexao;
import controle.Cache;

public class NovidadesProduto extends RelativeLayout{

	private Codigobarras corrente;
	private RelativeLayout sombra;
	private TextView nomeProduto;
	private LayoutParams paramNomeProduto;
	private LayoutParams paramSombra;
	
	public NovidadesProduto(Context context, Codigobarras corrente) {
		super(context);
		
		this.corrente = corrente;
		
		this.setBackgroundDrawable(Cache.get().getImagem(corrente.getImagem()));
		eventoClique();
		
		adicionaObjetos();
	}
	
	private void adicionaObjetos() {
		geraSombra();
		geraNomeProduto();
		
		sombra.addView(nomeProduto, paramNomeProduto);
		this.addView(sombra, paramSombra);
	}

	private void geraSombra(){
		paramSombra = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 65);
		paramSombra.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		sombra = new RelativeLayout(getContext());
		sombra.setBackgroundColor(Color.BLACK);
		sombra.getBackground().setAlpha(65);
	}
	
	private void geraNomeProduto(){
		paramNomeProduto = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramNomeProduto.addRule(RelativeLayout.CENTER_VERTICAL);
		paramNomeProduto.addRule(RelativeLayout.ALIGN_LEFT);
		
		nomeProduto = new TextView(getContext());
		nomeProduto.setTextColor(Color.WHITE);
		nomeProduto.setText(corrente.getNome());
	}

	private void eventoClique(){
		this.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				eventoProduto();				
			}
		});
	}
	
	private void eventoProduto(){
		
		Intent intent = new Intent();

		intent.setClassName("android.activity", "android.activity.CodigobarrasActivity");

		String codigobarras = Conexao.xStream.toXML(corrente);

		intent.putExtra("Codigobarras", String.valueOf(codigobarras));

		getContext().startActivity(intent);
	}
}
