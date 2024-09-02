

//获取地址栏参数
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

$('#btn-pay').click(function () {
    var phone=GetQueryString("phone");
    var bookid=GetQueryString("bookid");
    var quantity=GetQueryString("quantity");
    $.ajax({
        url:"/insertNewOrder",
        data:{
            "phone":phone,
            "bookid":bookid,
            "quantity":quantity
        },
        type:"post",
        success:function (data) {
            alert(data)
            if(data==true){
                window.location.href="/";//////////////////////////进入付款
            }else{
                alert("添加order记录失败")
            }
        },
        error:function (data) {
            alert("ajax  -btnpay.click失败")
        }
    })
})

// 取消付款
$('#btn-cancel').click(function () {
    alert("d")
    var url="/buyer/product/detail?id="+GetQueryString("bookid");
    console.log(url)
    window.location.href=url;
})