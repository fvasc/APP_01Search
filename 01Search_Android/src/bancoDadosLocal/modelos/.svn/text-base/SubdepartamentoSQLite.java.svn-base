package bancoDadosLocal.modelos;

import Modelos.Subdepartamento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class SubdepartamentoSQLite extends OperacaoGenerica<Subdepartamento> {
	
	private static SubdepartamentoSQLite subdepartamentoSQLite;

	private Context context;
	
	public static SubdepartamentoSQLite get(Context context) {
		if (subdepartamentoSQLite == null)
			subdepartamentoSQLite = new SubdepartamentoSQLite(context);
		
		return subdepartamentoSQLite;
	}
	
	public SubdepartamentoSQLite(Context context) {
		super(context);
		
		super.ID_NAME = "idSubdepartamento";
		super.TABLE_NAME = "subdepartamento";
		super.CREATE_TABLE = "CREATE TABLE `" + TABLE_NAME + "` (" +
							 "`" + ID_NAME + "` int(10) unsigned NOT NULL AUTO_INCREMENT," +
							 "`idDepartamento` int(10) unsigned NOT NULL," +
						     "`nome` varchar(45) DEFAULT NULL," +
					 	     "PRIMARY KEY (`" + ID_NAME + "`));";
		
		subdepartamentoSQLite = this;
		this.context = context;
	}

	protected ContentValues preencheValues(Subdepartamento subdepartamento){
		ContentValues values = new ContentValues();
		
		values.put(ID_NAME, subdepartamento.getIdSubdepartamento());
		values.put("nome", subdepartamento.getNome());
		values.put("idDepartamento", subdepartamento.getIdDepartamento().getIdDepartamento());
		
		
		return values;
	}
	
	protected Subdepartamento preencheCursor(Cursor cursor) throws Exception{
		Subdepartamento subdepartamento = new Subdepartamento();
		subdepartamento.setIdSubdepartamento(Integer.parseInt(cursor.getString(0)));
		subdepartamento.setNome(cursor.getString(1));
		subdepartamento.setIdDepartamento(DepartamentoSQLite.get(context).select(cursor.getString(2)));
		
		return subdepartamento;		
	}

}