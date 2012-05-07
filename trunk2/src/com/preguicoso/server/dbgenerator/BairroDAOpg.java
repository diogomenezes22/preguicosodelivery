package com.preguicoso.server.dbgenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BairroDAOpg {

	// public void inserir(Bairropg b) {
	// try {
	// Connection conn = ConnectionFactory.createConnection();
	// PreparedStatement pstm = conn
	// .prepareStatement("INSERT INTO item_category(type) "
	// + "VALUES (?)");
	// pstm.setString(1, category.getType());
	//
	// pstm.executeUpdate();
	// pstm.close();
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	// public void atualizar(ItemCategory category) {
	// try {
	// Connection conn = ConnectionFactory.createConnection();
	// PreparedStatement pstm = conn
	// .prepareStatement("UPDATE item_category " + "SET type=? "
	// + "WHERE id_item_category = ?");
	// pstm.setString(1, category.getType());
	// pstm.setInt(2, category.getIdItemCategory());
	//
	// pstm.executeUpdate();
	// pstm.close();
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	// public void excluir(int idItemCategory) {
	// try {
	// Connection conn = ConnectionFactory.createConnection();
	// PreparedStatement pstm = conn
	// .prepareStatement("DELETE FROM item_category WHERE id_item_category = ?");
	// pstm.setInt(1, idItemCategory);
	//
	// pstm.executeUpdate();
	// pstm.close();
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }

	public List<Bairropg> listar(String cidade) {
		ArrayList<Bairropg> lista = new ArrayList<Bairropg>();
		Bairropg b;
		try {
			Connection conn = ConnectionFactory.createConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT b.cd_bairro, b.cd_cidade, b.ds_bairro_nome "
							+ "FROM bairros b, cidades c "
							+ "WHERE b.cd_cidade=c.cd_cidade AND c.ds_cidade_nome=\'"
							+ cidade + "\'");
			while (rs.next()) {
				b = new Bairropg(rs.getLong("cd_bairro"),
						rs.getLong("cd_cidade"), rs.getString("ds_bairro_nome"));
				lista.add(b);
			}
			rs.close();
			stm.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// public int getIdItemCategory(String type) throws TypeNotFoundException {
	// List<ItemCategory> lista = listar();
	// for (ItemCategory i : lista) {
	// if (i.getType().endsWith(type)) {
	// return i.getIdItemCategory();
	// }
	// }
	// throw new TypeNotFoundException("O tipo procurado n�o existe!");
	// }
	//
	// public String getType(int idItemCategory) {
	// String type = "";
	// try {
	// Connection conn = ConnectionFactory.createConnection();
	// Statement stm = conn.createStatement();
	// ResultSet rs = stm.executeQuery("SELECT * FROM item_category "
	// + "WHERE id_item_category = " + idItemCategory);
	//
	// if (rs.next()) {
	// type = rs.getString("type");
	// }
	//
	// rs.close();
	// stm.close();
	// conn.close();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// return type;
	// }
}
