package bancoDadosLocal;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OperacaoGenerica<T> extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "01Search";

	protected String TABLE_NAME;
	protected String ID_NAME;
	protected String CREATE_TABLE;

	public OperacaoGenerica(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	// Adding new Codigobarras
	public void insert(T objeto){
		SQLiteDatabase db = this.getWritableDatabase();

		try {
			db.insertOrThrow(TABLE_NAME, null, preencheValues(objeto));
			db.close(); // Closing database connection
		} catch (SQLiteConstraintException e) {
			update(objeto);
		}	
		
	}

	// Getting single Codigobarras
	public T select(String id) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, ID_NAME + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor != null){
			cursor.moveToFirst();
			try {
				return preencheCursor(cursor);
			} catch (Exception e) {				 
				return null;
			}
		}
		
		return null;
	}

	// Getting All Rows
	public List<T> selectAll() {		
		List<T> resultList = new ArrayList<T>();
		
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_NAME;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {				
				try {
					resultList.add(preencheCursor(cursor));
				} catch (Exception e) {
				}
			} while (cursor.moveToNext());
		}
		
		// return row cursor
		return resultList;
	}
	
	// Updating single Row
	public void update(T object) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values =  preencheValues(object);
		// updating row
		db.update(TABLE_NAME, values, ID_NAME + " = ?",	new String[] { values.getAsString(ID_NAME) });
	}

	// Deleting single Codigobarras
	public void delete(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, ID_NAME + " = ?", new String[] { id });
		db.close();
	}

	// Getting Codigobarras Count
	public int getCount() {
		String countQuery = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	protected ContentValues preencheValues(T objeto){
		return null;
	}
	
	protected T preencheCursor(Cursor cursor) throws Exception{
		return null;
		
	}

}