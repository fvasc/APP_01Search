package bancoDadosLocal.modelos;

import Modelos.Estabelecimento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class EstabelecimentoSQLite extends OperacaoGenerica<Estabelecimento> {

	private static EstabelecimentoSQLite estabelecimentoSQLite;

	private Context context;

	public static EstabelecimentoSQLite get(Context context) {
		if (estabelecimentoSQLite == null)
			estabelecimentoSQLite = new EstabelecimentoSQLite(context);

		return estabelecimentoSQLite;
	}

	public EstabelecimentoSQLite(Context context) {
		super(context);

		super.ID_NAME = "idEstabelecimento";
		super.TABLE_NAME = "estabelecimento";
		super.CREATE_TABLE = "CREATE TABLE `estabelecimento` (\n"
				+ "  `idEstabelecimento` int(10) unsigned NOT NULL AUTO_INCREMENT,\n"
				+ "  `cnpj` varchar(45) NOT NULL,\n"
				+ "  `descricao` varchar(45) DEFAULT NULL,\n"
				+ "  `logo` varchar(255) DEFAULT NULL,\n"
				+ "  `nome` varchar(45) NOT NULL,\n"
				+ "  `idLogin` int(11) NOT NULL,\n"
				+ "  PRIMARY KEY (`idEstabelecimento`),\n"
				+ "  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),\n"
				+ "  UNIQUE KEY `idLogin_UNIQUE` (`idLogin`)\n" + ");";

		estabelecimentoSQLite = this;
		this.context = context;
	}

	protected ContentValues preencheValues(Estabelecimento estabelecimento) {
		ContentValues values = new ContentValues();

		values.put(ID_NAME, estabelecimento.getIdEstabelecimento());
		values.put("cnpj", estabelecimento.getCnpj());
		values.put("nome", estabelecimento.getNome());
		values.put("descricao", estabelecimento.getDescricao());
		values.put("logo", estabelecimento.getLogo());
		values.put("idLogin", estabelecimento.getIdLogin().getIdLogin());

		return values;
	}

	protected Estabelecimento preencheCursor(Cursor cursor) throws Exception {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setIdEstabelecimento(Integer.parseInt(cursor
				.getString(0)));
		estabelecimento.setCnpj(cursor.getString(1));
		estabelecimento.setNome(cursor.getString(2));
		estabelecimento.setDescricao(cursor.getString(3));
		estabelecimento.setLogo(cursor.getString(4));
		estabelecimento.setIdLogin(LoginSQLite.get(context).select(
				cursor.getString(4)));

		return estabelecimento;
	}
}