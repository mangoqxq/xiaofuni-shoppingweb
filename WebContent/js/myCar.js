 function selectAll() {
    	//prop()方法设置或返回被选元素的属性和值
        var checkAllEle = $("#checkAll").prop("checked");
        var checkOnes = $("[name='checkOne']");
      //用each()方法遍历每一个单选框，并设置单选框的值和全选框的值相同
        checkOnes.each(function () {
            $(this).prop("checked", checkAllEle);
            selectCal();
        })

        function selectCal() {
            for (var i = 0; i < checkOnes.length; i++) {
                if (checkOnes[i].checked == true) {
                    var total_num = 0;//定义商品总价变量
                    var s_subtotal = $(".subtotal");//小计
                    //使用for循环，如果单选框被选中则将小计值加进商品总价中
                    for (var i = 0; i < checkOnes.length; i++) {
                        if (checkOnes[i].checked == true) {
                            total_num += Number(s_subtotal[i].innerHTML);
                            $("#total").html(total_num);
                        }
                    }
                    if (checkAllEle == false) {
                        $("#total").html("0");//如果全选框为0，商品总价为0
                    }
                }
            }
        }
    }
//事件会在页面加载完成后触发
    window.onload = function cal() {
        //小计
        var get_uPrice = $(".unitPrice");
        //for循环，获取饰品的价格，toFixed() 方法可把 Number四舍五入为2位小数的数字
        for (var i = 0; i < get_uPrice.length; i++) {
            var numf = Number(get_uPrice[i].innerHTML);
            $(".subtotal")[i].innerHTML = numf.toFixed(2);
        }
        //计算总价
        //获取复选框
        var ache = $("[name='checkOne']");
        function calculate(obj) {
            var total_num = 0;
            var s_subtotal = $(".subtotal");
            for (var i = 0; i < ache.length; i++) {
                if (ache[i].checked == true) {
                    total_num += Number(s_subtotal[i].innerHTML);
                }
            }
            $("#total").text(total_num);
        }

        for (var i = 0; i < ache.length; i++) {
            ache[i].onclick = function () {
                calculate(this);
                var count = 0;
                //定义一个计数器
                for (var m = 0; m < ache.length; m++) {
                    //判断是否取消全选
                    if (!ache[m].checked) {
                        $("#checkAll").prop("checked", false);
                    } else { //如果是选中状态，计数器+1
                        count++;
                    }
                    //判断是否都是选中状态/如果是则自动选中全选按钮
                    if (count == ache.length) {
                        $("#checkAll").prop("checked", true);
                    }
                }
            }
        }
        //加减框及价格计算
        var atd = $("#lists").children("tr");
        for (var i = 0; i < atd.length; i++) {
            fn(atd[i]);
        }

       function fn(atd) {
            var anum = atd.getElementsByClassName("num");
            var opri = atd.getElementsByClassName("unitPrice")[0];
            var ospan = atd.getElementsByClassName("subtotal")[0];
            var otxt = atd.getElementsByClassName("amount")[0];
            var num1 = Number(otxt.value);
            var num2 = Number(opri.innerHTML);
            //点击事件
            anum[0].onclick = function () {
                num1--;
                if (num1 < 0) {
                    num1 = 0;
                }
                otxt.value = num1;
                ospan.innerHTML = (num1 * num2).toFixed(2);
                calculate(this);
            };
            anum[1].onclick = function () {
                num1++;
                otxt.value = num1;
                ospan.innerHTML = (num1 * num2).toFixed(2);
                calculate(this);
            }
        }

    }