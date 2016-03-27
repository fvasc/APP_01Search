package bancoDadosLocal.modelos;

import Modelos.Login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class LoginSQLite extends OperacaoGenerica<Login> {

	private static LoginSQLite loginSQLite;

	public static LoginSQLite get(Context context) {
		if (loginSQLite == null)
			loginSQLite = new LoginSQLite(context);

		return loginSQLite;
	}

	public LoginSQLite(Context context) {
		super(context);

		super.ID_NAME = "idLogin";
		super.TABLE_NAME = "login";
		super.CREATE_TABLE = "CREATE TABLE `login` (\n"
				+ "  `idLogin` int(11) NOT NULL,\n"
				+ "  `login` varchar(45) NOT NULL,\n"
				+ "  `senha` varchar(45) NOT NULL,\n"
				+ "  `perfil` varchar(45) NOT NULL,\n"
				+ "  PRIMARY KEY (`idLogin`),\n"
				+ "  UNIQUE KEY `Login_UNIQUE` (`Login`)\n" + ");";

		loginSQLite = this;
	}

	protected ContentValues preencheValues(Login login) {
		ContentValues values = new ContentValues();

		values.put(ID_NAME, login.getIdLogin());
		values.put("login", login.getLogin());
		values.put("senha", login.getSenha());
		values.put("perfil", login.getPerfil());

		return values;
	}

	protected Login preencheCursor(Cursor cursor) throws Exception {

		Login login = new Login();
		login.setIdLogin(Integer.parseInt(cursor.getString(0)));
		login.setLogin(cursor.getString(1));
		login.setSenha(cursor.getString(2));
		login.setPerfil(cursor.getString(3));

		return login;
	}

}