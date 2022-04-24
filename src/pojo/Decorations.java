package pojo;

public class Decorations {
private String decNo;//饰品号
	private String decCover;//饰品图片
	private String decName;//饰品名称
	private float nowPrice;//饰品现价
	private float orgPrice;//饰品原价
	private int comments;//对该饰品现有的评论条数
	private String author;//设计者
	private String pressDate;//设计年份
	private String press;//材质
	private int decNum;//库存
	private int buyNum;//购买数量
	public String getDecNo() {
		return decNo;
	}
	public void setDecNo(String decNo) {
		this.decNo = decNo;
	}
	public String getDecCover() {
		return decCover;
	}
	public void setDecCover(String decCover) {
		this.decCover = decCover;
	}
	public String getDecName() {
		return decName;
	}
	public void setDecName(String decName) {
		this.decName = decName;
	}
	public float getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(float nowPrice) {
		this.nowPrice = nowPrice;
	}
	public float getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(float orgPrice) {
		this.orgPrice = orgPrice;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPressDate() {
		return pressDate;
	}
	public void setPressDate(String pressDate) {
		this.pressDate = pressDate;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getDecNum() {
		return decNum;
	}
	public void setDecNum(int decNum) {
		this.decNum = decNum;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	
	
}
