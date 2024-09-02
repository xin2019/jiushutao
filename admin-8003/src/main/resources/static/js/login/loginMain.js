
$(function () {


    /*
    个人订单界面导航条控制
     */
    $('#PersonalOrderNav li').click(function (e) {
        $('#PersonalOrderNav li').removeClass('active')
        $(this).addClass('active')
    })


    /*
    个人设置界面
     */
    $('#centerNav li').click(function (e) {
        $('#centerNav li').removeClass('active');
        $(this).addClass('active');
    })

    window.onload=function (ev) {

        //交互后台判断登陆状态
        $.post("/buyer/getUsername", function (obj) {
            if(obj.loginUser!=null&&obj.loginUser!=""){
                sessionStorage['loginUser']=obj.loginUser;
                // alert("post--"+obj.loginUser)
                $("#loginSpan").removeAttr("href");
                $("#loginSpan").removeAttr("data-toggle");
                $("#myModal").modal('hide');

                $("#loginSpan").html(obj.loginUser + " 欢迎你!");

                $("#loginSpan").addClass("dropdown-toggle");
                $("#loginSpan").attr("data-toggle","dropdown");
                $("#loginSpan").attr("data-target","#encompassLoginSpan")
                $("#encompassLoginSpan").addClass("dropdown");

            }else {
                sessionStorage.clear();
                alert(sessionStorage['loginUser']+"logout failed")
            }

        }, "json");
    }


    //以下是各个功能模块

    //登陆/注册 模块
    $('#loginBtn').click(function () {

        $.ajax({
            url: "/buyer/loginBefore",
            type: "POST",
            data: {
                "username": $("input[name='username']").val(),
                "password": $("input[name='password']").val()
            },
            datatype: "json",
            success: function (data) {
                var obj=JSON.parse(data);
console.log("loginbefore::::::"+obj)
console.log(obj.id);
                if(obj.isExistBuyer==true){
                    sessionStorage['id']=obj.id;
                     window.location.reload();
                }else{
                 $('#showMsg').text(obj.msg);
                 //     alert("密码错误")
                }

            },
            error: function (data) {
                alert("ajax-error");
            }

        });
    });

    /*
    搜索框
     */
    $('#searchBook').click(function () {
        var searchName=$('#searchName').val();
        $.ajax({
            url: "/buyer/product/searchBooks?likename="+searchName,
            type: "get",
            jsontype: "json",
            success: function (data) {
                console.log(data)
                var obj=JSON.parse(data)
                var msg=obj.msg;
                $('#formalContent').addClass('hidden');
                $('#searchNull').addClass('hidden');
                $('#searchContent').addClass('hidden');
                if(msg!=null&&msg!=""){


                    $('#searchNull').removeClass('hidden');
                    $('#tip').html(msg)
                }
                else{
                   var books= obj.likebooks;
                    var content="";
                  for(var index in books){
                      var hrefurl="/buyer/product/detail?id="+books[index].t_book_id;
                      console.log(hrefurl)
                      content+=
                          "<div class='col-md-4' style='text-align: center'>"+
                          "<a href='"+hrefurl+"'"+
                         ">"+
                          "<img class='img-rounded img-responsive' style='width: 358px;height: 248px' src='"+
                          books[index].t_book_photo+"/1.jpg'>"+
                          "<span style='font-weight: bolder;text-align: center'>"+books[index].t_book_name+"</span>"+
                          "</a>"+
                          "</div>"
                      ;
                    $('#searchbody').html(content)
                  }
                    $('#searchContent').removeClass('hidden')
                }
            }
        })

    })



    function showOrders(){
        var loginUser=sessionStorage['loginUser'];
        var id=sessionStorage['id'];
        if(loginUser!=null&&loginUser!=""){
            window.location.href="/buyer/allOrder?id="+id;
        }else{
            $("#myModal").modal('show');
        }
    }

    //个人中心 模块
    $("#navIndividual").click(function () {
       showOrders();
    });

    //登陆后  -》我的订单模块
    $('#myOrderCommon').click(function () {
        showOrders();


    });



    //退出登陆 模块
    $('#logout').click(function () {
        $.ajax({
                url: "/buyer/loginout",
                type: "GET",

                datatype: "json",
                success: function (data) {
                   sessionStorage.clear();
                   window.location.reload();
                },
                error: function (data) {
                    alert("退出失败" +
                        "qq：1720878303");
                }

            });
    });



    //购物车 模块
    $("#navCart").click(function () {
        var loginUser=sessionStorage['loginUser'];

        if(loginUser!=null&&loginUser!=""){
            var url="/cart?phone="+loginUser;
        window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }

    });

    $("#navCart2").click(function () {
        var loginUser=sessionStorage['loginUser'];

        if(loginUser!=null&&loginUser!=""){
            var url="/cart?phone="+loginUser;
            window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }
    });

    //购物袋 侧边栏
    $("#siderCart").click(function () {
        var loginUser=sessionStorage['loginUser'];

        if(loginUser!=null&&loginUser!=""){
            var url="/cart?phone="+loginUser;
            window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }
    });

    //购物袋 搜索框旁边 模块
    $("#navCart3").click(function () {

        var loginUser=sessionStorage['loginUser'];

        if(loginUser!=null&&loginUser!=""){
            var url="/cart?phone="+loginUser;
            window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }
    });

    /*
    个人设置旁边的购物袋
     */
    $('#navCart4').click(function () {
        var loginUser=sessionStorage['loginUser'];

        if(loginUser!=null&&loginUser!=""){
            var url="/cart?phone="+loginUser;
            window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }
    })


    //注册用户界面直接登陆
    $("#returnLogin").click(function () {
        $("#myModal1").modal("hide");
        $("#myModal").modal("show");
    });


/*
我的订单模块----------------------
 */
    $('#myOrder').click(function () {
        $('#formalContentBeforeMain').removeClass('hidden')
        $('#personalSettingContent').addClass('hidden')
        $('#PersonalOrderNav li').removeClass('active')
        $('#PersonalOrderNav').find('li')[0].addClass('active')
    })







    /*
     个人中心页面各个功能---------------

     */


    /*
    个人设置
     */

    /*
    修改个人资料  修改按钮
     */


    $('#btnSubmitPersonalInfo').click(function () {
        console.log($("input[name='sex']").val())
        console.log($('#phone').val())

        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').removeClass('hidden')
       $.ajax({
           url:"personal/updateBuyer",
           type:"get",
           data:{
               "name":$("input[name='name']").val(),
               "phone":$('#phone').val(),
               "email":$("input[name='email']").val(),
               "sex":$("input[name='sex']").val()
           },
           datatype:"json",
           success:function (data) {
               alert("修改成功");
               window.location.reload()
           },
           error:function (data) {
               console.log("btnSubmitPersonalInfo------gg")
           }
       })
    })





    /*
     点击 个人订单-》 修改密码
      */
    $('#changePass').click(function () {
        $('#changePassContent').removeClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').addClass('hidden')
    })
    /*
     点击 个人订单-》 修改密码 左侧栏
      */
    $('#changePassLeft').click(function () {
        $('#changePassContent').removeClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').addClass('hidden')
    })

    /*
   点击 个人订单-》 修改密码--》原密码框失去焦点

    */
    // $('#oldPwd').onblur=function(){
    //     console.log("onblue")
    //     var phone=sessionStorage['loginUser'];
    //     var password=$('#oldPwd').val();
    //     $.ajax({
    //         url:"/buyer/isRightUser?phone="+phone+"&password="+password,
    //         type:"get",
    //         success:function (data) {
    //             alert(data)
    //         },
    //         error:function (data) {
    //             console.log("g")
    //         }
    //     })
    // }


    /*
    点击 个人订单-》 修改密码--》提交
     */


    $('#btnSubmitChangePass').click(function () {

        var phone=sessionStorage['loginUser'];
        var oldPass=$('#oldPwd').val();
        var newPass=$('#newPwd').val();
        var rePass=$('#rePwd').val();
        console.log("pas========="+oldPass)
        console.log("pho========"+phone)
        if(newPass!=rePass){
            alert("两次密码不一样");
            $('#rePwd').focus();
        }else{
            $.ajax({
                url:"/buyer/savePass",
                data:{
                    "oldPass":oldPass,
                    "phone":phone,
                    "newPass":newPass
                },

                typedata:"json",
                type:"post",
                success:function (data) {
                    var obj=JSON.parse(data);
                    alert(obj.msg);
                },
                error:function (data) {
                    console.log("gg---btnSubmitChangePass")
                }
            })
        }

    })


    /*
     点击 个人订单-》 上传头像
      */
    $('#uploadImage').click(function () {
        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').removeClass('hidden')
        $('#personalInfoContent').addClass('hidden')
    })
    /*
    点击 个人订单-》 上传头像 左侧
     */
    $('#uploadImageLeft').click(function () {
        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').removeClass('hidden')
        $('#personalInfoContent').addClass('hidden')
    })

    /*
     点击 个人订单-》 上传头像--》上传按钮
      */
    $('#btnSubmitUploadImage').click(function () {
        alert("暂未开通")
    })



    /*
    点击 个人订单-》 收货管理
     */
    $('#changeAddress').click(function () {
        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').removeClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').addClass('hidden')
        var phone=sessionStorage['loginUser'];
        $.ajax({
            url:"/buyer/personal/getAddress",
            data:{
                "phone":phone
            },
            type:"post",
            success:function (data) {
                $('#buyerName').text(data.t_buyer_name);
                console.log("ppppppppppppppppp=============>"+data.t_buyer_address)
                $('#buyerAddressP').text(data.t_buyer_address);
                $('#buyerAddress').text(data.t_buyer_address);
                $('#buyerAddress').val(data.t_buyer_address);
                $('#buyerPhone').html(data.t_buyer_phone);
                $('#buyerAddress').addClass('hidden')
            },
            error:function (data) {
                console.log("gg--changeAddress")
            }

        })
    })

    /*
    点击 个人订单-》 收货管理 左侧
     */
    $('#changeAddressLeft').click(function () {
        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').removeClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').addClass('hidden')
        var phone=sessionStorage['loginUser'];
        $.ajax({
            url:"/buyer/personal/getAddress",
            data:{
                "phone":phone
            },
            type:"post",
            success:function (data) {
                $('#buyerName').text(data.t_buyer_name);
                console.log("ppppppppppppppppp=============>"+data.t_buyer_address)
                $('#buyerAddressP').text(data.t_buyer_address);
                $('#buyerAddress').text(data.t_buyer_address);
                $('#buyerAddress').val(data.t_buyer_address);
                $('#buyerPhone').html(data.t_buyer_phone);
                $('#buyerAddress').addClass('hidden')
            },
            error:function (data) {
                console.log("gg--changeAddress")
            }

        })
    })

    /*
        点击 个人订单-》 收货管理 --》修改按钮
         */
    $('#updateAddress').click(function () {
        $('#updateAddressOver').removeClass('hidden');
        $('#buyerAddress').removeClass('hidden')
        $('#buyerAddressP').addClass('hidden')
    })
    /*
       点击 个人订单-》 收货管理 --》提交按钮
        */
    $('#updateAddressOver').click(function () {
        var phone=sessionStorage['loginUser'];
        var address=$('#buyerAddress').val();
        console.log("ddddd====>"+$('#buyerAddress').val())
        $.ajax({
            url:"/buyer/personal/updateAddress",
            type:"post",
            data:{
                "address":$('#buyerAddress').val(),
                "phone":phone
            },
            success:function (data) {
                console.log("updateAddressOver======="+data)
                if(data==true){

                    $('#buyerAddress').html(address)
                    $('#buyerAddressP').text(address)


                    alert("修改成功")

                }else{
                    alert("修改失败，买家id调用失败,请重新尝试修改，如若不行联系管理员")
                }
                $('#updateAddressOver').addClass('hidden');
                $('#buyerAddressP').removeClass('hidden')
                $('#buyerAddress').addClass('hidden')
            }
        })
    })


    /*
    个人资料设置
     */



    $('#personalSetting').click(function () {

        var buyerid=sessionStorage['loginUser'];
        var urltt="/buyer/queryByBuyerName?name="+buyerid;
        console.log(urltt)
        $.ajax({
            url:urltt ,
            type: "get",
            datatype:"json",
            success:function (data) {
                var obj=JSON.parse(data);
                console.log(obj);
                console.log(obj.t_buyer_name);
                $('#formalContentBeforeMain').addClass('hidden');
                $('#personalSettingContent').removeClass('hidden');
                $('#realName').val(obj.t_buyer_name);
                $('#phone').val(obj.t_buyer_phone);
                $('#email').val(obj.t_buyer_email);

                var sex=obj.t_buyer_sex;
                console.log("sex====="+sex)
                if(sex==true){
                    console.log("进入了"+sex)
                    $('#gender-f').attr("checked");
                    $('#gender-m').removeAttr("checked");
                }else{
                    console.log("进入了"+sex)
                    $('#gender-f').removeAttr("checked");
                    $('#gender-m').attr("checked")
                }
            },
            error:function (data) {
                console.log("gg-personalSetting")
            }
        })
    })



    // $('#personalInfo').click(function () {
    //     alert(sessionStorage['id'])
    //     var urltest="/buyer/personal/getBuyerByBuyerId?id="+buyerid;
    //     console.log("url========"+urltest);
    //     $.ajax({
    //         url: urltest,
    //         type: "get",
    //         datatype:"json",
    //         success:function (data) {
    //            var obj= JSON.parse(data)
    //             console.log(obj)
    //             console.log(obj.t_buyer_address)
    //         },
    //         error:function (data) {
    //             console.log("gg")
    //         }
    //     })
    // })



});


// 用户注册

function register(){
    var pa=$('#password1').val();
    var pa2=$('#password2').val();
   if(pa!=pa2){
       alert("两次密码不一样")
   }else{
       $.ajax({
           url:"/register",
           data:$("#form1").serialize(),
           type:"post",
           success:function (data) {
               if(data==true){
                   alert("注册成功");
                   $('#myModal1').modal('hide');
                   $('#myModal').modal('show');
               }else{
                   alert("注册失败")
               }
           },
           error:function (data) {
               alert("ajax提交失败")
           }
       })
   }



}

//用户注册界面返回
function showLoginPanel() {
    $('#myModal').modal('show');
}

/*
全选-购物车
 */
$('#checkall').click(function () {
    var flag=$(this).prop("checked");
    if(flag){
        $('.ckitem').prop("checked",true);
    }else{
        $('.ckitem').prop("checked",false);
    }
})

/*
单选
 */
$(".ckitem").click(function () {
    // var cl=$('.ckitem').length;
    var totalPrice=0;
    var length=$('.ckitem:checked').length;
    $('#selectCount').text(length)

    // var t=$('#cartBody tr');

    for(var i=0;i<length;i++){
        var tr = $($(".cart-body tr")[i]);
        var check=  tr.children(":eq(0)").children(".ckitem").prop("checked");
        var countPrice= tr.children(":eq(5)").children("span").html();
        console.log("countPrice==>"+countPrice);
        countPrice=Number(countPrice)
        // totalPrice+=countPrice;
        if(check){
            totalPrice+=countPrice;
        }
    }
    $('#selectTotal').text(totalPrice);

})