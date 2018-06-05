<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctxStatics}/img/com/i_logo.png">
    <title>青岛海联软件科技有限公司</title>
    <link rel="stylesheet" href="${ctxStatics}/asserts/css/bootstrap-3.3.7.min.css">
    <link rel="stylesheet" href="${ctxStatics}/css/style.css">
    <link rel="stylesheet" href="${ctxStatics}/css/newsContent.css">
</head>
<body id="newsContent newsContentFirst">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h1>公司新闻</h1>
    </div>
</section>
<section id="compNewsDetails">
    <div class="container">
        <div id="main">
            <article>
                <header class="text-center">
                    <h2 class="articleTitle">海联科技人脸识别系统V1.0研发成功</h2>
                    <div class="posted-on">
                        <span>posted on <i><time class="publishedTime">2017-03-21</time></i> </span>
                        &nbsp;
                        <span>by <i class="publishedPeople">jerry</i> </span>
                    </div>
                </header>
                <div class="articleContent">
                    <p>
                        我公司研发的人脸识别系统V1.0已经成功上线，人脸识别精准率实验室环境保持在95%以上，可以在人脸识别闸机，安防，公司考勤等领域实现应用。
                    </p>
                </div>
            </article>
        </div>
    </div>
</section>
<footer></footer>
<script src="${ctxStatics}/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatics}/asserts/js/bootstrap-3.3.7.min.js"></script>
<script src="${ctxStatics}/js/main.js"></script>
<script>
    $(function () {
        getContent();
    })

    function getContent() {
        $.ajax({
            type: "GET",
            url: "${portalPath}/newsContent",
            data: {'id':'${newsid}'},
            dataType: "json",
            success: function(data){
                console.log(data)
                $('#resText').empty();   //清空resText里面的所有内容
                var html = '';
                var item = data.data;
                html += ' <article>\n' +
                    '                <header class="text-center">\n' +
                    '                    <h2 class="articleTitle">'+item.title+'</h2>\n' +
                    '                    <div class="posted-on">\n' +
                    '                        <span>posted on <i><time class="publishedTime">'+item.updateDate+'</time></i> </span>\n' +
                    '                        &nbsp;\n' +
                    '                        <span>by <i class="publishedPeople">'+item.user.name+'</i> </span>\n' +
                    '                    </div>\n' +
                    '                </header>\n' +
                    '                <div class="articleContent">\n' +
                    '                    <p>'+item.contentHtml+'  </p>\n' +
                    '                </div>\n' +
                    '            </article>';
                $('#main').html(html);


            }
        });
    }
</script>
</body>
</html>

