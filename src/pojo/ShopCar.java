package pojo;

import java.util.*;

import pojo.Decorations;

public class ShopCar {
	private HashMap<String, Decorations> buyList;// �����洢�������Ʒ,��ΪdecNo,ֵΪdecorations�����
	// �޲ι���

	public ShopCar() {
		this.buyList = new HashMap<String, Decorations>();

	}

	// ���ع��ﳵ
	public HashMap<String, Decorations> getBuyList() {
		return buyList;
	}

	// ��ӹ��ﳵ����װ��Ҫ��ӵ���Ʒ��Ϣ������numΪҪ��ӵ���Ʒ����
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

	// ɾ����Ʒ��decNo��ʾ��ƷID
	public void removeItem(String decNo) {
		buyList.remove(decNo);
	}

	// ����removeItem()�������ӹ��ﳵ��һ��һ��ָ�����Ƶ���Ʒ
	public void removeItem(String[] decNos) {
		if (decNos != null) {
			for (int i = 0; i < decNos.length; i++) {
				buyList.remove(decNos[i]);
			}
		}
	}

	// ����һ����Ʒ
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
	// ��չ��ﳵ
	public void clearCar() {
		buyList.clear();
	}

}

