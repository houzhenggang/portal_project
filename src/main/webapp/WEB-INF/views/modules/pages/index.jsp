<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctxStatics}/images/com/i_logo.png">
    <title>青岛海联软件科技有限公司-大数据、人工智能、可视化、计算机视觉</title>
    <link rel="stylesheet" href="${ctxStatics}/asserts/css/bootstrap-3.3.7.min.css">
    <link rel="stylesheet" href="${ctxStatics}/css/style.css">
    <link rel="stylesheet" href="${ctxStatics}/css/index.css">
    <style>

    </style>
</head>
<body id="index">
<nav id="mainNav" class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">导航折叠按钮</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">
                <img class="img-responsive" src="${ctxStatics}/images/com/logo_old.png" alt="">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${index}">首页 <span class="sr-only">(current)</span></a></li>
                <li class="dropdown ">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">解决方案</a>
                    <ul class="dropdown-menu">
                        <li><a href="#">企业大数据平台</a></li>
                        <li><a href="#">数据仓库建设</a></li>
                        <li><a href="#">海量数据挖掘</a></li>
                        <li><a href="#">可视化数据探索</a></li>
                        <li><a href="#">人工智能研发</a></li>
                    </ul>
                    <ul class="dropdown-menu">
                        <li>
                            <ul>
                                <li>
                                    <a href="solution">
                                        <h5>大数据平台</h5>
                                        <p>金融大数据解决方案</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li>
                                    <a href="solution1">
                                        <h5>EDW企业数据仓库</h5>
                                        <p>数据仓库解决方案</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li>
                                    <a href="solution2">
                                        <h5>舆情平台</h5>
                                        <p>舆情分析解决方案</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li>
                                    <a href="solution3">
                                        <h5>数据可视化</h5>
                                        <p>数据可视化解决方案</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li>
                                    <a href="solution4">
                                        <h5>人脸识别</h5>
                                        <p>人脸闸机解决方案</p>
                                    </a>
                                </li>
                                <!-- <li>
                                    <a href="pages/solution5.html">
                                        <h5>计算机视觉</h5>
                                        <p>无人机盘库解决方案</p>
                                    </a>
                                </li> -->
                                <!-- <li>
                                    <a href="pages/solution6.html">
                                        <h5>计算机视觉</h5>
                                        <p>计算机视觉</p>
                                    </a>
                                </li> -->
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="news">公司新闻</a></li>
                <li><a href="about">关于我们</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <!-- <li class="user"><a href="#"><span class="glyphicon glyphicon-envelope"></span>
                    </a></li> -->
            </ul>
        </div>
    </div>
</nav>
<section class="banner">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>

        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="${ctxStatics}/images/index/banner01.png" alt="...">
                <div class="carousel-caption">
                    <h1 class="mainTitle">智能分析 · 数据洞察 · 预见未来<strong></strong></h1>
                    <h1 class="subTitle"><strong>海联科技</strong> | 商业智能</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctxStatics}/images/index/banner02.png" alt="...">
                <div class="carousel-caption">
                    <h1 class="mainTitle">海量数据 · 实时处理 · 挖掘探索<strong></strong></h1>
                    <h1 class="subTitle"><strong>海联科技</strong> | 大数据</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctxStatics}/images/index/banner03.png" alt="...">
                <div class="carousel-caption">
                    <h1 class="mainTitle">图形可视化 · 交互图表 · 数据大屏<strong></strong></h1>
                    <h1 class="subTitle"><strong>海联科技</strong> | 可视化</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctxStatics}/images/index/banner04.png" alt="...">
                <div class="carousel-caption">
                    <h1 class="mainTitle">图像处理 · 人脸识别 · 智能AI<strong></strong></h1>
                    <h1 class="subTitle"><strong>海联科技</strong> | 人工智能</h1>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>
<section class="features">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a>
                    <h3>大数据平台</h3>
                    <p>基于开源大数据平台</p>
                </a>
            </div>
            <div class="col-md-3">
                <a>
                    <h3>企业数据仓库</h3>
                    <p>数据采集、数据处理、数据建模</p>
                </a>
            </div>
            <div class="col-md-3">
                <a>
                    <h3>数据挖掘</h3>
                    <p>挖掘算法、数据探索</p>
                </a>
            </div>
            <div class="col-md-3">
                <a>
                    <h3>人工智能</h3>
                    <p>图像处理、计算机视觉</p>
                </a>
            </div>
        </div>
    </div>
</section>
<section class="describe">
    <div class="container">
        <div class="row text-center">
            <h2>拥有丰富的大数据实施和软件开发能力</h2>
            <p>海量数据处理、数据挖掘，可视化分析、人工智能、NLP自然语言处理</p>
        </div>
        <div class="row">
            <div class="col-md-4">
                <h3>大数据实施</h3>
                <a>
                    <h4>基于开源大数据Cloudera平台<span class="glyphicon glyphicon-arrow-right"></span></h4>
                    <p>为企业的大数据集成、存储、数据挖掘提供全流程的解决方案，让企业能聚焦于挖掘自身大数据资产的商业价值</p>
                </a>
            </div>
            <div class="col-md-4">
                <h3>数据挖掘</h3>
                <a>
                    <h4>数据挖掘与可视化探索<span class="glyphicon glyphicon-arrow-right"></span></h4>
                    <p>数据挖掘算法、可视化报表，为用户提供简便、易用、高性能的数据分析与可视化。</p>
                </a>
            </div>
            <div class="col-md-4">
                <h3>数据建模</h3>
                <a>
                    <h4>数据建模<span class="glyphicon glyphicon-arrow-right"></span></h4>
                    <p>面向企业数据治理需求，提供完善的数据元信息管理、数据仓库模型构建。</p>
                </a>
            </div>

        </div>
    </div>
</section>
<section class="solution">
    <div class="container">
        <div class="row text-center">
            <h2>提供有针对性的业务场景解决方案</h2>
            <p>贴近企业业务需求、量身定制解决方案，实现准确决策</p>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="imgTextBox">
                    <img class="img-responsive" src="${ctxStatics}/images/index/solution01.png" alt="">
                    <div class="textBox">
                        <h3><a>商务智能解决方案</a></h3>
                        <ul>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>交通大数据解决方案</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>服务业大数据解决方案</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>金融风险大数据解决方案</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>供应商绩效评价解决方案</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="imgTextBox">
                    <img class="img-responsive" src="${ctxStatics}/images/index/solution02.png" alt="">
                    <div class="textBox">
                        <h3><a>人工智能AI</a></h3>
                        <ul>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>NLP自然语言处理</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>人脸闸机解决方案</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>人脸考勤解决方案</a>
                            </li>
                            <li>
                                <a><span class="glyphicon glyphicon-home"></span>无人机盘库解决方案</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="partner">
    <div class="container">
        <div class="row text-center">
            <h2>我们的客户</h2>
            <p>牵手海联科技，实现合作共赢，期待您的加入</p>
        </div>
        <div class="row logos">
            <div class="col-md-2 item"><div class="imgBox"></div></div>
            <div class="col-md-2 item"><div class="imgBox"></div></div>
            <div class="col-md-2 item"><div class="imgBox"></div></div>
            <div class="col-md-2 item"><div class="imgBox"></div></div>
            <div class="col-md-2 item"><div class="imgBox"></div></div>
            <div class="col-md-2 item"><div class="imgBox"></div></div>
        </div>
    </div>
</section>
<footer id="mainFooter">
    <div class="container">
        <div class="row">
            <div class="col-md-3 introduce">
                <h3>关于我们</h3>
                <p>
                    青岛海联软件科技有限公司是一家致力于提供大数据实施、移动互联网技术解决方案和IT服务的技术型企业,我们在大中型互联网系统集群建设与服务方面积累了丰富的经验，并在商务智能与数据处理等领域形成了独特的技术优势。
                    <!--<p>人工智能领域，目前已经与北京理工大学，天津大学等国内知名高校建立合作关系，共同实现在人工智能领域的创新应用，目前已经在图像识别，人脸识别，无人机、无人车、智能设备、导航、控制等领域取得了一定的成绩。-->
                    <!--<p>产业互联网时代，企业将从“服务提供者”转型为“客户运营商"， 与用户建立“强关系”，运用云计算、大数据、人工智能与物联网等创新技术，积淀研发实力，厚积薄发；聚焦用户体验，夯实运营基础，寻求商业创新，助力各行各业的客户向数字时代的客户运营商转型，为用户提供实时、精准、个性化的产品与服务。-->
            </div>
            <div class="col-md-2 col-md-offset-1">
                <h3>解决方案</h3>
                <ul>
                    <li><a>企业大数据平台</a></li>
                    <li><a>数据仓库建设</a></li>
                    <li><a>海量数据挖掘</a></li>
                    <li><a>数据可视化</a></li>
                    <li><a>人工智能</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3>联系方式</h3>
                <ul>
                    <!-- <li><a href="#">联系我们</a></li> -->
                    <li>青岛海联软件科技有限公司</li>
                    <li>QQ群：650596829</li>
                    <li>销售邮箱：sales@hlsofttech.com</li>
                    <li>
                        <li>联系电话：陈先生 15963206518</li>
                        <li>办公地址：青岛市市北区山东路万科中心15F</li>
                    </li>
                </ul>
            </div>
            <div class="col-md-3">
                <h3>微信扫码关注</h3>
                <ul>
                    <!--<li>扫码关注微信</li>-->
                    <li class="QRcode"><img class="img-responsive" src="${ctxStatics}/images/index/QRcode_gongzhonghao.png"></li>
                </ul>
            </div>
        </div>
        <p class="copyright text-center">Copyright©️2018 青岛海联软件科技有限公司 版权所有 鲁ICP备17043642号</p>

    </div>
</footer>

<script src="${ctxStatics}/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatics}/asserts/js/bootstrap-3.3.7.min.js"></script>
<script src="${ctxStatics}/js/main.js"></script>
<script>
    $(function () {

    })
</script>
</body>
</html>
