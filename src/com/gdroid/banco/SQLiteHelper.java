package com.gdroid.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SQLiteHelper extends SQLiteOpenHelper {

	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;
	
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	@Override
	// Criar novo banco...
	public void onCreate(SQLiteDatabase db) {
		int qtdeScripts = scriptSQLCreate.length;

		// Executa cada sql passado como parâmetro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
	}

	@Override
	// Mudou a vers�o...
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.v("Teste", "Atualizando da vers�o " + versaoAntiga + " para " + novaVersao + ". Todos os registros ser�o deletados.");
		Log.v("Teste", scriptSQLDelete + "");
		// Deleta as tabelas...
		
		int qtdeScripts = scriptSQLDelete.length;

		// Executa cada sql passado como par�metro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLDelete[i];
			Log.v("Teste", "upgrade "+sql);
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
		
		// Cria novamente...
		onCreate(db);
	}
}