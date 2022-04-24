package pojo;
import java.util.*;
public class Order {
private String orderId;
private String userName;
private Date orderTime;
private float totalPrice;


public String getOrderId() {
	return orderId;
}
public void setOrdersId(String orderId) {
	this.orderId = orderId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Date getOrderTime() {
	return orderTime;
}
public void setOrderTime(Date orderTime) {
	this.orderTime = orderTime;
}
public float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}


public void createOrderId() {
	int machineId = 1;
	int hashCodeV = UUID.randomUUID().toString().hashCode();
	if(hashCodeV<0) {
		hashCodeV = -hashCodeV;
		this.orderId = machineId+String.format("%015d", hashCodeV);
	}
	
}

}
