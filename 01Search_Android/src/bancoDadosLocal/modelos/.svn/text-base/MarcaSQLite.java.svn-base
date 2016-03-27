package bancoDadosLocal.modelos;

import Modelos.Marca;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class MarcaSQLite extends OperacaoGenerica<Marca> {

	private static MarcaSQLite marcaSQLite;

	public static MarcaSQLite get(Context context) {
		if (marcaSQLite == null)
			marcaSQLite = new MarcaSQLite(context);

		return marcaSQLite;
	}

	public MarcaSQLite(Context context) {
		super(context);

		super.ID_NAME = "idMarca";
		super.TABLE_NAME = "marca";
		super.CREATE_TABLE = "CREATE TABLE `marca` ("
				+ "`idMarca` int(10) unsigned NOT NULL,"
				+ "`nome` varchar(45) DEFAULT NULL, "
				+ "PRIMARY KEY (`idMarca`));";

		marcaSQLite = this;
	}

	protected ContentValues preencheValues(Marca marca) {
		ContentValues values = new ContentValues();

		values.put(ID_NAME, marca.getIdMarca());
		values.put("nome", marca.getNome());

		return values;
	}

	protected Marca preencheCursor(Cursor cursor) throws Exception {
		Marca marca = new Marca();
		marca.setIdMarca(Integer.parseInt(cursor.getString(0)));
		marca.setNome(cursor.getString(1));

		return marca;
	}

}