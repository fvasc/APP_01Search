package bancoDadosLocal.modelos;

import Modelos.Departamento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class DepartamentoSQLite extends OperacaoGenerica<Departamento> {
	
	private static DepartamentoSQLite departamentoSQLite;

	public static DepartamentoSQLite get(Context context) {
		if (departamentoSQLite == null)
			departamentoSQLite = new DepartamentoSQLite(context);
		
		return departamentoSQLite;
	}
	
	public DepartamentoSQLite(Context context) {
		super(context);
		
		super.ID_NAME = "idDepartamento";
		super.TABLE_NAME = "departamento";
		super.CREATE_TABLE = "CREATE TABLE `departamento` (\n" +
							 "  `idDepartamento` int(10) unsigned NOT NULL AUTO_INCREMENT,\n" + 
							 "  `nome` varchar(45) DEFAULT NULL,\n" + 
							 "  PRIMARY KEY (`idDepartamento`)\n" + 
							 ");";
		
		departamentoSQLite = this;
	}

	protected ContentValues preencheValues(Departamento departamento){
		ContentValues values = new ContentValues();
		
		values.put(ID_NAME, departamento.getIdDepartamento());
		values.put("nome", departamento.getNome());
		
		return values;
	}
	
	protected Departamento preencheCursor(Cursor cursor) throws Exception{
		Departamento departamento = new Departamento();
		departamento.setIdDepartamento(Integer.parseInt(cursor.getString(0)));
		departamento.setNome(cursor.getString(1));
		
		return departamento;		
	}

}