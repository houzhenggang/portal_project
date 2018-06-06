/**
 * Created by win0 on 2017/10/18.
 */
$(function () {

    dropdownOpen(); //下拉菜单hover打开
    menu2ToMenu3(); //二三级菜单联动
    loadMainNav();//加载主导航
    loadFooter(); //加载页脚
    loadNewsContent();//加载新闻详情页面的样式
    solutionSubNavFixed(); //解决方案页面子导航的悬浮效果
    $('.carousel').carousel({
        // interval: 3000
    });

    //加载主导航
    function loadMainNav() {
        $("body:not(#index)>nav:not(.subNav)").load("/ #mainNav>*", function () {
            $(this).find("a").attr("href", function () {
                return "../" + $(this).attr("href");
            });
            // console.log($(this)[0])
            $(this).find("img").attr("src", function () {
                return "../" + $(this).attr("src");
            });
            menu2ToMenu3();//二三级菜单联动
            dropdownOpen();//下拉菜单hover打开
        });
    }

    //加载页脚
    function loadFooter() {
        $("body:not(#index)>footer").load("/ #mainFooter>*", function () {
            // console.log($(this)[0]);
            $(this).find("img").attr("src", function () {
                return "../" + $(this).attr("src");
            });
        });
    }

    //二三级菜单联动
    function menu2ToMenu3() {
        $("ul.navbar-nav > .dropdown > .dropdown-menu > li > a").append('<span class="icon"></span>');

        var $menu2_li = $("ul.navbar-nav > .dropdown > .dropdown-menu:first-of-type > li");
        $menu2_li.eq(0).children("a").addClass("active");//默认选中第一个
        $menu2_li.hover(function () {
            $menu2_li.find("a").removeClass("active");
            $(this).find("a").addClass("active");
            var index = $(this).index();
            $(this).parents(".dropdown-menu").next().children("li").eq(index).show().siblings().hide();
        });
    }

    //下拉菜单hover打开
    function dropdownOpen() {

        var $dropdownLi = $('li.dropdown');

        $dropdownLi.mouseover(function () {
            $(this).addClass('open');
        }).mouseout(function () {
            $(this).removeClass('open');
        });
    }

    //加载新闻详情页面的样式
    function loadNewsContent() {
        $("#newsContent:not(.newsContentFirst)").find("#main").load("/portal/newscontent #main>*", function () {
            // console.log($("#articleTitle").text())
            $(".articleTitle").text($("#articleTitle").text());
            $(".publishedTime").text($("#publishedTime").text());
            $(".publishedPeople").text($("#publishedPeople").text());
            $(".articleContent").html($("#articleContent").html());

        })
    }

    //解决方案页面子导航的悬浮效果
    function solutionSubNavFixed() {
        var $subNavLi = $("body[id^=solution]").find(".subNav .attribute-tabs>ul>li");
        $subNavLi.click(function () {
            $subNavLi.removeClass("active");
            $(this).addClass("active");
        });
        window.onscroll = function () {
            var top = $(document).scrollTop();
            if (top > 510) {
                $("body[id^=solution]").find(".subNav").addClass("fixTop")
            } else {
                $("body[id^=solution]").find(".subNav").removeClass("fixTop")
            }
        }
    }
});
