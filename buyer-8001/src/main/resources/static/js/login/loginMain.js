
function qqqt(){
    alert("请在卖家后台申请")
}
//除了主页面和书籍详情页，其他页面若用户未登录不能访问,废弃使用
function beforeOnloadExceptIndexAndDetail() {
    var userID=null;
    var bookID=null;
    var userPhone=null;
    var cookie=document.cookie;
    var cookieArray=cookie.split(";");
    for(var i=0;i<cookieArray.length;i++){
        if(cookieArray[i].search("userID")!=-1){
            userID=cookieArray[i].split("=")[1];
        }
        else if(cookieArray[i].search("bookID")!=-1){
            bookID=cookieArray[i].split("=")[1];
        }else if(cookieArray[i].search("userPhone")!=-1){
            userPhone=cookieArray[i].split("=")[1];
        }
    }

    // alert("userPhone=="+userPhone);
    if(userPhone!=null&&userPhone!=""&&userPhone!=undefined) {

        $("#loginSpan").removeAttr("href");
        $("#loginSpan").removeAttr("data-toggle");
        $("#myModal").modal('hide');
        $("#githubPanel").addClass("hidden");

        $("#loginSpan").html(userPhone + " 欢迎你!");

        $("#loginSpan").addClass("dropdown-toggle");
        $("#loginSpan").attr("data-toggle","dropdown");
        $("#loginSpan").attr("data-target","#encompassLoginSpan")
        $("#encompassLoginSpan").addClass("dropdown");
    }else{
        window.location.href="/";
    }
}
function beforeOnload(){
    var userID=null;
    var bookID=null;
    var userPhone=null;
    var cookie=document.cookie;
    var cookieArray=cookie.split(";");
    for(var i=0;i<cookieArray.length;i++){
        if(cookieArray[i].search("userID")!=-1){
            userID=cookieArray[i].split("=")[1];
        }
        else if(cookieArray[i].search("bookID")!=-1){
            bookID=cookieArray[i].split("=")[1];
        }else if(cookieArray[i].search("userPhone")!=-1){
            userPhone=cookieArray[i].split("=")[1];
        }
    }
    if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){

        $("#githubPanel").addClass("hidden");

        // alert("onload::userphone=>"+userPhone);

        $("#loginSpan").removeAttr("href");
        $("#loginSpan").removeAttr("data-toggle");
        $("#myModal").modal('hide');

        $("#loginSpan").html(userPhone + " 欢迎你!");

        $("#loginSpan").addClass("dropdown-toggle");
        $("#loginSpan").attr("data-toggle","dropdown");
        $("#loginSpan").attr("data-target","#encompassLoginSpan")
        $("#encompassLoginSpan").addClass("dropdown");
    }
}

//获取用户登录状态
window.onload=function (ev) {

    var userID=null;
    var bookID=null;
    var userPhone=null;
    var cookie=document.cookie;
    var cookieArray=cookie.split(";");
    for(var i=0;i<cookieArray.length;i++){
        if(cookieArray[i].search("userID")!=-1){
            userID=cookieArray[i].split("=")[1];
        }
        else if(cookieArray[i].search("bookID")!=-1){
            bookID=cookieArray[i].split("=")[1];
        }else if(cookieArray[i].search("userPhone")!=-1){
            userPhone=cookieArray[i].split("=")[1];
        }
    }
    if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){

      //  alert("onload::userphone=>"+userPhone);
        $("#githubPanel").addClass("hidden");

        $("#loginSpan").removeAttr("href");
        $("#loginSpan").removeAttr("data-toggle");
        $("#myModal").modal('hide');

        $("#loginSpan").html(userPhone + " 欢迎你!");

        $("#loginSpan").addClass("dropdown-toggle");
        $("#loginSpan").attr("data-toggle","dropdown");
        $("#loginSpan").attr("data-target","#encompassLoginSpan")
        $("#encompassLoginSpan").addClass("dropdown");
    }
    //之前版本
    // console.log("退出了吗");
    // console.log("loginuser==>"+sessionStorage['loginUser']);
    // var user=sessionStorage['loginUser'];
    // if(user!=null||user!=""||user!=undefined){
    //     alert("还活着:"+user);
    //     $.ajax({
    //         url:"/buyer/getBuyerLogin?phone="+user,
    //         type:"get",
    //         datatype: "json",
    //         success:function (data) {
    //             var obj=JSON.parse(data);
    //             console.log(obj);
    //             alert(obj.status);
    //             if(obj.status=="1"){
    //                 $("#loginSpan").removeAttr("href");
    //                 $("#loginSpan").removeAttr("data-toggle");
    //                 $("#myModal").modal('hide');
    //
    //                 $("#loginSpan").html(user + " 欢迎你!");
    //
    //                 $("#loginSpan").addClass("dropdown-toggle");
    //                 $("#loginSpan").attr("data-toggle","dropdown");
    //                 $("#loginSpan").attr("data-target","#encompassLoginSpan")
    //                 $("#encompassLoginSpan").addClass("dropdown");
    //             }
    //
    //
    //         }
    //     });
    // }else{
    //
    //     //交互后台判断登陆状态
    //     $.post("/buyer/getUse" +
    //         "" +
    //         "" +
    //         "rname", function (obj) {
    //         if(obj.loginUser!=null&&obj.loginUser!=""){
    //             sessionStorage['loginUser']=obj.loginUser;
    //             var loginUser=obj.loginUser;
    //             // alert("post--"+obj.loginUser);
    //
    //             console.log("交互后台判断登陆状态/buyer/getUsername==>"+loginUser);
    //             $.ajax({
    //                 url:"/buyer/getBuyerLogin?phone="+obj.loginUser,
    //                 type:"get",
    //                 datatype: "json",
    //                 success:function (data) {
    //                     var obj=JSON.parse(data);
    //                     console.log(obj);
    //                     alert(obj.status);
    //                     if(obj.status=="1"){
    //                         $("#loginSpan").removeAttr("href");
    //                         $("#loginSpan").removeAttr("data-toggle");
    //                         $("#myModal").modal('hide');
    //
    //                         $("#loginSpan").html(loginUser + " 欢迎你!");
    //
    //                         $("#loginSpan").addClass("dropdown-toggle");
    //                         $("#loginSpan").attr("data-toggle","dropdown");
    //                         $("#loginSpan").attr("data-target","#encompassLoginSpan")
    //                         $("#encompassLoginSpan").addClass("dropdown");
    //                     }
    //
    //
    //                 }
    //             });
    //
    //
    //
    //
    //
    //
    //         }else {
    //             sessionStorage.clear();
    //             alert(sessionStorage['loginUser']+"logout failed")
    //         }
    //
    //     }, "json");
    // }

}





/*
   按书籍分类
    */
function categorySort(category){
    console.log(category)
    $.ajax({
        url:"/buyer/product/getCategoryBooks?category="+category,
        type:"get",
        success:function (data) {
            console.log("书籍分类")
            console.log(data);
            // $('#formalContent').addClass('hidden');
            // $('#categoryContent').removeClass('hidden');
            if(data.length==0){
                $('#carouselContext').html("<p>暂未上架该类书籍</p>")
            }else{
                var divContent;
                for(var i=0;i<data.length;i++){
                    divContent+="<div class='col-md-4'>";
                    divContent+=" <a href='/buyer/product/detail?id="+data[i].t_book_id+"'>"+
                        "<img class='img-rounded img-responsive' style='width:360px;height:229px;' src='"+data[i].t_book_photo+"/1.jpg'>"+"<br>"+
                        "<span class='text-center center-block'>"+data[i].t_book_name+"</span>"+"</a>"+"</div>";
                }
                // $('#categoryContent').html(divContent);
                $('#carouselContext').html(divContent);
            }
        },
        error:function (data) {
            alert("ajax---getCategoryBooks")
        }
    })
}


$(function () {

    /*
    获得主栏位书籍
     */
    $.ajax({
        url:"/buyer/product/getMainbooks",
        type:"get",
        success:function (data) {
            console.log(data)
            $('#mainImg1').attr("src",data[0].t_book_photo+"/1.jpg");
            $('#mainText1').html(data[0].t_book_name);
           if(data[0].t_book_id=="999"){
               console.log("1--")
               $('#mainImg1').click(function () {
                   alert("请登陆卖家后台界面进行申请");
               })
               $('#mainText1').click(function () {
                   alert("请登陆卖家后台界面进行申请");
               })
           }
            if(data[0].t_book_id!="999"){

                $('#mainHref1').attr("href","/buyer/product/detail?id="+data[0].t_book_id);
                $('#mainText1').attr("href","/buyer/product/detail?id="+data[0].t_book_id);
            }

            $('#mainImg2').attr("src",data[1].t_book_photo+"/1.jpg");
            $('#mainText2').html(data[1].t_book_name);
            if(data[1].t_book_id=="999"){
                console.log("2--")
                $('#mainImg2').click(function () {
                    alert("请登陆卖家后台界面进行申请");
                })
                $('#mainText2').click(function () {
                    alert("请登陆卖家后台界面进行申请");
                })
            }
            if(data[1].t_book_id!="999"){

                $('#mainHref2').attr("href","/buyer/product/detail?id="+data[1].t_book_id);
                $('#mainText2').attr("href","/buyer/product/detail?id="+data[1].t_book_id);
            }
            $('#mainImg3').attr("src",data[2].t_book_photo+"/1.jpg");
            $('#mainText3').html(data[2].t_book_name);
            if(data[2].t_book_id=="999"){
                console.log("3--")
                $('#mainImg3').click(function () {
                    alert("请登陆卖家后台界面进行申请");
                })
                $('#mainText3 ').click(function () {
                    alert("请登陆卖家后台界面进行申请");
                })
            }
            if(data[2].t_book_id!="999"){

                $('#mainHref3').attr("href","/buyer/product/detail?id="+data[2].t_book_id);
                $('#mainText3').attr("href","/buyer/product/detail?id="+data[2].t_book_id);
            }


        },
        error:function (data) {
            alert("ajaz--------获得主栏位")
        }
    })



    /*
    获得好书推荐栏位书籍
     */
    $.ajax({
        url:"/buyer/product/getRecomendBooks",
        type:"get",
        success:function (data) {
            console.log("haoshu")
            console.log(data)
            var divContent="";

            if(data[0].t_book_id=="999"){
                divContent+="<div class='col-md-4' onclick='qqqt()'>"+
                "<img class='img-rounded img-responsive' src='"+data[0].t_book_photo+"/1.jpg' >"+"<br>"+
                    "<span class='text-center center-block' >栏位出租</span>"+
                "</div>";
            }else{

                for(var i=0;i<data.length;i++){
                    divContent+="<div class='col-md-4'>";
                    divContent+=" <a href='/buyer/product/detail?id="+data[i].t_book_id+"'>"+
                        "<img class='img-rounded img-responsive' style='width:360px;height:229px;' src='"+data[i].t_book_photo+"/1.jpg'>"+"<br>"+
                        "<span class='text-center center-block'>"+data[i].t_book_name+"</span>"+"</a>"+"</div>";
                }
            }
            $('#recommendContent').html(divContent);
        },
        error:function (data) {
            alert("ajax---9001端口没开--/buyer/product/getRecomendBooks")
        }
    })

    /*
    获得金牌书屋栏位
     */
    $.ajax({
        url:"/buyer/product/getGoldShops",
        type:"get",
        success:function (data) {
            console.log("----------gold--")
            console.log(data);
            var divContent="";
            if(data[0].t_shop_id=="999"){
                divContent+="<div class='col-md-6' onclick='qqqt()'>"+
                    " <img class='img-rounded' src='"+data[0].t_shop_photo+"/1.jpg' style='width:553px;height:243px;'>"+
                    "<br>"+
                    "<span class='text-center center-block'>栏位招租</span>"+
                    "</div>";
            }else{
                for(var i=0;i<data.length;i++){
                    divContent+="<div class='col-md-6'>";
                    divContent+=" <a href='/buyer/product/shopDetail?shopid="+data[i].t_shop_id+"'>"+
                        "<img class='img-rounded img-responsive' style='width:553px;height:243px;' src='"+data[i].t_shop_photo+"/1.jpg'>"+"<br>"+
                        "<span class='text-center center-block'>"+data[i].t_shop_name+"</span>"+"</a>"+"</div>";
                }
            }
            $('#goldContent').html(divContent);
        },
        error:function (data) {
            alert("ajax-getGoldShops-")
        }
    })


    /*
    更多好书
     */
    $.ajax({
        url:"/buyer/product/getMoreGoodBooks",
        type:"get",
        success:function (data) {
            console.log("更多好书-----begin");
            console.log(data);
            var divContent="";

            if(data[0].t_book_id=="999"){
                divContent+="<div class='col-md-3' onclick='qqqt()'>"+
                    "<img class='img-rounded img-responsive' style='width:261px;height:248px;' src='"+data[0].t_book_photo+"/1.jpg' >"+"<br>"+
                    "<span class='text-center center-block' >栏位出租</span>"+
                    "</div>";
            }else{

                for(var i=0;i<data.length;i++){
                    divContent+="<div class='col-md-3'>";
                    divContent+=" <a href='/buyer/product/detail?id="+data[i].t_book_id+"'>"+
                        "<img class='img-rounded img-responsive' style='width:261px;height:248px;' src='"+data[i].t_book_photo+"/1.jpg'>"+"<br>"+
                        "<span class='text-center center-block'>"+data[i].t_book_name+"</span>"+"</a>"+"</div>";
                }
            }
            $('#showMoreBooksGoodContent').html(divContent);
        },
        error:function (data) {
            alert("ajax----/buyer/product/getMoreGoodBooks")
        }
    })

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
                if(obj.isExistBuyer==true){
                  // var tt=  sessionStorage["loginUser"];  null
                  //   console.log(tt);
                  //   alert("loginBefore:"+tt);

                   //  var d=new Date();
                   // d.setDate(d.getDay()+60*1000*24*60);
                   //  document.cookie="userPhone="+$("input[name='username']").val()+";expires="+d.toGMTString+";path=/";
                    $('#status').html("1");
                    sessionStorage['id']=obj.id;
                    console.log("")
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
    // $('#searchBook').click(function () {
    //     var searchName=$('#searchName').val();
    //     $.ajax({
    //         url: "/buyer/product/searchBooks?likename="+searchName,
    //         type: "get",
    //         jsontype: "json",
    //         success: function (data) {
    //             console.log(data)
    //             var obj=JSON.parse(data)
    //             var msg=obj.msg;
    //             $('#formalContent').addClass('hidden');
    //             $('#searchNull').addClass('hidden');
    //             $('#searchContent').addClass('hidden');
    //             if(msg!=null&&msg!=""){
    //
    //
    //                 $('#searchNull').removeClass('hidden');
    //                 $('#tip').html(msg)
    //             }
    //             else{
    //                var books= obj.likebooks;
    //                 var content="";
    //               for(var index in books){
    //                   var hrefurl="/buyer/product/detail?id="+books[index].t_book_id;
    //                   console.log(hrefurl)
    //                   content+=
    //                       "<div class='col-md-4' style='text-align: center'>"+
    //                       "<a href='"+hrefurl+"'"+
    //                      ">"+
    //                       "<img class='img-rounded img-responsive' style='width: 358px;height: 248px' src='"+
    //                       books[index].t_book_photo+"/1.jpg'>"+
    //                       "<span style='font-weight: bolder;text-align: center'>"+books[index].t_book_name+"</span>"+
    //                       "</a>"+
    //                       "</div>"
    //                   ;
    //                 $('#searchbody').html(content)
    //               }
    //                 $('#searchContent').removeClass('hidden')
    //             }
    //         }
    //     })
    //
    // })



    function showOrders(){

        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){
            console.log("/buyer/allOrder2?id="+userID)
            window.location.href="/buyer/allOrder2?id="+userID;
        }else{
            $("#myModal").modal('show');
        }
    }

    //个人中心 模块
    $("#navIndividual").click(function () {
      //  alert("进入个人中心模块");

       showOrders();
    });

    //登陆后  -》我的订单模块
    $('#myOrderCommon').click(function () {
        showOrders();


    });



    //退出登陆 模块
    $('#logout').click(function () {

        alert("20240902")

        // var userID=null;
        // var bookID=null;
        // var userPhone=null;
        // var cookie=document.cookie;
        // var cookieArray=cookie.split(";");
        // for(var i=0;i<cookieArray.length;i++){
        //     if(cookieArray[i].search("userID")!=-1){
        //         userID=cookieArray[i].split("=")[1];
        //     }
        //     else if(cookieArray[i].search("bookID")!=-1){
        //         bookID=cookieArray[i].split("=")[1];
        //     }else if(cookieArray[i].search("userPhone")!=-1){
        //         userPhone=cookieArray[i].split("=")[1];
        //     }
        // }
        // $.ajax({
        //         url: "/buyer/loginout?phone="+userPhone,
        //         type: "GET",
        //
        //         datatype: "json",
        //         success: function (data) {
        //            sessionStorage.clear();
        //            // var date=new Date();
        //            // date.setMonth(date.getMonth()-1);
        //            // document.cookie="userPhone=; expires="+date.toGMTString+";path=/";
        //            //  console.log(data)
        //             window.location.href=data;
        //         },
        //         error: function (data) {
        //             alert("退出失败请联系：" +
        //                 "qq：1720878303");
        //         }
        //
        //     });
    });



    //购物车 模块
    $("#navCart").click(function () {
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){
            var url="/cart?phone="+userPhone;
        window.location.href=url;
        }else{
            // alert("请先登录")
            $("#myModal").modal('show');
        }

    });

    $("#navCart2").click(function () {

        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){
            var url="/cart?phone="+userPhone;
            window.location.href=url;
        }else{ //alert("请先登录")
            $("#myModal").modal('show');
        }
    });

    //购物袋 侧边栏
    $("#siderCart").click(function () {
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){
            var url="/cart?phone="+userPhone;
            window.location.href=url;
        }else{
            $("#myModal").modal('show');
        }
    });

    //购物袋 搜索框旁边 模块
    $("#navCart3").click(function () {
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }

        if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){
            var url="/cart?phone="+userPhone;
            window.location.href=url;
        }else{ //alert("请先登录")
            $("#myModal").modal('show');
        }
    });

    /*
    个人设置旁边的购物袋
     */
    $('#navCart4').click(function () {
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        if(userPhone!=null&&userPhone!=""){
            var url="/cart?phone="+userPhone;
            window.location.href=url;
        }else{ //alert("请先登录")
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
        console.log("name.val()",$("#realName").val());
        console.log("name.text()",$("#realName").text());
        console.log("phone",$('#phone').val());
        console.log("email:",$("#email").val());
        console.log("sex:",$("input[name='sex']").val());
        $('#changePassContent').addClass('hidden');
        $('#changeAddressContent').addClass('hidden')
        $('#uploadImageContent').addClass('hidden')
        $('#personalInfoContent').removeClass('hidden')
       $.ajax({
           url:"personal/updateBuyer",
           type:"get",
           data:{
               "name":$("#realName").val(),
               "phone":$('#phone').val(),
               "email":$("#email").val(),
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
        $('#uploadImageContent').addClass('hidden');
        $('#personalInfoContent').addClass('hidden');

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


        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }

        var oldPass=$('#oldPwd').val();
        var newPass=$('#newPwd').val();
        var rePass=$('#rePwd').val();
        if(newPass!=rePass){
            alert("两次密码不一样");
            $('#rePwd').focus();
        }else{
            $.ajax({
                url:"/buyer/savePass",
                data:{
                    "oldPass":oldPass,
                    "phone":userPhone,
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
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        // var phone=sessionStorage['loginUser'];
        // var buyerid=sessionStorage['id'];
        // alert("test--changeAddress=="+buyerid);
        $.ajax({
            url:"/buyer/personal/getAllAddress?buyerid="+userID,
            // url:"/buyer/personal/getAddress",
            // data:{
            //     "phone":phone
            // },
            type:"post",
            success:function (data) {
                console.log("收货管理111111111111",data)
                if(data!=null)
                $.ajax({
                    // data[0].t_buyer_id
                    // url:"/buyer/personal/getAllAddress?buyerid="+buyerid,
                    url:"/buyer/personal/getAllAddress?buyerid="+userID,
                    type:"get",
                    success:function (data) {
                        console.log("收货管理：：")
                        console.log(data);

                        var contentDic="";

                        for(var i=0;i<data.length;i++){
                            contentDic+="<tr><td>"+
                                data[i].t_address.t_address_name+"</td><td>"+
                                data[i].t_address.t_buyer_address+"</td>"+"<td>"+
                                data[i].t_address.t_address_phone+"</td>"+"<td><button class='btn btn-xs add-del btn-info' onclick='delteaddress("+data[i].t_address.t_address_id+")'>删除</button></td></tr>";
                        }
                        console.log(contentDic)
                        $('#shouhuoContent22').html(contentDic);
                    },
                    error:function (data) {
                        alert("ajax--渲染失败")

                    }
                })
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
        $('#personalInfoContent').addClass('hidden');
        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        // var phone=sessionStorage['loginUser'];
        // var buyerid=sessionStorage['id'];
        // alert("test--changeAddress=="+buyerid);
        $.ajax({
            url:"/buyer/personal/getAllAddress?buyerid="+userID,
            // url:"/buyer/personal/getAddress",
            // data:{
            //     "phone":phone
            // },
            type:"post",
            success:function (data) {
                console.log("收货管理111111111111",data)
                if(data!=null)
                    $.ajax({
                        // data[0].t_buyer_id
                        // url:"/buyer/personal/getAllAddress?buyerid="+buyerid,
                        url:"/buyer/personal/getAllAddress?buyerid="+userID,
                        type:"get",
                        success:function (data) {
                            console.log("收货管理：：")
                            console.log(data);

                            var contentDic="";

                            for(var i=0;i<data.length;i++){
                                contentDic+="<tr><td>"+
                                    data[i].t_address.t_address_name+"</td><td>"+
                                    data[i].t_address.t_buyer_address+"</td>"+"<td>"+
                                    data[i].t_address.t_address_phone+"</td>"+"<td><button class='btn btn-xs add-del btn-info' onclick='delteaddress("+data[i].t_address.t_address_id+")'>删除</button></td></tr>";
                            }
                            console.log(contentDic)
                            $('#shouhuoContent22').html(contentDic);
                        },
                        error:function (data) {
                            alert("ajax--渲染失败")

                        }
                    })
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

        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        $.ajax({
            url:"/buyer/personal/getBuyerByUserID?userID="+userID,
            type: "get",
            datatype:"json",
            success:function (obj) {
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

//点击个人资料

    $('#personalInfo').click(function () {

        var userID=null;
        var bookID=null;
        var userPhone=null;
        var cookie=document.cookie;
        var cookieArray=cookie.split(";");
        for(var i=0;i<cookieArray.length;i++){
            if(cookieArray[i].search("userID")!=-1){
                userID=cookieArray[i].split("=")[1];
            }
            else if(cookieArray[i].search("bookID")!=-1){
                bookID=cookieArray[i].split("=")[1];
            }else if(cookieArray[i].search("userPhone")!=-1){
                userPhone=cookieArray[i].split("=")[1];
            }
        }
        $.ajax({
            url:"/buyer/personal/getBuyerByUserID?userID="+userID,
            type: "get",
            datatype:"json",
            success:function (obj) {
                $('#formalContentBeforeMain').addClass('hidden');
                $('#personalSettingContent').removeClass('hidden');
                $('#personalInfoContent').removeClass('hidden');
                $('#changePassContent').addClass('hidden');
                $('#uploadImageContent').addClass('hidden');
                $('#changeAddressContent').addClass('hidden');
                $('#realName').val(obj.t_buyer_name);
                $('#phone').val(obj.t_buyer_phone);
                $('#email').val(obj.t_buyer_email);

                var sex=obj.t_buyer_sex;
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