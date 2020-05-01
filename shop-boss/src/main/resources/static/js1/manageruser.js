//添加新用户
// function newUsers(){
//     //添加用户对话框
//     $('#adddg').dialog('open').dialog('setTitle','添加用户');
//     //数据清空
//     $('#fam').form('clear');
//
// }

//用户信息修改
function editUsers() {
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $('#modifydg').dialog('open').dialog('setTitle', '修改信息');
        //显示未修改前的用户信息
        $('#fim').form('load', row);
    }
}


//信息保存按钮事件
function saveUsers() {
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if (row == null) {
        add = "/u/update?id=0";
    } else {
        add = "/u/update?id=" + row.id;
    }

    $('#fim').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//用户信息添加按钮事件
function addUsers() {
    var add = "/user/save_users";
    $('#fam').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}


//用户删除按钮事件
function removeUsers() {
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '确定要删除该用户?', function (r) {
            if (r) {

                $.post('/u/remove_account', {id: row.id}, function (result) {
                    if (result.success) {

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }, 'json');
            }
        });
    }
}

// 添加商品类别信息
function newProductsType() {
    $('#adddg').dialog('open').dialog('setTitle', '添加商品类型');
    //数据清空
    $('#fam').form('clear');
}

//添加商品类型按钮时间
function addProductsType() {
    var add = "/p/save_productsType";
    $('#fam').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

function modifyProductsType() {
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if (row == null) {
        add = "/p/update?id=0";
    } else {
        add = "/p/update?id=" + row.id;
    }

    $('#fim').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            console.info(result);
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//商品类别信息修改
function editProductsType() {
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $('#modifydg').dialog('open').dialog('setTitle', '修改商品类型信息');
        //显示未修改前的用户信息
        $('#fim').form('load', row);
    }
}

//商品类别删除按钮事件
function removeProductsType() {
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '确定要删除该商品类型?', function (r) {
            if (r) {

                $.post('/p/remove_productsType', {id: row.id}, function (result) {
                    if (result.success) {

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }, 'json');
            }
        });
    }
}

// ---------------------------------------------------------------商品信息JS------------------------------------------------------------------------------------------


// 添加商品信息
function newProducts() {
    $('#adddg').dialog('open').dialog('setTitle', '添加商品信息');
    //数据清空
    $('#fam').form('clear');
}

//商品删除按钮事件
function removeProducts() {
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', '确定要删除该商品信息?', function (r) {
            if (r) {

                $.post('/p/remove_products', {id: row.id}, function (result) {
                    if (result.success) {

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }, 'json');
            }
        });
    }
}

//商品信息修改
function editProducts() {
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $('#modifydg').dialog('open').dialog('setTitle', '修改商品信息');
        //显示未修改前的用户信息
        $('#fim').form('load', row);
    }
}


//添加商品信息按钮事件
function addProducts() {
    var add = "/p/save_products";
    $('#fam').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

// 修改商品信息按钮事件
function modifyProducts() {
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if (row == null) {
        add = "/p/updateProducts?id=0";
    } else {
        add = "/p/updateProducts?id=" + row.id;
    }

    $('#fim').form('submit', {
        url: add,
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (result) {
            console.info(result);
            var result = eval('(' + result + ')');
            if (result.success) {

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}


// ------------------------------------- 订单JS --------------------------------------------------------------------------------

function confirmOrder(status) {
    var row = $('#datagrid').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm', (status == 'shipMented' ? '确认发货嘛?' : (status == 'rejected' ? '确认拒绝发货嘛?' : '未获取到状态值!')), function (r) {
            if (r) {

                $.post('/o/confirmOrder', {id: row.id, status: status}, function (result) {
                    if (result.success) {

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: (status == 'shipMented' ? '发货成功' : (status == 'rejected' ? '已拒绝发货!' : '未获取到状态值!'))
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }, 'json');
            }
        });
    } else {
        $.messager.show({	// show error message
            title: 'Error',
            msg: '请先选择需要操作的数据!'
        });
    }
}

// 打印定单
function orderExport() {
    window.location.href = '/o/orderHistory/export';
}
















