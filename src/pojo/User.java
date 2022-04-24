package pojo;

public class User {
 private  String username="";
  private String userpwd="";
  private String sex="";
  private String birthday="";
  private String mail="";
  private String selProvince="";
  private String selCity="";
  private String work="";
  private String like="";
  private String intro="";
  
  //无参构造
  public User() {
		super();
	}
  //有参构造
  
public String getUsername() {
	return username;
}



public User(String username, String userpwd, String sex, String birthday, String mail, String selProvince,
		String selCity, String work, String like, String intro) {
	super();
	this.username = username;
	this.userpwd = userpwd;
	this.sex = sex;
	this.birthday = birthday;
	this.mail = mail;
	this.selProvince = selProvince;
	this.selCity = selCity;
	this.work = work;
	this.like = like;
	this.intro = intro;
}

public String getSelProvince() {
	return selProvince;
}

public void setSelProvince(String selProvince) {
	this.selProvince = selProvince;
}

public String getSelCity() {
	return selCity;
}

public void setSelCity(String selCity) {
	this.selCity = selCity;
}


public void setUsername(String username) {
	this.username = username;
}
public String getUserpwd() {
	return userpwd;
}
public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getWork() {
	return work;
}
public void setWork(String work) {
	this.work = work;
}


public String getLike() {
	return like;
}

public void setLike(String like) {
	this.like = like;
}

public String getIntro() {
	return intro;
}
public void setIntro(String intro) {
	this.intro = intro;
}
   
}
