var itemArrays=[];
function deleteItem(itemID){
    $.ajax({
        url:"/cart/deleteItem",
        type:"post",
        datatype:"json",
        data:{"itemID":itemID},
        success:function (data) {
            console.log(data);
            // 局部更新，待改善，不用全局重载
            window.location.reload();
        },
        error:function (data) {
            alert("请联系管理员qq：1720878303")
        }
    });
}

//点击复选框,商品id待修正
function addToSettlement(itemID){
    var total=0;
    itemArrays=[];
    var checkboxs=document.getElementsByTagName("input");
    //    console.log(itemID);
    // console.log("checkboxs============");
    // console.log(checkboxs);
    // console.log("len:"+checkboxs.length);

    // console.log(checkboxs[itemID-1].checked)
    for(var i=0;i<checkboxs.length;i++){

        if(checkboxs[i].checked){
            var temp=i*7+2;
            var t=$("#"+(i+1)).parent().parent().find(".item");
            // console.log("t============");
            // console.log(t);
            // console.log(t[temp].innerText);

            total=total+parseFloat(t[temp].innerText);
            // console.log("total="+total)
            itemArrays.push(i+1);
        }
    }
    var res=$("#totalPrice");
    res[0].innerText=total;
    // console.log(res[0].innerText)
    // console.log(itemArrays)
    console.log("totalPriceInnerHTML=============")

}


//提交订单
$("#submitOrder").click(function () {
    // console.log("itemArrrayds=============")
    // console.log(itemArrays)

    $.ajax({
        url:"/cart/modifySettleState" ,
        type:"post",
        datatype:"json",
        data:{"arrs":JSON.stringify(itemArrays),"totalPrice":$("#totalPrice")[0].innerText},
        success:function (data) {
            console.log(data);
            var obj=JSON.parse(data);
            if(obj.success=="yes"){
                console.log("tiaozhuan")
                window.location.href="/cart/accessOrder";
            }
            else{
                alert("请选择结算商品！")
            }
        },
        error:function (data) {
            alert("error,请联系管理员qq：1720878303")
        }
    });
});