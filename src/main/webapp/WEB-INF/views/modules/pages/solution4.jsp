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
<body id="solution_AI">
<nav class="navbar navbar-inverse"></nav>
<section class="banner">
    <div class="banner-content text-center">
        <h2>人脸识别闸机解决方案</h2>
        <p>软硬一体的人脸识别闸机解决方案，提升人员系统化管理的安全性与便捷性 </p>
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
                    <h3 class="text-center">集成人脸识别的闸机硬件</h3>
                    <p>软硬件一体化产品输出，将传统闸机加入人脸识别前端设备、云端识别接口、识别通信模块，让您零开发成本即可投入线下场景使用 </p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-02.png" alt="">
                    <h3>后台业务管理系统</h3>
                    <p>可视化人脸管理系统，提供易于操作的人脸识别、人脸库增删改查能力，在原有业务管理模块基础上，增强对用户更精准的管控与记录 </p>
                    <!-- <a href="">了解详情<span class="glyphicon glyphicon-arrow-right"></span></a> -->
                </div>
            </div>
            <div class="col-md-4">
                <div class="imgTxtBox">
                    <img src="${ctxStatics}/images/solution/sec1-03.png" alt="">
                    <h3>人脸识别SDK</h3>
                    <p>提供适配多种硬件环境的SDK，快速集成活体检测、人脸质量检测、人脸图像捕获等能力；实现高度灵活的开发定制 </p>
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
                <p>通过集成了人脸SDK的前端设备，在设备本地获取人脸图像，调用人脸查找SDK，基于查找结果
                    判断是否可以放行，并将返回结果通过信号指令传输给闸机或门禁，并完成设备联动 </p>
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
                    <h3 class="text-center">景区闸机</h3>
                    <p>将人脸识别功能集成到景区闸机中，游客可通过在线购票录入人脸，在景区内即可通过刷脸完成各种验票，提升游客体验的同时，也有助于景区精细化管理 </p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">小区刷脸通行</h3>
                    <p>业主可将人脸作为身份识别凭证，可在线上提前注册登记来访人员，如亲朋访友、家政服务人员等，保安仅需通过验证人脸即可快速完成登记放行等相关事宜 </p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">企业门禁</h3>
                    <p>在传统刷工卡门禁系统的基础上，增加人脸识别能力，员工可不用携带工卡上下班出入公司，便捷了员工的同时，进一步提升了人员管理的安全性和高效性 </p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="imgTxtBox">
                    <div class="bgImg"></div>
                    <h3 class="text-center">会员个性化服务</h3>
                    <p>基于人脸识别，服务人员可以第一时间获取会员的身份信息，并结合会员过往消费记录，提供个性化服务，提升顾客满意度，促进消费转化 </p>
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
                    <h3 class="text-center">大规模人脸检索</h3>
                    <p>支持百万量级人脸库检索，检索速度业内领先，可应对各种业务需求 </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">识别精度高</h3>
                    <p>人脸识别技术国际领先，识别准确率超过99%，识别效果国际领先 </p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="item">
                    <h3 class="text-center">易于集成</h3>
                    <p>提供SDK能力，适配多种软硬件环境，可根据业务需求灵活使用 </p>
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
