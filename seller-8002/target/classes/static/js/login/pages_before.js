window.onload=function (ev) {
    $.ajax({
        url: "/buyer/getUsername",
        type: "POST",
        data: {
        },
        async : false,
        datatype: "json",
        success: function (obj) {

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

            }
        },
        error: function (data) {
            alert("ajax-error");
        }

    });
}