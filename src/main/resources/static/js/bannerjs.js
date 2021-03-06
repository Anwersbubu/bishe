    var piclist = $("#banner .pic ul li");
    var picnav = $("#banner .picnav ul li");
    var banner = $("#banner");
    var navbox = $("#banner .picnav");
    var i = 0;
    var time = null;
    banner.hover(function() {
            navbox.stop();

            clearInterval(time)
        },
        function() {
            navbox.stop();

            time = setInterval(function() {
                    i = (i + 1) % 5;
                    auto(i)
                },
                3000)
        });
    picnav.mouseover(function() {
        i = $(this).index();
        auto(i)
    });
    function auto(i) {
        picnav.addClass("first").siblings().removeClass("first");
        piclist.eq(i).fadeIn().siblings().fadeOut();
        picnav.eq(i).addClass("active").siblings().removeClass("active")
    }
    time = setInterval(function() {
            i = (i + 1) % 5;
            auto(i)
        },
        3000);
