//显示更多地址...
function dispalyMoreAddr(obj) {
    var addr = document.getElementById("address");
    var lis = addr.getElementsByTagName("li");
    for (var i = 0; i < lis.length; i++) {
        lis[i].className = "list-group-item";
    }

    var div = document.getElementById("addrDiv");

    obj.style.display = "none";

    var aa = document.getElementById("down");

    if (aa == null) {
        var a = document.createElement("a");
        a.href = "#";
        a.id = "down";
        a.className = "text-info";
        a.innerHTML = "收起地址↑&nbsp;&nbsp;";

        div.appendChild(a);

        var adr = document.getElementById("addAddr");

        div.insertBefore(a, adr);


        a.onclick = function () {
            this.style.display = "none";
            obj.style.display = "inline";

            for (var i = 4; i < lis.length; i++) {
                lis[i].className = "list-group-item hidden";
            }
        }
    } else {
        aa.style.display = "inline";
    }
}

$(function () {
    addressLst();
})

// 查询地址列表
function addressLst() {
    $.ajax({
        async: false,
        url: "/address/addressList",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                loadAddressInfo(data, 'address');
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

// 加载地址信息
function loadAddressInfo(data, address_div_id) {
    $('#' + address_div_id).html('');
    var list = data.object;
    if (list == null) return;
    for (var i = 0; i < list.length; i++) {
        var row = ``;
        if (list[i].flag == 1) {
            row += row + `<li class="list-group-item"><input type="radio" addressId="${list[i].id}" name="addr">&nbsp;${list[i].province}${list[i].city}${list[i].district}&nbsp;&nbsp;${list[i].detailAddress}&nbsp;<span class="text-success">默认地址</span></li>`;
        } else {
            row = `<li class="list-group-item"><input type="radio" addressId="${list[i].id}" name="addr">&nbsp;${list[i].province}${list[i].city}${list[i].district}&nbsp;&nbsp;${list[i].detailAddress}</li>`;
        }
        $('#' + address_div_id).append(row);
    }
}

//添加地址
function addAddress() {
    var checkStatus = $('#setDefaultAddr').prop("checked");
    $('#flag_id').val(checkStatus ? 1 : 0);
    $.ajax({
        url: "/address/addAddress",
        type: 'GET',
        data: $('#address_form_id').serialize(),
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                layer.alert("成功添加地址", {icon: 1, time: 2000});
                loadAddressInfo(data, 'address');
                $('#addressModal').modal('hide');
                $('#address_form_id').reset();
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

//删除地址
function delAddress() {
    var addressId = '';
    var liLst = $('#address').find('input');
    for (var i = 0; i < liLst.length; i++) {
        var checkStatus = $(liLst[i]).prop("checked");
        if (checkStatus) {
            addressId = $(liLst[i]).attr("addressId");
        }
    }
    $.ajax({
        url: "/address/delAddress",
        type: 'GET',
        data: {
            addressId: addressId
        },
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                layer.alert("成功删除地址", {icon: 1, time: 2000});
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

/**
 * 提交订单, 更新订单状态为paymented(已付款)
 */
function submitOrder() {
    var orderNum = $("#orderNum").val();
    if (orderNum == '') {
        layer.alert("订单号为空!", {icon: 2, time: 2000});
        return;
    }
    var addressId = '';
    var liLst = $('#address').find('input');
    for (var i = 0; i < liLst.length; i++) {
        var checkStatus = $(liLst[i]).prop("checked");
        if (checkStatus) {
            addressId = $(liLst[i]).attr("addressId");
        }
    }
    if(addressId == '')
    {
        layer.alert("请先选择收货地址!", {icon: 2, time: 2000});
        return;
    }
    $.ajax({
        url: "/order/submitOrder",
        type: 'GET',
        data: {
            orderNum: orderNum,
            addressId: addressId
        },
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                layer.alert("恭喜, 下单成功!", {icon: 1, time: 2000}, function (index, item) {
                    // 跳转订单信息页面
                    window.location.href = "/order/list";
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