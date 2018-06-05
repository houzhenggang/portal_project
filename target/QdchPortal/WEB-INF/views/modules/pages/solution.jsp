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
<body id="solution_bigData">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h2>金融风险大数据解决方案</h2>
        <p>为金融行业客户提供包括智能营销、风险控制、辅助产品研发、辅助投资决策等一系列解决方案，不仅帮助金融客户更好地了解用户、筛选用户、获取用户，更能辅助客户进行新产品研发和投资决策。</p>
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
                    <h3 class="text-center">数据集成</h3>
                    <p>将散落在各个数据孤岛的数据整合到统一数据仓库平台中，通过全量和增量采集相结合的手段完成采集工作。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-02.png" alt="">
                    <h3>ETS调度监控</h3>
                    <p>让数据有序的按照数据模型设计的逻辑一步一步被加工出来，保障数据上下游依赖的正确性，在发现问题时能够提醒开发人员及时处理。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-03.png" alt="">
                    <h3>风险预警分析</h3>
                    <p>基于交易市场内部用户交易数据以及网络舆情数据，对交易市场的各类参与主体进360度的风险监控。</p>
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
                <p>基于金融用户的交易数据和行为数据，依托深厚的技术底蕴和领先的人工智能技术，提供基于金融风险、金融画像的辅助解决方案，帮助政府监控市场风险。</p>
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
                    <h3 class="text-center">市场风险管理</h3>
                    <p>根据市场交易数据和网络舆情平台监测数据，及时发现市场风险以及用户风险</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">用户画像</h3>
                    <p>存量用户进行画像分析，帮助金融机构了解用户短期意图和长期兴趣，有效识别高价值用户</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">投资决策</h3>
                    <p>根据平台画像和分析数据，识别金融投资机会，及时规避金融风险</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">辅助产品研发</h3>
                    <p>帮助金融机构根据用户群特点设计更有针对性的金融产品。</p>
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
                    <h3 class="text-center">独特的金融风险监控指标</h3>
                    <p>针对不同的场外金融业态，设计不同的金融风险指标。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">丰富独特的数据</h3>
                    <p>基于交易市场的交易数据和互联网的工商舆情数据。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">业界领先的技术能力</h3>
                    <p>深厚的技术底蕴和领先的人工智能技术，帮助客户准确了解、识别、筛选、获取用户。</p>
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
