package com.gdroid.controle;

import java.util.List;

import com.gdroid.modelo.Pedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.provider.BaseColumns;
import android.widget.Toast;

import android.provider.BaseColumns;
import com.gdroid.banco.Login;
import com.gdroid.modelo.Clientes;
import com.gdroid.modelo.Clientes.Cliente;
import com.gdroid.modelo.ItensPedidos;
import com.gdroid.modelo.ItensPedidos.ItensPedido;
import com.gdroid.modelo.Pedidos.Pedido;
import com.gdroid.modelo.Usuario;
import com.gdroid.modelo.Usuario.Usuarios;
import com.gdroid.webservice.REST;

public class Repositorio {

	private static final String NOME_BANCO = "gdroid";
	private static final String TABELA_USUARIO = "usuario";
	private static final String TABELA_CLIENTES = "clientes";
	private static final String TABELA_PEDIDOS = "pedidos";
	private Context context;

	protected SQLiteDatabase bd;

	public Repositorio(Context context) {
		this.context = context;
		bd = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	protected Repositorio(){
	}
	
	public boolean TemConexao() { 
        boolean lblnRet = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
                lblnRet = true; 
            } else {
                lblnRet = false; 
            }
        }catch (Exception e) {
            
        }
        return lblnRet;
    }
	
	/**************************************************************/
	/*****************			USUARIOS			***************/
	/**************************************************************/

	
	public long inserir(Usuario usuario, int flag_enviar_servidor) {
		
		boolean tem_internet = TemConexao();
        if(tem_internet){
			long id = 0;
			if(flag_enviar_servidor == 1){
				REST cliREST = new REST();
				try {
					id = cliREST.inserirUsuario(usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(id!=0 || flag_enviar_servidor==0){
				ContentValues values = new ContentValues();
				if(flag_enviar_servidor == 1){
					values.put(Usuarios._ID, id);
				}
				else{
					id = usuario.getId();
		            values.put(Usuarios._ID, id);
				}
				values.put(Usuarios._ID, id);
				values.put(Usuarios.NOMVEN, usuario.getNomVen());
				values.put(Usuarios.APELIDO, usuario.getApelido());
				
				bd.insert(TABELA_USUARIO, "", values);
				return id;
			}
        }
        return 0;
	}
	
	public void atualizar(Usuario usuario, int flag_envia_servidor) {
		Login.flag_enviar_usuarios_atualizados_servidor = true;
		
		ContentValues values = new ContentValues();

		values.put(BaseColumns._ID, usuario.getId());
		values.put(Usuarios.NOMVEN, usuario.getNomVen());
		values.put(Usuarios.APELIDO, usuario.getApelido());
		

		String _id = String.valueOf(usuario.getId());
		String where = BaseColumns._ID + "=?";
		String[] whereArgs = new String[] { _id };
		bd.update(TABELA_USUARIO, values, where, whereArgs);
		
		if(flag_envia_servidor == 1){
			boolean tem_internet = TemConexao();
			if(tem_internet){
				REST cliREST = new REST();
				try {
					cliREST.atualizarUsuario(usuario);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Login.usuarios_atualizados_locais.add(usuario);
				Login.flag_enviar_usuarios_atualizados_servidor = true;
			}
		}
	}
	
	public Usuario buscarUsuario(long id) {

		Cursor c = bd.query(true, TABELA_USUARIO, Usuario.colunas, BaseColumns._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			Usuario usuario = new Usuario();

			usuario.setId(c.getLong(0));
			usuario.setNomVen(c.getString(1));
			usuario.setApelido(c.getString(2));
			

			return usuario;
		}
		return null;
	}
	
	public Usuario buscarUsuario(String NomVen) {
		Cursor c = bd.rawQuery("SELECT * FROM Vendedores where NomVen=?", new String[] { NomVen });
		Usuario usuario = null;
		
		if (c.getCount() > 0) {
			c.moveToFirst();
			usuario = new Usuario();

			usuario.setId(c.getLong(0));
			
			return usuario;
		}
		return usuario;
	}
	
	public int autentica_usuario(String NomVen, String Apelido) {
		
		try {
			Cursor c = bd.rawQuery("SELECT * FROM Vendedores where NomVen=? and Apelido=?", new String[] { NomVen, Apelido });
			
			return c.getCount();
		} catch (Exception e) {
			return -1;
		}
	}
	
	public List<Usuario> listaUsuariosNaoSincronizados(){
		REST cliREST = new REST();
		List<Usuario> usuarios = null;
	
		try {
			Cursor c =  bd.rawQuery("SELECT * FROM NomVen ORDER BY CodVen ASC", new String[] {});
			c.moveToLast();
			//String stringData;
			
			if(c.getCount()==0){
				//PRIMEIRA DEMANDA!
				try {
					usuarios = (List<Usuario>) cliREST.listarUsuarios();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
			//usuarios = (List<Usuario>) cliREST.listarUsuariosNaoSincronizados(stringData);
			}
			
			for(int i = 0; i < usuarios.size(); i++){				
				if(buscarUsuario(usuarios.get(i).getId()) != null){
					atualizar(usuarios.get(i),0);
				}
				else{
					inserir(usuarios.get(i), 0);
				}				
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void enviaUsuariosAtualizadosNaoSincronizados() throws Exception{
		if(Login.flag_enviar_usuarios_atualizados_servidor == true){
			REST cliRest = new REST();
			for(int i=0; i< Login.usuarios_atualizados_locais.size(); i++){
				cliRest.atualizarUsuario(Login.usuarios_atualizados_locais.get(i));
			}
			Login.usuarios_atualizados_locais.clear();
			Login.flag_enviar_usuarios_atualizados_servidor = false;
		}
	}
	/**************************************************************/
	/**************************************************************/
	/**************************************************************/	
	
	/**************************************************************/
	/*****************			CLIENTES			***************/
	/**************************************************************/

	
	public long inserir(Clientes cliente, int flag_enviar_servidor) {
		
		boolean tem_internet = TemConexao();
        if(tem_internet){
			long id = 0;
			if(flag_enviar_servidor == 1){
				REST cliREST = new REST();
				try {
					id = cliREST.inserirCliente(cliente);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(id!=0 || flag_enviar_servidor==0){
				ContentValues values = new ContentValues();
				if(flag_enviar_servidor == 1){
					values.put(Cliente.CODCLI, id);
				}
				else{
					id = cliente.getId();
		            values.put(Cliente.CODCLI, id);
				}
				

				values.put(Cliente.CODCLI, id);
				values.put(Cliente.CNPJCLI, cliente.getCNPJCli());
				values.put(Cliente.INSCLI, cliente.getInsCli());
				values.put(Cliente.SUFRA, cliente.getSufra());
				values.put(Cliente.RAZCLI, cliente.getRazCli());
				values.put(Cliente.FANCLI, cliente.getFanCli());
				values.put(Cliente.ENDCLI, cliente.getEndCli());
				values.put(Cliente.NUMCLI, cliente.getNumCli());
				values.put(Cliente.BAICLI, cliente.getBaiCli());
				values.put(Cliente.CEPCLI, cliente.getCepCli());
				values.put(Cliente.DDDFONCLI, cliente.getDDDFonCli());
				values.put(Cliente.FONCLI, cliente.getFonCli());
				values.put(Cliente.CADCLI, cliente.getCadCli());
				values.put(Cliente.CODVEN, cliente.getCodVen());
				values.put(Cliente.EMAILCLI, cliente.getEmailCli());
				values.put(Cliente.OBSCLI, cliente.getObsCli());
				values.put(Cliente.ALERTA, cliente.getAlerta());
				values.put(Cliente.NOMCID, cliente.getNomCid());
				values.put(Cliente.NOMEST, cliente.getNomEst());
				values.put(Cliente.ENDCOB, cliente.getEndCob());
				values.put(Cliente.NUMCOB, cliente.getNumCob());
				values.put(Cliente.BAICOB, cliente.getBaiCob());
				values.put(Cliente.CEPCOB, cliente.getCepCob());
				values.put(Cliente.CPFCLI, cliente.getCPFCli());
				values.put(Cliente.RGCLI, cliente.getRGCli());
				values.put(Cliente.PROPCLI, cliente.getPropCli());
				values.put(Cliente.TIPOVENDA, cliente.getTipoVenda());
				
				bd.insert(TABELA_USUARIO, "", values);
				return id;
			}
        }
        return 0;
	}
	
	public void atualizar(Clientes cliente, int flag_envia_servidor) {
		Login.flag_enviar_clientes_atualizados_servidor = true;
		
		ContentValues values = new ContentValues();

		values.put(BaseColumns._ID, cliente.getId());
		values.put(Cliente.CNPJCLI, cliente.getCNPJCli());
		values.put(Cliente.INSCLI, cliente.getInsCli());
		values.put(Cliente.SUFRA, cliente.getSufra());
		values.put(Cliente.RAZCLI, cliente.getRazCli());
		values.put(Cliente.FANCLI, cliente.getFanCli());
		values.put(Cliente.ENDCLI, cliente.getEndCli());
		values.put(Cliente.NUMCLI, cliente.getNumCli());
		values.put(Cliente.BAICLI, cliente.getBaiCli());
		values.put(Cliente.CEPCLI, cliente.getCepCli());
		values.put(Cliente.DDDFONCLI, cliente.getDDDFonCli());
		values.put(Cliente.FONCLI, cliente.getFonCli());
		values.put(Cliente.CADCLI, cliente.getCadCli());
		values.put(Cliente.CODVEN, cliente.getCodVen());
		values.put(Cliente.EMAILCLI, cliente.getEmailCli());
		values.put(Cliente.OBSCLI, cliente.getObsCli());
		values.put(Cliente.ALERTA, cliente.getAlerta());
		values.put(Cliente.NOMCID, cliente.getNomCid());
		values.put(Cliente.NOMEST, cliente.getNomEst());
		values.put(Cliente.ENDCOB, cliente.getEndCob());
		values.put(Cliente.NUMCOB, cliente.getNumCob());
		values.put(Cliente.BAICOB, cliente.getBaiCob());
		values.put(Cliente.CEPCOB, cliente.getCepCob());
		values.put(Cliente.CPFCLI, cliente.getCPFCli());
		values.put(Cliente.RGCLI, cliente.getRGCli());
		values.put(Cliente.PROPCLI, cliente.getPropCli());
		values.put(Cliente.TIPOVENDA, cliente.getTipoVenda());
		

		String _id = String.valueOf(cliente.getId());
		String where = BaseColumns._ID + "=?";
		String[] whereArgs = new String[] { _id };
		bd.update(TABELA_CLIENTES, values, where, whereArgs);
		
		if(flag_envia_servidor == 1){
			boolean tem_internet = TemConexao();
			if(tem_internet){
				REST cliREST = new REST();
				try {
					cliREST.atualizarCliente(cliente);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Login.clientes_atualizados_locais.add(cliente);
				Login.flag_enviar_clientes_atualizados_servidor = true;
			}
		}
	}
	
	public Clientes buscarCliente(long id) {

		Cursor c = bd.query(true, TABELA_USUARIO, Clientes.colunas, BaseColumns._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			Clientes clientes = new Clientes();

			clientes.setId(c.getLong(0));
			clientes.setCNPJCli(c.getString(1));
			clientes.setInsCli(c.getString(2));
			clientes.setSufra(c.getString(3));
			clientes.setRazCli(c.getString(4));
			clientes.setFanCli(c.getString(5));
			clientes.setEndCli(c.getString(6));
			clientes.setNumCli(c.getString(7));
			clientes.setBaiCli(c.getString(8));
			clientes.setCepCli(c.getString(9));
			clientes.setDDDFonCli(c.getString(10));
			clientes.setFonCli(c.getString(11));
			clientes.setCadCli(c.getString(12));
			clientes.setCodVen(c.getString(13));			
			clientes.setEmailCli(c.getString(14));
			clientes.setObsCli(c.getString(15));
			clientes.setAlerta(c.getString(16));
			clientes.setNomCid(c.getString(17));
			clientes.setNomEst(c.getString(18));
			clientes.setEndCob(c.getString(19));
			clientes.setNumCob(c.getString(20));
			clientes.setBaiCob(c.getString(21));
			clientes.setCepCob(c.getString(22));
			clientes.setCPFCli(c.getString(23));
			clientes.setRGCli(c.getString(24));
			clientes.setPropCli(c.getString(25));
			clientes.setTipoVenda(c.getString(26));
			

			return clientes;
		}
		return null;
	}
	
	public Clientes buscarCliente(String CNPJCli) {
		Cursor c = bd.rawQuery("SELECT * FROM Clientes where CNPJCli=?", new String[] { CNPJCli });
		Clientes cliente = null;
		
		if (c.getCount() > 0) {
			c.moveToFirst();
			cliente = new Clientes();

			cliente.setId(c.getLong(0));
			
			return cliente;
		}
		return cliente;
	}
	

	
	public List<Clientes> listaClientesNaoSincronizados(){
		REST cliREST = new REST();
		List<Clientes> clientes = null;
	
		try {
			Cursor c =  bd.rawQuery("SELECT * FROM Clientes ORDER BY CodCli ASC", new String[] {});
			c.moveToLast();
			//String stringData;
			
			if(c.getCount()==0){
				//PRIMEIRA DEMANDA!
				try {
					clientes = (List<Clientes>) cliREST.listarClientes();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
			//clientes = (List<Cliente>) cliREST.listarClientesNaoSincronizados(stringData);
			}
			
			for(int i = 0; i < clientes.size(); i++){				
				if(buscarCliente(clientes.get(i).getId()) != null){
					atualizar(clientes.get(i),0);
				}
				else{
					inserir(clientes.get(i), 0);
				}				
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}
	
	public void enviaClientesAtualizadosNaoSincronizados() throws Exception{
		if(Login.flag_enviar_clientes_atualizados_servidor == true){
			REST cliRest = new REST();
			for(int i=0; i< Login.clientes_atualizados_locais.size(); i++){
				cliRest.atualizarCliente(Login.clientes_atualizados_locais.get(i));
			}
			Login.clientes_atualizados_locais.clear();
			Login.flag_enviar_clientes_atualizados_servidor = false;
		}
	}
	/**************************************************************/
	/**************************************************************/
	/**************************************************************/	
	
	/**************************************************************/
	/*****************			PEDIDOS   			***************/
	/**************************************************************/

	
	public long inserir(Pedidos pedidos, int flag_enviar_servidor) {
		
		boolean tem_internet = TemConexao();
        if(tem_internet){
			long id = 0;
			if(flag_enviar_servidor == 1){
				REST cliREST = new REST();
				try {
					id = cliREST.inserirPedidos(pedidos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(id!=0 || flag_enviar_servidor==0){
				ContentValues values = new ContentValues();
				if(flag_enviar_servidor == 1){
					values.put(Pedido.CODPED, id);
				}
				else{
					id = pedidos.getId();
		            values.put(Pedido.CODPED, id);
				}
	
				values.put(Pedido.CODPED, id);
				values.put(Pedido.DATAPED, pedidos.getDataPed());
				values.put(Pedido.CODCLI, pedidos.getCodCli());
				values.put(Pedido.CODVEN, pedidos.getCodVen());
				values.put(Pedido.TOTLAN, pedidos.getTotLan());
				values.put(Pedido.TOTPEC, pedidos.getTotPec());
				values.put(Pedido.TOTICMS, pedidos.getTotIcms());
				values.put(Pedido.TOTIPI, pedidos.getTotIpi());
				values.put(Pedido.TOTPRO, pedidos.getTotPro());
				values.put(Pedido.TOTDES, pedidos.getTotDes());
				values.put(Pedido.TOTBRUTO, pedidos.getTotBruto());
				values.put(Pedido.TOTPED, pedidos.getTotPed());

		
				bd.insert(TABELA_PEDIDOS, "", values);
				return id;
			}
        }
        return 0;
	}
	
	public void atualizar(Pedidos pedidos, int flag_envia_servidor) {
		Login.flag_enviar_pedidos_atualizados_servidor = true;
		
		ContentValues values = new ContentValues();

		values.put(Pedido.CODPED, pedidos.getId());
		values.put(Pedido.DATAPED, pedidos.getDataPed());
		values.put(Pedido.CODCLI, pedidos.getCodCli());
		values.put(Pedido.CODVEN, pedidos.getCodVen());
		values.put(Pedido.TOTLAN, pedidos.getTotLan());
		values.put(Pedido.TOTPEC, pedidos.getTotPec());
		values.put(Pedido.TOTICMS, pedidos.getTotIcms());
		values.put(Pedido.TOTIPI, pedidos.getTotIpi());
		values.put(Pedido.TOTPRO, pedidos.getTotPro());
		values.put(Pedido.TOTDES, pedidos.getTotDes());
		values.put(Pedido.TOTBRUTO, pedidos.getTotBruto());
		values.put(Pedido.TOTPED, pedidos.getTotPed());

		String _id = String.valueOf(pedidos.getId());
		String where = BaseColumns._ID + "=?";
		String[] whereArgs = new String[] { _id };
		bd.update(TABELA_PEDIDOS, values, where, whereArgs);
		
		if(flag_envia_servidor == 1){
			boolean tem_internet = TemConexao();
			if(tem_internet){
				REST cliREST = new REST();
				try {
					cliREST.atualizarPedidos(pedidos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Login.pedidos_atualizados_locais.add(pedidos);
				Login.flag_enviar_pedidos_atualizados_servidor = true;
			}
		}
	}
	
	public Pedidos buscarPedidos(long id) {

		Cursor c = bd.query(true, TABELA_PEDIDOS, Pedidos.colunas, BaseColumns._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			Pedidos pedidos = new Pedidos();

			pedidos.setId(c.getInt(0));
			pedidos.setDataPed(c.getString(1));
			pedidos.setCodCli(c.getInt(2));
			pedidos.setCodVen(c.getInt(3));
			pedidos.setTotLan(c.getInt(4));
			pedidos.setTotPec(c.getDouble(5));
			pedidos.setTotIcms(c.getDouble(6));
			pedidos.setTotIpi(c.getDouble(7));
			pedidos.setTotPro(c.getDouble(8));
			pedidos.setTotDes(c.getDouble(9));
			pedidos.setTotBruto(c.getDouble(10));
			pedidos.setTotPed(c.getDouble(11));
			
			

			return pedidos;
		}
		return null;
	}
	
	public Pedidos buscarPedidos(String CodPed) {
		Cursor c = bd.rawQuery("SELECT * FROM Pedidos where CodPed=?", new String[] { CodPed });
		Pedidos pedidos = null;
		
		if (c.getCount() > 0) {
			c.moveToFirst();
			pedidos = new Pedidos();

			pedidos.setId(c.getLong(0));
			
			return pedidos;
		}
		return pedidos;
	}
	
	
	
	public List<Pedidos> listaPedidosNaoSincronizados(){
		REST cliREST = new REST();
		List<Pedidos> Pedidos = null;
	
		try {
			Cursor c =  bd.rawQuery("SELECT * FROM Pedidos ORDER BY CodPed ASC", new String[] {});
			c.moveToLast();
			String stringData;
			
			if(c.getCount()==0){
				//PRIMEIRA DEMANDA!
				try {
					Pedidos = (List<Pedidos>) cliREST.listarPedidos();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//stringData = c.getString(c.getColumnIndex("lastmodified"));
				//pedidos = (List<Pedidos>) cliREST.listarUsuariosNaoSincronizados(stringData);
			}
			
			for(int i = 0; i < Pedidos.size(); i++){				
				if(buscarUsuario(Pedidos.get(i).getId()) != null){
					atualizar(Pedidos.get(i),0);
				}
				else{
					inserir(Pedidos.get(i), 0);
				}				
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Pedidos;
	}
	
	public void enviaPedidosAtualizadosNaoSincronizados() throws Exception{
		if(Login.flag_enviar_usuarios_atualizados_servidor == true){
			REST cliRest = new REST();
			for(int i=0; i< Login.usuarios_atualizados_locais.size(); i++){
				cliRest.atualizarUsuario(Login.usuarios_atualizados_locais.get(i));
			}
			Login.usuarios_atualizados_locais.clear();
			Login.flag_enviar_usuarios_atualizados_servidor = false;
		}
	}
	

	/**************************************************************/
	/*****************			ItensPedidos	    ***************/
	/**************************************************************/

	
	public long inserir(ItensPedidos itenspedidos, int flag_enviar_servidor) {
		
		boolean tem_internet = TemConexao();
        if(tem_internet){
			long id = 0;
			if(flag_enviar_servidor == 1){
				REST cliREST = new REST();
				try {
					id = cliREST.inserirItensPedidos(itenspedidos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(id!=0 || flag_enviar_servidor==0){
				ContentValues values = new ContentValues();
				if(flag_enviar_servidor == 1){
					values.put(ItensPedido.CODPED, id);
				}
				else{
					id = itenspedidos.getId();
		            values.put(ItensPedido._ID, id);
				}
				
			
				
				values.put(ItensPedido._ID, id);
				values.put(ItensPedido.CODPED, itenspedidos.getCodPed());
				values.put(ItensPedido.CODPRO, itenspedidos.getCodPro());
				values.put(ItensPedido.DESPRO, itenspedidos.getDesPro());
				values.put(ItensPedido.UNID, itenspedidos.getUnid());
				values.put(ItensPedido.CUSTTOT, itenspedidos.getCustTot());
				

		
				bd.insert(TABELA_PEDIDOS, "", values);
				return id;
			}
        }
        return 0;
	}
	
	public void atualizar(ItensPedidos itenspedidos, int flag_envia_servidor) {
		Login.flag_enviar_itenspedidos_atualizados_servidor = true;
		
		ContentValues values = new ContentValues();

		values.put(ItensPedido._ID, itenspedidos.getId());
		values.put(ItensPedido.CODPED, itenspedidos.getCodPed());
		values.put(ItensPedido.CODPRO, itenspedidos.getCodPro());
		values.put(ItensPedido.DESPRO, itenspedidos.getDesPro());
		values.put(ItensPedido.UNID, itenspedidos.getUnid());
		values.put(ItensPedido.CUSTTOT, itenspedidos.getCustTot());

		String _id = String.valueOf(itenspedidos.getId());
		String where = BaseColumns._ID + "=?";
		String[] whereArgs = new String[] { _id };
		bd.update(TABELA_PEDIDOS, values, where, whereArgs);
		
		if(flag_envia_servidor == 1){
			boolean tem_internet = TemConexao();
			if(tem_internet){
				REST cliREST = new REST();
				try {
					cliREST.atualizarItensPedidos(itenspedidos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//Login.itenspedidos_atualizados_locais.add(itenspedidos);
				//????????
				//Login.flag_enviar_itenspedidos_atualizados_servidor = true;
			}
		}
	}
	
	public ItensPedidos buscarItensPedidos(long id) {

		Cursor c = bd.query(true, TABELA_PEDIDOS, ItensPedidos.colunas, BaseColumns._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			ItensPedidos itenspedidos = new ItensPedidos();

			
			
			
			
			itenspedidos.setId(c.getInt(0));
			itenspedidos.setCodPed(c.getInt(1));
			itenspedidos.setCodPro(c.getInt(2));
			itenspedidos.setDesPro(c.getString(3));
			itenspedidos.setUnid(c.getString(4));
			itenspedidos.setCustTot(c.getDouble(5));
		
			
			

			return itenspedidos;
		}
		return null;
	}
	
	public ItensPedidos buscarItensPedidos(String CodPed) {
		Cursor c = bd.rawQuery("SELECT * FROM ItensPedidos where CodPed=?", new String[] { CodPed });
		ItensPedidos itenspedidos = null;
		
		if (c.getCount() > 0) {
			c.moveToFirst();
			itenspedidos = new ItensPedidos();

			itenspedidos.setId(c.getLong(0));
			
			return itenspedidos;
		}
		return itenspedidos;
	}
	
	
	
	public List<ItensPedidos> listaItensPedidosNaoSincronizados(){
		REST cliREST = new REST();
		List<ItensPedidos> ItensPedidos = null;
	
		try {
			Cursor c =  bd.rawQuery("SELECT * FROM ItensPedidos ORDER BY CodPed ASC", new String[] {});
			c.moveToLast();
			String stringData;
			
			if(c.getCount()==0){
				//PRIMEIRA DEMANDA!
				try {
					ItensPedidos = (List<ItensPedidos>) cliREST.listarItensPedidos();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//stringData = c.getString(c.getColumnIndex("lastmodified"));
				//itenspedidos = (List<ItensPedidos>) cliREST.listarUsuariosNaoSincronizados(stringData);
			}
			
			for(int i = 0; i < ItensPedidos.size(); i++){				
				if(buscarUsuario(ItensPedidos.get(i).getId()) != null){
					atualizar(ItensPedidos.get(i),0);
				}
				else{
					inserir(ItensPedidos.get(i), 0);
				}				
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ItensPedidos;
	}
	
	public void enviaItensPedidosAtualizadosNaoSincronizados() throws Exception{
		if(Login.flag_enviar_usuarios_atualizados_servidor == true){
			REST cliRest = new REST();
			for(int i=0; i< Login.usuarios_atualizados_locais.size(); i++){
				cliRest.atualizarUsuario(Login.usuarios_atualizados_locais.get(i));
			}
			Login.usuarios_atualizados_locais.clear();
			Login.flag_enviar_usuarios_atualizados_servidor = false;
		}
	}
	
	

	
	
	public void fechar() {
		if (bd != null) {
			bd.close();
		}
	}
}