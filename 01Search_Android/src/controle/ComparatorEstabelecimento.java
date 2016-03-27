package controle;

import java.util.Comparator;

import android.util.Log;
import layouts.ItemListView;

public class ComparatorEstabelecimento implements Comparator {
	boolean crescente = true;
	String param;

	public ComparatorEstabelecimento(boolean crescente, String param) {
		this.crescente = crescente;
		this.param = param;
	}

	public int compare(Object o1, Object o2) {
		ItemListView p1 = (ItemListView) o1;
		ItemListView p2 = (ItemListView) o2;
		
		if(param == "Nome"){
			return comparar(p1.getTexto(), p2.getTexto());
		}else
			return comparar(p1.getTexto2(), p2.getTexto2());
	}
	
	private int comparar(String t1, String t2){
		if (crescente) {
			if(t1.compareTo(t2) > 0)
				return 1;
			else if (t2.compareTo(t1) > 0)
				return -1;
			return 0;
		} else {
			if(t1.compareTo(t2) > 0)
				return -1;
			else if (t2.compareTo(t1) > 0)
				return 1;
			return 0;
		}
	}
}
