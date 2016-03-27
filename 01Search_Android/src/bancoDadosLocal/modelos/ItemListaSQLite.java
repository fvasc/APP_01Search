package bancoDadosLocal.modelos;

import java.util.Date;

import controle.Cache;
import Modelos.Codigobarras;
import Modelos.ItemLista;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import bancoDadosLocal.OperacaoGenerica;

public class ItemListaSQLite extends OperacaoGenerica<ItemLista> {

	private static ItemListaSQLite ItemListaSQLite;

	private Context context;

	public static ItemListaSQLite get(Context context) {
		if (ItemListaSQLite == null)
			ItemListaSQLite = new ItemListaSQLite(context);

		return ItemListaSQLite;
	}

	public ItemListaSQLite(Context context) {
		super(context);

		super.ID_NAME = "idCodigobarras";
		super.TABLE_NAME = "codigobarras";
		super.CREATE_TABLE = "CREATE TABLE `codigobarras` (\n"
				+ "  `idCodigoBarras` varchar(11) NOT NULL,\n"
				+ "  `idMarca` int(10) NOT NULL,\n"
				+ "  `idSubdepartamento` int(10) NOT NULL,\n"
				+ "  `nome` varchar(100) DEFAULT NULL,\n"
				+ "  `imagem` varchar(255) DEFAULT NULL,\n"
				+ "  `data_cadastro` datetime DEFAULT NULL,\n"
				+ "  `data_liberacao` datetime DEFAULT NULL,\n"
				+ "  `descricao` varchar(255) DEFAULT NULL,\n"
				+ "  `versao` datetime DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`idCodigoBarras`)\n" + ")";

		ItemListaSQLite = this;
		this.context = context;
	}

	protected ContentValues preencheValues(Codigobarras codigobarras) {
		ContentValues values = new ContentValues();

		values.put(ID_NAME, codigobarras.getIdCodigoBarras());
		values.put("idMarca", codigobarras.getIdMarca().getIdMarca());
		values.put("idSubdepartamento", codigobarras.getIdSubdepartamento().getIdSubdepartamento());
		values.put("nome", codigobarras.getNome());
		values.put("imagem", codigobarras.getImagem());
		values.put("data_cadastro", codigobarras.getDataCadastro().toString());
		values.put("descricao", codigobarras.getDescricao());

		return values;
	}

	protected ItemLista preencheCursor(Cursor c) throws Exception {

		ItemLista itemLista = new ItemLista();

	/*	codigobarras.setIdCodigoBarras(c.getString(0));
		codigobarras.setIdMarca(Cache.get().getMarca(context, c.getInt(1)));
		codigobarras.setIdSubdepartamento(Cache.get().getSubdepartamento(context, c.getInt(2)));
		codigobarras.setNome(c.getString(3));
		codigobarras.setImagem(c.getString(4));
		codigobarras.setDataCadastro(new Date(c.getString(5)));
		codigobarras.setDataLiberacao(new Date(c.getString(6)));
		codigobarras.setDescricao(c.getString(7));*/

		return itemLista;
	}
}