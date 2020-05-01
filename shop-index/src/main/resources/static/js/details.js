function pageUp() {
    var arr1 = ["book10_nav.png", "book8_nav.jpg", "book9_nav.jpg"];
    var obj = document.getElementById("love");
    var imgs = obj.getElementsByTagName("img");
    for (var i = 0; i < imgs.length; i++) {
        imgs[i].src = "/images/" + arr1[i];
    }
}

function pageDown() {
    var arr2 = ["book2_nav.jpg", "book3_nav.jpg", "book4_nav.jpg"];
    var obj = document.getElementById("love");
    var imgs = obj.getElementsByTagName("img");

    for (var i = 0; i < imgs.length; i++) {
        imgs[i].src = "/images/" + arr2[i];
    }
}

function selectByCount(obj) {
    if (obj.value.trim() == "") {
        obj.value = 1;
    }
}

/**
 * 添加购物车方法
 */
function addShop() {
    if ($("#shopNum").val() == '') {
        layer.alert("商品数量不能为空", {icon: 2, time: 2000});
        return;
    }
    $.ajax({
        async: false,
        url: "/shop/addShop",
        type: 'GET',
        data: {
            goodsId: $("#goodId").val(),
            num: $("#shopNum").val()
        },
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                layer.alert("成功添加购物车!", {icon: 1, time: 2000}, function (index, item) {
                    location.reload();
                });
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

function confirmOrder() {
    // 判断用户是否登陆
    if($('#loginStatus').val() == '0')
    {
        layer.alert('请先登陆!', {icon: 2, time: 2000});
        return;
    }

    var imageUrl = $('#imageUrl').attr('src');
    var goodsName = $('#goodsName').text();
    var price = $('#sellPrice').val();
    var num = $('#shopNum').val();
    if ($("#shopNum").val() == '') {
        layer.alert("商品数量不能为空", {icon: 2, time: 2000});
        return;
    }
    var timestamp = (new Date()).getTime();
    window.location.href = "/order/confirmI?goodsId=" + $('#goodId').val() + "&orderNum=" + timestamp + "&imageUrl=" + imageUrl + "&goodsName=" + goodsName + "&price=" + price + "&num=" + num;
}