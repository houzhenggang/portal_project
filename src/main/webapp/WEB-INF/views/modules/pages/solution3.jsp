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
    <link rel="stylesheet" href="${ctxStatics}/css/solution.css">
</head>
<body id="solution_dataView">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h2>可视化解决方案</h2>
        <p>更便捷的数据探索，更好看的商业见解，助你更畅快地探索大数据的商业价值。</p>
        <!-- <a class="button" href="#" target="_blank">联系我们</a> -->
    </div>
</section>
<nav class="subNav">
    <div class="attributes-nav">
        <div class="list attribute-tabs" >
            <ul>
                <li class="active"><a href="#solution-sec1" >方案构成</a></li>
                <li><a href="#solution-sec2" >方案概述</a></li>
                <li><a href="#solution-sec3" >应用场景</a></li>
                <li><a href="#solution-sec4" >方案优势</a></li>
            </ul>
        </div>
    </div>
</nav>
<section id="solution-sec1">
    <div class="container text-center">
        <h2>方案构成</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-01.png" alt="">
                    <h3 class="text-center">多类型数据源配置</h3>
                    <p>支持离线计算的数据建模；支持从数据仓库和数据存储中直接拉取数据；使用SQL进行离线数据运算，数据自动导入中间表。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-02.png" alt="">
                    <h3>数据探索</h3>
                    <p>允许用户直连自有数据源；支持Generic SQL、ElasticSearch、InfluxDB等多种数据库类型；使用交互式探查数据；例行ETS作业任务调度。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-03.png" alt="">
                    <h3>丰富的图表展现</h3>
                    <p>支持基础图表以及漏斗图、桑基图、盒装图、旋转表、地图等多种图表类型；灵活配置图表样式；时间窗口联动，统一的时间区域控件，自动帮您每天拉取最新的数据进行展示。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
        </div>
    </div>
</section>
<section id="solution-sec2">
    <div class="container">
        <h2 class="text-center">方案概述</h2>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <p>一站式的大数据商业智能可视化平台，它集成了多人协作、数据探查、交互式的可视化分析和灵活的作业调度等功能，帮助用户更快、更好的探索海量数据中隐藏的商业价值。</p>
            </div>
        </div>
    </div>
</section>
<section id="solution-sec3">
    <div class="container">
        <h2 class="text-center">应用场景</h2>
        <div class="row">
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">可视分析</h3>
                    <p>用户可以快速对数据进行建模分析，结合报表工具丰富的数据分析能力，让用户进一步挖掘数据的隐含信息。</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">报表平台</h3>
                    <p>支持多种数据源，并提供种类繁多的可视化图表，提供了从数据源到报表转化的能力，方便用户快速构建报表平台。</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">数据大屏</h3>
                    <p>使用拼接大屏展示展示全景数据，360度全方位、多角度、全景展现企业的各项指标，形成数据中心、会商中心</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">移动端</h3>
                    <p>移动数据驾驶舱，无论在家里还是出差在外，实时掌握企业经营状况，及时发现问题并做出决策调整。</p>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="solution-sec4">
    <div class="container">
        <h2 class="text-center">方案优势</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">丰富的图表</h3>
                    <p>支持折线图、表格、饼图、指标数字、漏斗图、桑基图等多种图表类型；灵活配置图表样式、颜色、字体、字号、标注等一应俱全。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">图表数据自动刷新</h3>
                    <p>支持自定义时间间隔，自动拉取最新数据。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">快捷报表分享</h3>
                    <p>带权限分享报表；无权限、脱敏快照分享。</p>
                </div>

            </div>
        </div>
    </div>
</section>
<footer></footer>
<script src="${ctxStatics}/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatics}/asserts/js/bootstrap-3.3.7.min.js"></script>
<script src="${ctxStatics}/js/main.js"></script>
<script>
    $(function () {

    })
</script>
</body>
</html>
