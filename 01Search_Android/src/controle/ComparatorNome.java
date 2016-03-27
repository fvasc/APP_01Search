package controle;

import java.util.Comparator;

public class ComparatorNome<T> implements Comparator<T> {
	boolean crescente = true;

	public ComparatorNome(boolean crescente) {
		this.crescente = crescente;
	}

	public int compare(T p1, T p2) {

		return comparar(p1.toString(), p2.toString());
	}

	private int comparar(String t1, String t2) {
		if (crescente) {
			if (t1.compareTo(t2) > 0)
				return 1;
			else if (t2.compareTo(t1) > 0)
				return -1;
			return 0;
		} else {
			if (t1.compareTo(t2) > 0)
				return -1;
			else if (t2.compareTo(t1) > 0)
				return 1;
			return 0;
		}
	}

}
