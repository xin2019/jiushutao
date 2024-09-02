
$(function () {




    //界面控制
    $(window).scroll(function () {
        $('.spy').each(function () {
            pt = $(this).offset().top;
            st = $(window).scrollTop();
            if (st >= (pt - 200)) {
                idx = $(this).attr('idx');
                $('#' + idx).addClass('active');
                $('.spyObject').not($('#' + idx)).removeClass('active');
            }
        });
    });
    $('#recommend').click(function () {
        $('html,body').animate({scrollTop: $("#recommend1").offset().top}, 1000);//定位到该位置
    });
    $('#moreBooks').click(function () {
        $('html,body').animate({scrollTop: $("#moreBooks1").offset().top}, 1000);//定位到该位置
    });
    $('#topSpy').click(function () {
        $('html,body').animate({scrollTop: 0}, 1000);//定位到该位置
    });

    $('#studyChamber').click(function () {
        $('html,body').animate({scrollTop: $("#studyChamber1").offset().top}, 1000);//定位到该位置
    });


    $("#myCarousel").carousel('cycle');

    $(".prev-slide").click(function () {
        $("#myCarousel").carousel('prev');
    });

    $(".next-slide").click(function () {
        $("#myCarousel").carousel('next');
    });

    $(".fisrt").hover(function () {
        $("#myCarousel").carousel(0);
        $(this).addClass('active');
    }, function () {
        $(this).removeClass('active');
    });
    $(".second").hover(function () {
        $("#myCarousel").carousel(1);
        $(this).addClass('active');
    }, function () {
        $(this).removeClass('active');
    });
    $(".third").hover(function () {
        $("#myCarousel").carousel(2);
        $(this).addClass('active');
    }), function () {
        $(this).removeClass('active');
    };
});