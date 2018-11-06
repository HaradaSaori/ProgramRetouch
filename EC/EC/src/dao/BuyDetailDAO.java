package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import base.DBManager;
import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;

/**
 *
 * @author d-yamaguchi
 *
 */
public class BuyDetailDAO {

	/**
	 * 購入詳細登録処理
	 * @param bddb BuyDetailDataBeans
	 * @throws SQLException
	 * 			呼び出し元にスローさせるため
	 */
	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy_detail(buy_id,item_id) VALUES(?,?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());
			st.executeUpdate();
			System.out.println("inserting BuyDetail has been completed");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return {BuyDataDetailBeans}
	 * @throws SQLException
	 */
	public ArrayList<BuyDetailDataBeans> getBuyDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_buy_detail WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<BuyDetailDataBeans> buyDetailList = new ArrayList<BuyDetailDataBeans>();

			while (rs.next()) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setId(rs.getInt("id"));
				bddb.setBuyId(rs.getInt("buy_id"));
				bddb.setItemId(rs.getInt("item_id"));
				buyDetailList.add(bddb);
			}

			System.out.println("searching BuyDataBeansList by BuyID has been completed");
			return buyDetailList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	* 購入IDによる購入詳細情報検索
	* @param buyId
	* @return buyDetailItemList ArrayList<ItemDataBeans>
	*             購入詳細情報のデータを持つJavaBeansのリスト
	* @throws SQLException
	*/
	public static ArrayList<ItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT m_item.id,"
							+ " m_item.name,"
							+ " m_item.price"
							+ " FROM t_buy_detail"
							+ " JOIN m_item"
							+ " ON t_buy_detail.item_id = m_item.id"
							+ " WHERE t_buy_detail.buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> buyDetailItemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));

				buyDetailItemList.add(idb);
			}

			System.out.println("searching ItemDataBeansList by BuyID has been completed");
			return buyDetailItemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public BuyDataBeans userData(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			//SELECT文
			String sql = "SELECT * FROM t_buy INNER JOIN m_delivery_method ON t_buy.delivery_method_id = m_delivery_method.id INNER JOIN t_buy_detail ON t_buy.id = t_buy_detail.buy_id WHERE buy_id = ?";

			// SELECTを実行し、結果表（ResultSet）を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();

			BuyDataBeans bdb = null;

			while (rs.next()) {
				int Id = rs.getInt("id");
				Date buyDate = rs.getTimestamp("create_date");
				int deliveryMethodId = rs.getInt("delivery_method_id");
				String deliveryMethodName = rs.getString("m_delivery_method.name");
				int totalPrice = rs.getInt("total_price");

				bdb = new BuyDataBeans(Id, buyDate, deliveryMethodId, deliveryMethodName, totalPrice);

			}

			pStmt.close();

			return bdb;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}
		return null;
	}

}
