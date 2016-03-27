package layouts;

import android.graphics.drawable.Drawable;

public class ItemListView {

    private String texto;
    private String texto2;
    private int iconeRid;
    private Drawable imagem;
    private Boolean	isDrawable = false;

    public ItemListView(String texto, int iconeRid) {
        this.texto = texto;
        this.texto2 = null;
        this.iconeRid = iconeRid;
    }
    
    public ItemListView(String texto, Drawable imagem) {
        this.texto = texto;
        this.texto2 = "---";
        this.imagem = imagem;
        isDrawable = true;
    }

    public ItemListView(String texto, String texto2, int iconeRid) {
        this.texto = texto;
        this.texto2 = "---";
        this.iconeRid = iconeRid;
    }

    public Drawable getImagem() {
		return imagem;
	}

	public void setImagem(Drawable imagem) {
		this.imagem = imagem;
	}

	public Boolean getIsDrawable() {
		return isDrawable;
	}

	public void setIsDrawable(Boolean isDrawable) {
		this.isDrawable = isDrawable;
	}

	public ItemListView(String texto, String texto2, Drawable imagem) {
        this.texto = texto;
        this.texto2 = texto2;
        this.imagem = imagem;
        isDrawable = true;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }
}
