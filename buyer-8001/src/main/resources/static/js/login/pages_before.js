window.onload=function (ev) {


    var cookie=document.cookie;
    console.log(cookie);
    var cookieArray=cookie.split(";");
    var userPhone=cookieArray[0].split("=")[1];
    if(userPhone!=null&&userPhone!=""&&userPhone!=undefined){

        // alert("onload::userphone=>"+userPhone);

        $("#loginSpan").removeAttr("href");
        $("#loginSpan").removeAttr("data-toggle");
        $("#myModal").modal('hide');
$("#githubPanel").addClass("hidden");
        $("#loginSpan").html(userPhone + " 欢迎你!");

        $("#loginSpan").addClass("dropdown-toggle");
        $("#loginSpan").attr("data-toggle","dropdown");
        $("#loginSpan").attr("data-target","#encompassLoginSpan")
        $("#encompassLoginSpan").addClass("dropdown");
    }

    //废弃，前一个版本
    // $.post("/buyer/getUsername", function (obj) {
    //     if(obj.loginUser!=null&&obj.loginUser!=""){
    //         sessionStorage['loginUser']=obj.loginUser;
    //         var loginUser=obj.loginUser;
    //         alert("post--"+obj.loginUser);
    //
    //         $.ajax({
    //             url:"/buyer/getBuyerLogin?phone="+obj.loginUser,
    //             type:"get",
    //             datatype: "json",
    //             success:function (data) {
    //                 var obj=JSON.parse(data);
    //                 console.log(obj);
    //                 // alert(obj.status);
    //                 if(obj.status=="1"){
    //                     $("#loginSpan").removeAttr("href");
    //                     $("#loginSpan").removeAttr("data-toggle");
    //                     $("#myModal").modal('hide');
    //
    //                     $("#loginSpan").html(loginUser + " 欢迎你!");
    //
    //                     $("#loginSpan").addClass("dropdown-toggle");
    //                     $("#loginSpan").attr("data-toggle","dropdown");
    //                     $("#loginSpan").attr("data-target","#encompassLoginSpan")
    //                     $("#encompassLoginSpan").addClass("dropdown");
    //                 }
    //
    //
    //             }
    //         });
    //     }else {
    //         sessionStorage.clear();
    //         alert(sessionStorage['loginUser']+"logout failed")
    //     }
    //
    // }, "json");

   //原先的,废弃，前两个版本
   //  $.ajax({
   //      url: "/buyer/getUsername",
   //      type: "POST",
   //      data: {
   //      },
   //      async : false,
   //      datatype: "json",
   //      success: function (obj) {
   //
   //          if(obj.loginUser!=null&&obj.loginUser!=""){
   //              sessionStorage['loginUser']=obj.loginUser;
   //              // alert("post--"+obj.loginUser)
   //              $("#loginSpan").removeAttr("href");
   //              $("#loginSpan").removeAttr("data-toggle");
   //              $("#myModal").modal('hide');
   //
   //              $("#loginSpan").html(obj.loginUser + " 欢迎你!");
   //
   //              $("#loginSpan").addClass("dropdown-toggle");
   //              $("#loginSpan").attr("data-toggle","dropdown");
   //              $("#loginSpan").attr("data-target","#encompassLoginSpan")
   //              $("#encompassLoginSpan").addClass("dropdown");
   //
   //          }
   //      },
   //      error: function (data) {
   //          alert("ajax-error");
   //      }
   //
   //  });

    // $('#logout').click(function () {
    //     var cookie=document.cookie;
    //     console.log(cookie);
    //     var cookieArray=cookie.split(";");
    //     var userPhone=cookieArray[0].split("=")[1];
    //     // alert(userPhone);
    //
    //     $.ajax({
    //         url: "/buyer/loginout?phone="+userPhone,
    //         type: "GET",
    //
    //         datatype: "json",
    //         success: function (data) {
    //             sessionStorage.clear();
    //             var date=new Date();
    //             date.setDate(date.getTime()-1);
    //             document.cookie="userPhone=; expires="+date.toGMTString;
    //             console.log(data)
    //             window.location.href=data;
    //         },
    //         error: function (data) {
    //             alert("退出失败请联系：" +
    //                 "qq：1720878303");
    //         }
    //
    //     });
    // });
}