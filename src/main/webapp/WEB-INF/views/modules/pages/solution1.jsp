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
<body id="solution_dataWareHouse">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h2>数据仓库解决方案</h2>
        <p>提供一站式数据存储、处理、分析和可视化的大数据平台服务，无需经过繁琐的数据ETL/ELT过程，就可以通过一个简单易用的方式，进行数据访问、加工处理、探查分析和数据可视化。</p>
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
                    <h3 class="text-center">数据采集</h3>
                    <p>集成多种异构数据源，可将用户存储在各种环境的业务数据进行整合传输至数据仓库系统，包括关系型数据库、大数据存储、本地数据文件等，并对非结构化数据进行自动转换。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-02.png" alt="">
                    <h3>数据处理</h3>
                    <p>支持SQL及Spark DataFrame API编写ETL程序，支持Java、Python、Scala编写的第三方或者本地代码，支持ETL工作流例行调度，可在ETS资源调度平台中即时查询以及展示结果，对数据处理过程进行监控管理。</p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-03.png" alt="">
                    <h3>查询分析</h3>
                    <p>高度兼容SQL标准，提供库内分析、窗口函数等高级分析功能，只需执行SQL语句，即可对海量业务数据进行多维分析，毫秒级高性能响应，迅速获知查询结果。</p>
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
                <p>为您提供一站式数据存储、数据加工、数据管理、数据调度、数据分析可视化等完整的数据仓库解决方案，由离线处理、数据仓库、大数据可视化多部分组成。该方案将元数据集中到数据平台中，统一对数据进行加工处理和分析。在原始数据产出信息的整个链条中，该方案提供了数据字典、数据血缘等高级的数据治理功能，保证了数据仓库建设的高效性，并对实际业务产生价值。</p>
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
                    <h3 class="text-center">商业决策</h3>
                    <p>数据仓库服务提供高性能分布式数据分析服务，集成可视化平台，可帮助企业快速搭建智能BI系统，实现一站式数据分析并生成报表，辅助企业进行商业决策。</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">数据管理</h3>
                    <p>数据仓库服务支持多种异构数据源的导入和存储，帮助企业快速同步数据，并进行数据清洗和模型分析，将企业数据按业务主题分类管理。</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">实时分析</h3>
                    <p>数据仓库服务可以将海量数据实时传输和快速聚合，并进行实时数据清洗、处理和分析，迅速响应业务端各种实时查询需求，提供实时计算和分析支持。</p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">主数据管理</h3>
                    <p>提供企业关键业务数据（包括客户、供应商、产品和账目）的单一可信视图。借助主数据管理解决方案，您可以对主数据条目进行集中化管理。</p>
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
                    <h3 class="text-center">异构的数据处理框架</h3>
                    <p>数仓方案本身可以兼容下游多个物理集群存储介质，支持数据统一的视图，在逻辑层面进行元数据的计算和管理。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">稳定的调度平台</h3>
                    <p>稳定的调度系统对全链路的数据加工过程进行调度执行。多维度多手段的预警和链路分析工作，保证数据的稳定产出。</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">应用级解决方案</h3>
                    <p>集成个性化推荐、用户画像以及知识图谱解决方案。</p>
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
