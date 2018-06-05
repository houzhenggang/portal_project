<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctxStatics}/images/com/i_logo.png">
    <title>青岛海联软件科技有限公司</title>
    <link rel="stylesheet" href="${ctxStatics}/asserts/css/bootstrap-3.3.7.min.css">
    <link rel="stylesheet" href="${ctxStatics}/css/style.css">
    <link rel="stylesheet" href="${ctxStatics}/css/news.css">
</head>
<body id="news">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h1>公司新闻</h1>
    </div>
</section>
<section class="contentList">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <ul id="resText">

                </ul>
            </div>
        </div>

    </div>
</section>
<section class="text-center">
    <nav class="pageclass" aria-label="Page navigation">
        <ul class="pagination">
            <%--<li>--%>
                <%--<a href="#" aria-label="Previous">--%>
                    <%--<span aria-hidden="true">&laquo;</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li><a onclick="">1</a></li>--%>
            <%--<li><a href="#">2</a></li>--%>
            <%--<li><a href="#">3</a></li>--%>
            <%--<li><a href="#">4</a></li>--%>
            <%--<li><a href="#">5</a></li>--%>
            <%--<li>--%>
                <%--<a href="#" aria-label="Next">--%>
                    <%--<span aria-hidden="true">&raquo;</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        </ul>
    </nav>
</section>

<footer>
</footer>
<script src="${ctxStatics}/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatics}/asserts/js/bootstrap-3.3.7.min.js"></script>
<script src="${ctxStatics}/js/main.js"></script>
<script>

    $(function () {

        getList(1);
    })
    function getList(showpage) {
        $.ajax({
            type: "GET",
            url: "${portalPath}/newsList",
            data: {'pageNo':showpage},
            dataType: "json",
            success: function(data){
                console.log(data)
                $('#resText').empty();   //清空resText里面的所有内容
                var html = '';
                $.each(data.data.list, function(index, item){
                    html += '  <li class="item"><a href="${portalPath}/newsDetails?id='+item.id+'">\n' +
                        '                        <div class="media">\n' +
                        '                            <div class="media-left">\n' +
                        '                                <img class="media-object" src="'+item.image+'" alt="...">\n' +
                        '                            </div>\n' +
                        '                            <div class="media-body">\n' +
                        '                                <h3 class="media-heading">'+item.title+'</h3>\n' +
                        '                                <p>'+item.description+'</p>\n' +
                        '                                <div class="publishedDate"><time>'+item.updateDate+'</time></div>\n' +
                        '                            </div>\n' +
                        '                        </div></a>\n' +
                        '                    </li>';
                });
                $('#resText').html(html);
                html = "";
                for(var i = 1;i<=data.data.pageCount;i++){
                    html+='<li><a onclick="getList('+i+')">'+i+'</a></li>';
                }

                console.log(html)
                $('.pagination').empty().append(html);

            }
        });
    }
</script>
</body>
</html>

