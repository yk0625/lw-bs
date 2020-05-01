//导航栏公共方法

$(function () {
    //刷新用户信息
    refreshAccountInfo();
    // 刷新右侧猜你喜欢信息
    if ($('#love_div_id').length > 0) {
        changeBatch();
    }
    // 加载右侧购物车信息
    if ($("#shopTotalNum").length > 0) {
        countShopInfo();
    }
    $('#registerModel').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
})

//注册表单提交点击事件
function register() {
    // 判断两次密码是否一致
    var passwordValue = $("#passwordId").val();
    if(passwordValue.length < 6 || passwordValue.length > 8)
    {
        layer.alert("密码必须输入6-8位", {icon: 2, time: 2000});
        return;
    }
    var passwordConfirm = $("#passwordId2").val();
    if (passwordValue != passwordConfirm) {
        layer.alert("两次输入的密码不相同", {icon: 2, time: 2000});
        return;
    }
    $.ajax({
        url: "/account/register",
        type: 'GET',
        data: $('#registerForm').serialize(),
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                $("#registerModel").modal("hide");
                layer.alert("注册成功！", {icon: 1, time: 2000});
                // 修改页面展示用户信息
                $("#beforeLogin").hide();
                $("#accountNameSpan").html(data.object);
                $("#afterLogin").show();
            } else {
                layer.alert(data.message, {icon: 2, time: 2000});
            }
        },
        error: function (data) {
            console.info(data);
            layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
        }
    });
}

/**
 * 用户登录
 */
function login() {
    $.ajax({
        url: "/account/login",
        type: 'GET',
        data: $('#loginForm').serialize(),
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                $("#loginModel").modal("hide");
                layer.alert("登录成功！", {icon: 1, time: 2000});
                // 修改页面展示用户信息
                $("#beforeLogin").hide();
                $("#accountNameSpan").html(data.object);
                $("#afterLogin").show();
                window.location.reload();
            } else {
                layer.alert(data.message, {icon: 2, time: 2000});
            }
        },
        error: function (data) {
            console.info(data);
            layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
        }
    });
}

// 刷新用户信息
function refreshAccountInfo() {
    $.ajax({
        url: "/account/accountInfo",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                // 修改页面展示用户信息
                $("#beforeLogin").hide();
                $("#accountNameSpan").html(data.object);
                $("#afterLogin").show();
                $('#loginStatus').val('1');
            } else if (data.code == 1002) {
                // 用户未登录, 不做提示
            } else {
                layer.alert(data.message, {icon: 2, time: 2000});
            }
        },
        error: function (data) {
            console.info(data);
            layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
        }
    });
}

function exitLogin() {
    layer.confirm('确认退出嘛!', {btn: ['确定', '取消'], title: "提示"}, function () {
        $.ajax({
            url: "/account/exit",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.code == 200) {
                    layer.alert("成功退出", {icon: 1, time: 2000});
                    $('#loginStatus').val('0');
                    // 刷新当前页面
                    window.location.reload();
                } else {
                    layer.alert(data.message, {icon: 2, time: 2000});
                }
            },
            error: function (data) {
                console.info(data);
                layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
            }
        });
    })
}

function changeBatch() {
    $.ajax({
        async: false,
        url: "/products/changeBatch",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                loveDataHandler(data, 'love_div_id');
            } else {
                layer.alert(data.message, {icon: 2, time: 2000});
            }
        },
        error: function (data) {
            console.info(data);
            layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
        }
    });
}

// 猜你喜欢数据加载
function loveDataHandler(data, love_div_id) {
    $('#' + love_div_id).html('');
    var list = data.object;
    for (var i = 0; i < (list.length > 3 ? 3 : list.length); i++) {
        var row = `
            <div class="col-sm-12 col-md-12 wow fadeInRight animated">
              <div class="thumbnail">
                <a href="/order/detail/${list[i].id}"><img src="${list[i].goodsImage}" style="height: 200px; " alt="通用的占位符缩略图 "></a>
                <div class="caption ">
                  <h3>${list[i].goodsName}<em> ${list[i].colorNum}</em></h3>
                </div>
              </div>
            </div>
            `
        $('#' + love_div_id).append(row);
    }
}

// 右侧购物车信息总结
function countShopInfo() {
    $.ajax({
        async: false,
        url: "/shop/countShop",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                $("#shopTotalNum").html(data.object.totalNum + '个商品');
                $("#shopTotalPrice").html('总价' + data.object.totalPrice + '元');
            } else {
                layer.alert(data.message, {icon: 2, time: 2000});
            }
        },
        error: function (data) {
            console.info(data);
            layer.alert("系统发生错误，请稍后再试", {icon: 2, time: 2000});
        }
    });
}