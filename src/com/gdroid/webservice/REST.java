package com.gdroid.webservice;

import java.util.ArrayList;
import java.util.List;

import com.gdroid.modelo.Clientes;
import com.gdroid.modelo.ItensPedidos;
import com.gdroid.modelo.Pedidos;
import com.gdroid.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


public class REST {

        private static final String URL_WS = "http://146.164.250.138:8080/WebServiceREST/gdroid/";
        
        
        /*************************************************************/
        /*****************               USUARIOS               **********************/
        /*************************************************************/
        
        
        public Usuario getUsuario(long id) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "usuario/id/" + id);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Usuario usuario = gson.fromJson(resposta[1], Usuario.class);
                        return usuario;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public Usuario getUsuario(String login) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "usuario/login/" + login);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Usuario usuario = gson.fromJson(resposta[1], Usuario.class);
                        return usuario;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public int autentica_usuario(String login, String senha) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "autentica_usuario/" + login + "/" + senha);
                                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        int quantidade = gson.fromJson(resposta[1], Integer.class);
                        return quantidade;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<Usuario> getListaUsuario() throws Exception {

                String[] resposta = new WebService().get(URL_WS + "buscarUsuarios");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaUsuarios.add(gson.fromJson(array.get(i), Usuario.class));
                        }
                        return listaUsuarios;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }

        public int inserirUsuario(Usuario usuario) throws Exception {
                
                Gson gson = new Gson();
                int id = 0;
                String clienteJSON = gson.toJson(usuario);
                
                String[] resposta = new WebService().post(URL_WS + "inserirUsuario", clienteJSON);
                
                if (resposta[0].equals("200")) {
                        id = gson.fromJson(resposta[1], Integer.class);
                } else {
                        throw new Exception(resposta[1]);
                }
                return id;              
        }
        
        public String atualizarUsuario(Usuario usuario) throws Exception {
                
                Gson gson = new Gson();
                String clienteJSON = gson.toJson(usuario);
                
                String[] resposta = new WebService().post(URL_WS + "atualizarUsuario", clienteJSON);
                
                if (resposta[0].equals("200")) {
                        return resposta[1];
                } else {
                        throw new Exception(resposta[1]);
                }
                
        }
        
        public String deletarUsuario(int id) {
                
                String[] resposta = new WebService().get(URL_WS + "deletarUsuario/" + id);
                return resposta[1];
        }
        
        public List<Usuario> listarUsuarios() throws Exception {
                String[] resposta = new WebService().get(URL_WS + "listarUsuarios");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaUsuarios.add(gson.fromJson(array.get(i), Usuario.class));
                        }
                        return listaUsuarios;
                        
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<Usuario> listarUsuariosNaoSincronizados(String data) throws Exception {
                data = data.replace(" ", "_");
                
                String[] resposta = new WebService().get(URL_WS + "listarUsuariosNaoSincronizados/"+data);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaUsuarios.add(gson.fromJson(array.get(i), Usuario.class));
                        }
                        return listaUsuarios;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }

        
        /*************************************************************/
        /*****************               USUARIOS               **********************/
        /*************************************************************/
        
        
        public Clientes getCliente(long id) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "cliente/id/" + id);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Clientes cliente = gson.fromJson(resposta[1], Clientes.class);
                        return cliente;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public Clientes getCliente(String login) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "cliente/login/" + login);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Clientes cliente = gson.fromJson(resposta[1], Clientes.class);
                        return cliente;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public int autentica_cliente(String login, String senha) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "autentica_cliente/" + login + "/" + senha);
                                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        int quantidade = gson.fromJson(resposta[1], Integer.class);
                        return quantidade;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<Clientes> getListaCliente() throws Exception {

                String[] resposta = new WebService().get(URL_WS + "buscarClientes");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaClientes.add(gson.fromJson(array.get(i), Clientes.class));
                        }
                        return listaClientes;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }

        public int inserirCliente(Clientes cliente) throws Exception {
                
                Gson gson = new Gson();
                int id = 0;
                String clienteJSON = gson.toJson(cliente);
                
                String[] resposta = new WebService().post(URL_WS + "inserirCliente", clienteJSON);
                
                if (resposta[0].equals("200")) {
                        id = gson.fromJson(resposta[1], Integer.class);
                } else {
                        throw new Exception(resposta[1]);
                }
                return id;              
        }
        
        public String atualizarCliente(Clientes cliente) throws Exception {
                
                Gson gson = new Gson();
                String clienteJSON = gson.toJson(cliente);
                
                String[] resposta = new WebService().post(URL_WS + "atualizarCliente", clienteJSON);
                
                if (resposta[0].equals("200")) {
                        return resposta[1];
                } else {
                        throw new Exception(resposta[1]);
                }
                
        }
        
        public String deletarCliente(int id) {
                
                String[] resposta = new WebService().get(URL_WS + "deletarCliente/" + id);
                return resposta[1];
        }
        
        public List<Clientes> listarClientes() throws Exception {
                String[] resposta = new WebService().get(URL_WS + "listarClientes");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaClientes.add(gson.fromJson(array.get(i), Clientes.class));
                        }
                        return listaClientes;
                        
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<Clientes> listarClientesNaoSincronizados(String data) throws Exception {
                data = data.replace(" ", "_");
                
                String[] resposta = new WebService().get(URL_WS + "listarClientesNaoSincronizados/"+data);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaClientes.add(gson.fromJson(array.get(i), Clientes.class));
                        }
                        return listaClientes;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        /*************************************************************/
        /*****************               PEDIDOS               **********************/
        /*************************************************************/
        
        
        public Pedidos getPedidos(long id) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "pedidos/id/" + id);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Pedidos pedidos = gson.fromJson(resposta[1], Pedidos.class);
                        return pedidos;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        //////////???????????????????????????????????????///////////////////////////////
        public Pedidos getPedidos(String login) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "pedidos/login/" + login);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        Pedidos pedidos = gson.fromJson(resposta[1], Pedidos.class);
                        return pedidos;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
       ////////////////////////////////???????????????????????////////////////////////////
        
        public List<Pedidos> getListaPedidos() throws Exception {

                String[] resposta = new WebService().get(URL_WS + "buscarPedidos");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Pedidos> listaPedidos = new ArrayList<Pedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaPedidos.add(gson.fromJson(array.get(i), Pedidos.class));
                        }
                        return listaPedidos;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }

        public int inserirPedidos(Pedidos pedidos) throws Exception {
                
                Gson gson = new Gson();
                int id = 0;
                String pedidosJSON = gson.toJson(pedidos);
                
                String[] resposta = new WebService().post(URL_WS + "inserirPedidos", pedidosJSON);
                
                if (resposta[0].equals("200")) {
                        id = gson.fromJson(resposta[1], Integer.class);
                } else {
                        throw new Exception(resposta[1]);
                }
                return id;              
        }
        
        public String atualizarPedidos(Pedidos pedidos) throws Exception {
                
                Gson gson = new Gson();
                String pedidosJSON = gson.toJson(pedidos);
                
                String[] resposta = new WebService().post(URL_WS + "atualizarPedidos", pedidosJSON);
                
                if (resposta[0].equals("200")) {
                        return resposta[1];
                } else {
                        throw new Exception(resposta[1]);
                }
                
        }
        
        public String deletarPedidos(int id) {
                
                String[] resposta = new WebService().get(URL_WS + "deletarPedidos/" + id);
                return resposta[1];
        }
        
        public List<Pedidos> listarPedidos() throws Exception {
                String[] resposta = new WebService().get(URL_WS + "listarPedidos");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Pedidos> listaPedidos = new ArrayList<Pedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaPedidos.add(gson.fromJson(array.get(i), Pedidos.class));
                        }
                        return listaPedidos;
                        
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<Pedidos> listarPedidosNaoSincronizados(String data) throws Exception {
                data = data.replace(" ", "_");
                
                String[] resposta = new WebService().get(URL_WS + "listarPedidosNaoSincronizados/"+data);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<Pedidos> listaPedidos = new ArrayList<Pedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaPedidos.add(gson.fromJson(array.get(i), Pedidos.class));
                        }
                        return listaPedidos;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        
        
        
        /*************************************************************/
        /*****************              Itens PEDIDOS               **********************/
        /*************************************************************/
        
        
        public ItensPedidos getItensItensPedidos(long id) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "itenspedidos/id/" + id);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ItensPedidos itenspedidos = gson.fromJson(resposta[1], ItensPedidos.class);
                        return itenspedidos;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        //////////???????????????????????????????????????///////////////////////////////
        public ItensPedidos getItensPedidos(String login) throws Exception {

                String[] resposta = new WebService().get(URL_WS + "itenspedidos/login/" + login);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ItensPedidos itenspedidos = gson.fromJson(resposta[1], ItensPedidos.class);
                        return itenspedidos;
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
       ////////////////////////////////???????????????????????////////////////////////////
        
        public List<ItensPedidos> getListaItensPedidos() throws Exception {

                String[] resposta = new WebService().get(URL_WS + "buscarItensPedidos");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<ItensPedidos> listaItensPedidos = new ArrayList<ItensPedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaItensPedidos.add(gson.fromJson(array.get(i), ItensPedidos.class));
                        }
                        return listaItensPedidos;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }

        public int inserirItensPedidos(ItensPedidos itenspedidos) throws Exception {
                
                Gson gson = new Gson();
                int id = 0;
                String itenspedidosJSON = gson.toJson(itenspedidos);
                
                String[] resposta = new WebService().post(URL_WS + "inserirItensPedidos", itenspedidosJSON);
                
                if (resposta[0].equals("200")) {
                        id = gson.fromJson(resposta[1], Integer.class);
                } else {
                        throw new Exception(resposta[1]);
                }
                return id;              
        }
        
        public String atualizarItensPedidos(ItensPedidos itenspedidos) throws Exception {
                
                Gson gson = new Gson();
                String itenspedidosJSON = gson.toJson(itenspedidos);
                
                String[] resposta = new WebService().post(URL_WS + "atualizarItensPedidos", itenspedidosJSON);
                
                if (resposta[0].equals("200")) {
                        return resposta[1];
                } else {
                        throw new Exception(resposta[1]);
                }
                
        }
        
        public String deletarItensPedidos(int id) {
                
                String[] resposta = new WebService().get(URL_WS + "deletarItensPedidos/" + id);
                return resposta[1];
        }
        
        public List<ItensPedidos> listarItensPedidos() throws Exception {
                String[] resposta = new WebService().get(URL_WS + "listarItensPedidos");
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<ItensPedidos> listaItensPedidos = new ArrayList<ItensPedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaItensPedidos.add(gson.fromJson(array.get(i), ItensPedidos.class));
                        }
                        return listaItensPedidos;
                        
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        public List<ItensPedidos> listarItensPedidosNaoSincronizados(String data) throws Exception {
                data = data.replace(" ", "_");
                
                String[] resposta = new WebService().get(URL_WS + "listarItensPedidosNaoSincronizados/"+data);
                
                if (resposta[0].equals("200")) {
                        Gson gson = new Gson();
                        ArrayList<ItensPedidos> listaItensPedidos = new ArrayList<ItensPedidos>();
                        JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
                    
                    for (int i = 0; i < array.size(); i++){
                        listaItensPedidos.add(gson.fromJson(array.get(i), ItensPedidos.class));
                        }
                        return listaItensPedidos;
                        
                } else {
                        throw new Exception(resposta[1]);
                }
        }
        
        
}