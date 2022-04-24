function check(theform) {
	if (theform.username.value == "") {
		alert("用户名不能为空！");
		theform.username.focus();
		return false;
	}
	if(theform.userpwd.value==""){
		alert("密码不能为空");
		theform.userpwd.focus();
		return false;
	}
	if (theform.userpwd.value != theform.userpwd2.value) {
		alert("两次密码不一致，请重新输入！");
		theform.userpwd2.value == "";
		theform.userpwd2.focus();
		return false;
	}
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (!reg.test(theform.mail.value)) {
		alert("请填写正确的邮箱格式!");
		theform.mail.value == "";
		theform.mail.focus();
		return false;
	}
}

function usernameFocus() {
	var usernameId = document.getElementById("usernameId");
	usernameId.className = "import_prompt";
	usernameId.innerHTML = "1、由字母、数字、下划线、点、减号组成<br/>2、 只能以数字、字母开头或结尾，且长度为4-18";
}

function usernameBlur() {
	var username = document.getElementById("username");
	var usernameId = document.getElementById("usernameId");
	var reg = /^[0-9a-zA-Z][0-9a-zA-Z_.-]{2,16}[0-9a-zA-Z]$/;
	if (username.value == "") {
		usernameId.className = "error_prompt";
		usernameId.innerHTML = "用户名不能为空，请输入用户名";
		return false;
	}
	if (reg.test(username.value) == false) {
		usernameId.className = "error_prompt";
		usernameId.innerHTML = "1、由字母、数字、下划线、点、减号组成<br/>2、 只能以数字、字母开头或结尾，且长度为4-18";
		return false;
	}
	usernameId.className = "ok_prompt";
	usernameId.innerHTML = "用户名输入正确";
	return true;
}
function userpwdFocus() {
	var userpwdId = document.getElementById("userpwdId");
	userpwdId.className = "import_prompt";
	userpwdId.innerHTML = "1、必须由字母、数字组合构成<br/>2、 长度不少于9位";
}

function userpwdBlur() {
	var userpwd = document.getElementById("userpwd");
	var userpwdId = document.getElementById("userpwdId");
	var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;// 检验密码输入规则
	if (userpwd.value == "") {
		userpwdId.className = "error_prompt";
		userpwdId.innerHTML = "密码不能为空，请输入密码";
		return false;
	}
	if (reg.test(userpwd.value) == false) {
		userpwdId.className = "error_prompt";
		userpwdId.innerHTML = "1、必须由字母、数字组合构成<br/>2、 长度不少于9位"
		return false;
	}
	userpwdId.className = "ok_prompt";
	userpwdId.innerHTML = "密码输入正确";
	return true;
}

function userpwd2Focus() {
	var userpwd2Id = document.getElementById("userpwd2Id");
	userpwd2Id.className = "import_prompt";
	userpwd2Id.innerHTML = "再次确认的密码必须一致！";
}

function userpwd2Blur() {
	var userpwd2 = document.getElementById("userpwd2");
	var userpwdId = document.getElementById("userpwd2Id");
	var userpwd = document.getElementById("userpwd");
	if (userpwd2.value == "") {
		userpwd2Id.className = "error_prompt";
		userpwd2Id.innerHTML = "再次确认的密码不能为空，请输入密码";
		return false;
	}
	if (userpwd.value != userpwd2.value) {
		userpwd2Id.className = "error_prompt";
		userpwd2Id.innerHTML = "再次确认的密码必须一致，请重新输入！"
		return false;
	}
	userpwd2Id.className = "ok_prompt";
	userpwd2Id.innerHTML = "再次确认的密码输入一致";
	return true;
}

function mainFocus() {
	var mailId = document.getElementById("mailId");
	mailId.className = "import_prompt";
	mailId.innerHTML = "请输入正确格式的邮箱格式！";
}

function mailBlur() {
	var mail = document.getElementById("mail");
	var mailId = document.getElementById("mailId");
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (mail.value == "") {
		mailId.className = "error_prompt";
		mailId.innerHTML = "邮箱地址不能为空，请填写邮箱地址";
		return false;
	}
	if (reg.test(mail.value) == false) {
		mailId.className = "error_prompt";
		mailId.innerHTML = "请输入正确格式的邮箱地址！"
		return false;
	}
	mailId.className = "ok_prompt";
	mailId.innerHTML = "邮箱地址输入正确";
	return true;
}


var cityList = new Array(); 
cityList['-选择省份-'] = ['-选择城市-']; 
cityList['北京'] = [ '---', '朝阳区', '东城区', '西城区', '海淀区', '宣武区', '丰台区', '怀柔',
	'延庆 ', '房山' ];
cityList['天津'] = [ '---', '和平区', '河东区', '河西区', '南开区', '河北区', '红桥区', '滨海新区',
	'东丽区', '西青区', '津南区', '北辰区', '武清区', '宝坻区', '宁河区', '静海区', '蓟州区' ];
cityList['河北省'] = [ '---', '石家庄市', '唐山市', '秦皇岛市', '邯郸市', '邢台市', '保定市', '张家口市',
	'承德市', '沧州市', '廊坊市', '衡水市' ];
cityList['山西省'] = [ '---', '太原市', '大同市', '阳泉市', '长治市', '晋城市', '朔州市', '晋中市',
	'运城市', '忻州市', '临汾市', '吕梁市' ];
cityList['内蒙古自治区'] = [ '---', '呼和浩特市', '包头市', '乌海市', '赤峰市', '通辽市', '鄂尔多斯市',
	'呼伦贝尔市', '巴彦淖尔市', '乌兰察布市', '兴安盟', '锡林郭勒盟', '阿拉善盟' ];
cityList['辽宁省'] = [ '---', '沈阳市', '大连市', '鞍山市', '抚顺市', '本溪市', '丹东市', '锦州市',
	'营口市', '阜新市', '辽阳市', '盘锦市', '盘锦市', '朝阳市', '葫芦岛市' ];
cityList['吉林省'] = [ '---', '长春市', '吉林市', '四平市', '辽源市', '通化市', '白山市', '松原市',
	'白城市', '延边' ];
cityList['黑龙江省'] = [ '---', '哈尔滨市', '齐齐哈尔市', '鸡西市', '鹤岗市', '双鸭山市', '大庆市',
	'伊春市', '佳木斯市', '七台河市', '牡丹江市', '黑河市', '绥化市', '大兴安岭地区' ];
cityList['上海'] = [ '---', '黄浦区', '徐汇区', '长宁区', '静安区', '普陀区', '虹口区', '杨浦区',
	'浦东新区', '闵行区', '宝山区', '嘉定区', '金山区', '松江区', '青浦区', '奉贤区', '崇明区' ];
cityList['江苏省'] = [ '---', '南京市', '无锡市', '徐州市', '常州市', '苏州市', '南通市', '连云港市',
	'淮安市', '盐城市', '扬州市', '镇江市', '泰州市', '宿迁市' ];
cityList['浙江省'] = [ '---', '杭州市', '宁波市', '温州市', '嘉兴市', '湖州市', '绍兴市', '金华市',
	'衢州市', '舟山市', '台州市', '丽水市' ];
cityList['安徽省'] = [ '---', '合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市', '淮北市', '铜陵市',
	'安庆市', '黄山市', '滁州市', '阜阳市', '宿州市', '六安市', '亳州市', '池州市', '宣城市' ];
cityList['福建省'] = [ '---', '福州市', '厦门市', '莆田市', '三明市', '泉州市', '漳州市', '南平市',
	'龙岩市', '宁德市' ];
cityList['江西省'] = [ '---', '南昌市', '景德镇市', '萍乡市', '九江市', '新余市', '鹰潭市', '赣州市',
	'吉安市', '宜春市', '抚州市', '上饶市' ];
cityList['山东省'] = [ '---', '济南市', '青岛市', '淄博市', '枣庄市', '东营市', '烟台市', '潍坊市',
	'济宁市', '泰安市', '威海市', '日照市', '莱芜市', '临沂市', '德州市', '聊城市', '滨州市', '菏泽市' ];
cityList['河南省'] = [ '---', '郑州市', '开封市', '洛阳市', '平顶山市', '安阳市', '鹤壁市', '新乡市',
	'焦作市', '济源市', '濮阳市', '许昌市', '漯河市', '三门峡市', '南阳市', '商丘市', '信阳市', '周口市',
	'驻马店市' ];
cityList['湖北省'] = [ '---', '武汉市', '黄石市', '十堰市', '宜昌市', '襄阳市', '鄂州市', '荆门市',
	'孝感市', '荆州市', '黄冈市', '咸宁市', '随州市', '恩施', '仙桃市', '潜江市', '天门市', '神农架林区' ];
cityList['湖南省'] = [ '---', '长沙市', '株洲市', '湘潭市', '衡阳市', '邵阳市', '岳阳市', '常德市',
	'张家界市', '益阳市', '郴州市', '永州市', '怀化市', '娄底市', '湘西' ];
cityList['广东省'] = [ '---', '广州市', '韶关市', '深圳市', '珠海市', '汕头市', '佛山市', '江门市',
	'湛江市', '茂名市', '肇庆市', '惠州市', '梅州市', '汕尾市', '河源市', '阳江市', '清远市', '东莞市',
	'中山市', '东沙群岛', '潮州市', '揭阳市', '云浮市' ];
cityList['广西壮族自治区'] = [ '---', '南宁市', '柳州市', '桂林市', '梧州市', '北海市', '防城港市',
	'钦州市', '贵港市', '玉林市', '百色市', '贺州市', '河池市', '来宾市', '崇左市' ];
cityList['海南省'] = [ '---', '海口市', '三亚市', '三沙市', '五指山市', '琼海市', '儋州市', '文昌市',
	'万宁市', '东方市', '定安县', '屯昌县', '澄迈县', '临高县', '白沙', '昌江', '乐东', '陵水', '保亭',
	'琼中' ];
cityList['重庆'] = [ '---', '万州区', '黔江区', '涪陵区', '渝中区', '大渡口区', '江北区', '沙坪坝区',
	'九龙坡', '南岸区', '北碚区', '渝北区', '巴南区', '长寿区', '江津区', '合川区', '永川区', '南川区',
	'綦江区', '大足区', '璧山区', '铜梁区', '潼南区', '荣昌区', '开州区', '梁平区', '武隆区' ];
cityList['四川省'] = [ '---', '成都市', '自贡市', '攀枝花市', '泸州市', '德阳市', '绵阳市', '广元市',
	'遂宁市', '内江市', '乐山市', '南充市', '眉山市', '宜宾市', '广安市', '达州市', '雅安市', '巴中市',
	'资阳市', '阿坝', '甘孜', '凉山' ];
cityList['贵州省'] = [ '---', '贵阳市', '六盘水市', '遵义市', '安顺市', '铜仁市', '黔西南', '毕节市',
	'黔东南', '黔南' ];
cityList['云南省'] = [ '---', '昆明市', '曲靖市', '玉溪市', '保山市', '昭通市', '丽江市', '普洱市',
	'临沧市', '楚雄', '红河', '文山', '西双版纳', '大理', '德宏', '怒江', '迪庆' ];
cityList['西藏自治区'] = [ '---', '拉萨市', '昌都地区', '山南地区', '日喀则地区', '那曲地区', '阿里地区',
	'林芝地区' ];
cityList['陕西省'] = [ '---', '西安市', '铜川市', '宝鸡市', '咸阳市', '渭南市', '延安市', '汉中市',
	'榆林市', '安康市', '商洛市' ];
cityList['甘肃省'] = [ '---', '兰州市', '嘉峪关市', '金昌市', '白银市', '天水市', '武威市', '张掖市',
	'平凉市', '酒泉市', '庆阳市', '定西市', '陇南市', '临夏', '甘南' ];
cityList['青海省'] = [ '---', '西宁市', '海东市', '海北', '黄南', '海南', '果洛', '玉树', '海西' ];
cityList['宁夏回族自治区'] = [ '---', '银川市', '石嘴山市', '吴忠市', '固原市', '中卫市' ];
cityList['新疆维吾尔自治区'] = [ '---', '乌鲁木齐市', '克拉玛依市', '吐鲁番地区', '哈密地区', '昌吉',
	'博尔塔拉', '巴音郭楞', '阿克苏地区', '克孜勒苏柯尔克孜自治州', '喀什地区', '和田地区', '伊犁', '塔城地区',
	'阿勒泰地区', '石河子市', '阿拉尔市', '图木舒克市', '五家渠市' ];
cityList['台湾'] = [ '---', '台北市', '高雄市', '台南市', '台中市', '金门县', '南投县', '基隆市',
	'新竹市', '嘉义市', '新北市', '宜兰县', '新竹县', '桃园县', '苗栗县', '彰化县', '嘉义县', '云林县',
	'屏东县', '台东县', '花莲县', '澎湖县', '连江县' ];
cityList['香港特别行政区'] = [ '---', '香港岛', '香港岛', '新界' ];
cityList['澳门特别行政区'] = [ '---', '澳门', '离岛' ];
cityList['其他'] = [ '其他' ];

function changeCity() {
	var province = document.form1.selProvince;
	var city = document.form1.selCity;
	city.options.length = 0; // 清除当前city中的选项
	for ( var i in cityList) {
		if (i == province.value) {
			for ( var j in cityList[i]) {
				city.add(new Option(cityList[i][j], cityList[i][j]), null);
			}
		}
	}
}

function allCity() {
	var province = document.form1.selProvince;
	for ( var i in cityList) {
		province.add(new Option(i, i),null);
	}
	changeCity() 
}
window.onload = allCity;