package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 購入詳細
 * @author d-yamaguchi
 *
 */
public class BuyDetailDataBeans  implements Serializable {
	private int id;
	private int buyId;
	private int itemId;

	private int userId;
	private int totalPrice;
	private int deliveryMethodId;
	private Date buyDate;

	private String deliveryMethodName;
	private int deliveryMethodPrice;

	public BuyDetailDataBeans(){

	}

	public BuyDetailDataBeans(int buyId,  Date buyDate,int deliveryMethodId, String deliveryMethodName, int totalPrice) {
		this.buyId = buyId;
		this.deliveryMethodId = deliveryMethodId;
		this.buyDate = buyDate;
		this.deliveryMethodName = deliveryMethodName;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}
	public void setId(int buyDetailId) {
		this.id = buyDetailId;
	}
	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDeliveryMethodId() {
		return deliveryMethodId;
	}

	public void setDeliveryMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}

	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}

	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}
}