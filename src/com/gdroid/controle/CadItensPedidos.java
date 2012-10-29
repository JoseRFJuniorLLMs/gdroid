package com.gdroid.controle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.gdroid.modelo.ItensPedidos;
import com.gdroid.visao.R;

public class CadItensPedidos extends Activity {
	static final int RESULT_SALVAR = 1;

	protected static Repositorio repositorio;

	private EditText EdtCodPro;
	private EditText EdtDesPro;
	private EditText SpnUnid;
	private EditText EdtCustTot;
	private Button BtnCadItens;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.i("ciclo", getClassName() + ".onCreate() chamado: " + icicle);

		setContentView(R.layout.itenspedidos);

		ScrollView view = (ScrollView) findViewById(R.id.scrollView);
		view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				v.requestFocusFromTouch();
				return false;
			}
		});

		repositorio = new Repositorio(getApplicationContext());


		
		EdtCodPro = (EditText) findViewById(R.id.EdtCodPro);
		EdtDesPro = (EditText) findViewById(R.id.EdtDesPro);
		SpnUnid = (EditText) findViewById(R.id.SpnUnid);
		EdtCustTot = (EditText) findViewById(R.id.EdtCustTot);
		BtnCadItens = (Button) findViewById(R.id.BtnCadItens);
		
		
		BtnCadItens.setOnClickListener(new OnClickListener() {
		
			public void onClick(View view) {

				String erro = "";
				boolean campo_obrigatorio_nao_preenchido = false;
				boolean campo_invalido = false;

				
				
				EdtCodPro.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtDesPro.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				SpnUnid.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
				EdtCustTot.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			
				
				
				
				String CodPro = EdtCodPro.getContext().toString().trim();
				String DesPro = EdtDesPro.getContext().toString().trim();
				String Unid = SpnUnid.getContext().toString().trim();
				String CustTot = EdtCustTot.getContext().toString().trim();
			
							

				String[] campos = new String[] { CodPro, DesPro, Unid, CustTot};

//				if (campo_vazio(campos)) { // tem campo vazio
//					erro = "Campo(s) obrigat�rio(s) em branco.";
//					campo_obrigatorio_nao_preenchido = true;
//					if (nome.equals("")) {
//						campo_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (cpf.equals("")) {
//						campo_cpf.setCompoundDrawablesWithIntrinsicBounds(0, 0,	R.drawable.indicator_input_error, 0);
//					}
//					if (email.equals("")) {
//						campo_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (login.equals("")) {
//						campo_login.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (senha.equals("")) {
//						campo_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//					if (confirma_senha.equals("")) {
//						campo_confirma_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_input_error, 0);
//					}
//				}
				

				if (erro.equals("")) { // se não tem campo vazio e todos já
										// passaram na validação
					salvarItensPedidos();
				} else {
					Toast.makeText(CadItensPedidos.this, erro, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void salvarItensPedidos() {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ItensPedidos itenspedidos = new ItensPedidos();

		TelephonyManager telephony;
		// == Adquirindo uma referência ao Sistema de Gerenciamento do Telefone
		telephony = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		
		itenspedidos.setCodPro(Long.parseLong(EdtCodPro.getText().toString()));
		itenspedidos.setDesPro(EdtDesPro.getText().toString());
		itenspedidos.setUnid(SpnUnid.getContext().toString());
		itenspedidos.setCustTot(Double.parseDouble(EdtCustTot.getText().toString()));
		
		
		
		if(salvar(itenspedidos)){		
			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	protected boolean salvar(ItensPedidos itenspedidos) {
		boolean salvar = true;
		long id = repositorio.inserir(itenspedidos, 1); 
		if(id != 0){
			
			///////////////?????////////////
			//itenspedidos.CodCli = id;
			//itenspedidos.estaLogado = true;
			//////////////?/////////////////
			Toast.makeText(CadItensPedidos.this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
		}else{
			salvar = false;
			
			AlertDialog.Builder alerta = new AlertDialog.Builder(CadItensPedidos.this);
			alerta.setIcon(R.drawable.alerta);
			alerta.setTitle("Falha ao inserir este Cliente");
			alerta.setMessage("Este erro pode ter sido causado pela perda do sinal da rede sem fio");

			alerta.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			alerta.show();
		}
		return salvar;
	}

	public boolean campo_vazio(String[] campos) {
		int tam = campos.length;
		for (int i = 0; i < tam; i++) {
			if (campos[i].equals("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("ciclo", getClassName() + ".onStart() chamado.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ciclo", getClassName() + ".onResume() chamado.");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i("ciclo", getClassName() + ".onRestart() chamado.");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("ciclo", getClassName() + ".onPause() chamado.");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("ciclo", getClassName() + ".onStop() chamado.");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("ciclo", getClassName() + ".onDestroy() chamado.");

		repositorio.fechar();
	}
	
	private String getClassName() {
		String s = getClass().getName();
		return s.substring(s.lastIndexOf("."));
	}
}
