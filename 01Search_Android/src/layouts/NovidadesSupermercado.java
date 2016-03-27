package layouts;

import Modelos.Estabelecimento;
import android.activity.R;
import android.content.Context;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import controle.Cache;

public class NovidadesSupermercado extends RelativeLayout{
	
	private Estabelecimento corrente;
	private TextView nome;
	private ImageView logo;
	private CheckBox favorito;
	private LayoutParams paramNome;
	private LayoutParams paramLogo;
	private LayoutParams paramFavorito;
	private int idLogo;
	
	public NovidadesSupermercado(Context context, Estabelecimento estabelecimento) {
		super(context);
		
		this.corrente = estabelecimento;
		
		inicializaObjetos();
		adicionaObjetos();			

		this.setBackgroundColor(Color.BLACK);
		this.getBackground().setAlpha(65);
	}
	
	private void adicionaObjetos() {
		this.addView(logo, paramLogo);
		this.addView(nome, paramNome);
		this.addView(favorito, paramFavorito);
	}

	private void inicializaObjetos(){
		setaFavorito();
		setaLogo();
		setaNome();		
	}

	private void setaNome(){
		paramNome =  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramNome.addRule(RelativeLayout.RIGHT_OF, idLogo);
        paramNome.addRule(RelativeLayout.CENTER_VERTICAL);
        paramNome.setMargins(25,0,0,0);
		
		nome = new TextView(this.getContext());		
		nome.setText(corrente.getNome());
		nome.setTextSize(25f);
		nome.setTextColor(Color.WHITE);
	}

	private void setaLogo(){
		paramLogo =  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramLogo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		paramLogo.addRule(RelativeLayout.CENTER_VERTICAL);
		
		idLogo = Integer.parseInt(String.format("%d999", this.getId()));
		
        logo = new ImageView(this.getContext());		
        logo.setId(idLogo);
        logo.setBackgroundDrawable(Cache.get().getImagem(corrente.getLogo()));
	}

	private void setaFavorito(){
		paramFavorito =  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramFavorito.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		paramFavorito.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		favorito = new CheckBox(this.getContext());
		favorito.setButtonDrawable(R.drawable.checkbox_selector);
	}
}
