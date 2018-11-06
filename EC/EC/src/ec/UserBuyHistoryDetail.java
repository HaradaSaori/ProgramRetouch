package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URLからGETパラメータとしてIDを受け取る
				String id = request.getParameter("buy_id");

				// 確認用：idをコンソールに出力
			       System.out.println(id);


				//idを引数にして、idに紐づくユーザ情報を出力する
			       BuyDetailDAO bdb = new BuyDetailDAO();
				   BuyDataBeans buydata = bdb.userData(id);

				   // ユーザ情報をリクエストスコープにセット
				   request.setAttribute("buydata",buydata);

		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}

}
