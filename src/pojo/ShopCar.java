package pojo;

import java.util.*;

import pojo.Decorations;

public class ShopCar {
	private HashMap<String, Decorations> buyList;// 用来存储购买的商品,键为decNo,值为decorations类对象
	// 无参构造

	public ShopCar() {
		this.buyList = new HashMap<String, Decorations>();

	}

	// 返回购物车
	public HashMap<String, Decorations> getBuyList() {
		return buyList;
	}

	// 添加购物车，封装了要添加的商品信息，参数num为要添加的商品数量
	public void addItem(Decorations decoration, int num) {
		if (decoration != null) {
			String decNo = decoration.getDecNo();
			Decorations temp = (Decorations) buyList.get(decNo);
			if (temp != null) {
				temp.setBuyNum(temp.getBuyNum() + num);

			} else {
				temp = decoration;
				temp.setBuyNum(num);
				buyList.put(decNo, temp);

			}

		}

	}

	public void addItem(Decorations decoration) {
		addItem(decoration, 1);
	}

	// 删除商品，decNo表示商品ID
	public void removeItem(String decNo) {
		buyList.remove(decNo);
	}

	// 重载removeItem()，用来从购物车中一处一组指定名称的商品
	public void removeItem(String[] decNos) {
		if (decNos != null) {
			for (int i = 0; i < decNos.length; i++) {
				buyList.remove(decNos[i]);
			}
		}
	}

	// 减少一个商品
	public void minusItem(String decNo) {
		Decorations temp = (Decorations) buyList.get(decNo);
		if (temp != null) {
			int num = temp.getBuyNum();
			if (num > 1) {
				temp.setBuyNum(num - 1);
			} else if (num == 1) {
				buyList.remove(decNo);
			}

		}
	}
	// 清空购物车
	public void clearCar() {
		buyList.clear();
	}

}

