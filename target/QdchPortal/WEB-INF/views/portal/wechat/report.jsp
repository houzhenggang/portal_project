<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zn-CN">
<head>
    <meta charset="UTF-8"  name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache" />
    <title>举报</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/bootstrap.min-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/animate.css">
	<link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/style.css">
	<link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/0.4.1/weui.css">
    <style>
        body {
            background-color: #eee;
        }

        .container {
            margin-bottom: .6rem;
            padding-top: 1px;
            padding-bottom: 1px;
            background-color: #fff;
            /*box-shadow: 0.1rem 0.1rem 0.1rem #0002;*/
        }

        .form-group {
            position: relative;
            margin-bottom: auto;
            padding-top: 1px;
        }
        .form-group.titleBox {
            margin-bottom: .1rem;
        }

        .form-group > label:not(.error) {
            position: absolute;
            bottom: 0;
            margin-top: .5rem;
            margin-bottom: .475rem;
            width: 5em;
            /*#开头的4位带透明度的颜色值在手机上不好用*/
            color: rgba(0,0,0,.375);
            transition: .4s ease-in-out all;
        }
        .form-group.titleBox > label:not(.error),
        .form-group.noteBox > label:not(.error) {
            top: .5rem;
            width: 100%;
            color: #000;
        }
        .noteBox>label:not(.error){
            margin-top: 0;
        }
        label.error{
            display: block;
            width: 100%;
            position: absolute;
            font-size: .75rem;
            text-align: right;
            color: #ff4d4f;
            top: 1.25rem;
        }
        .form-group.titleBox > label.error,
        .form-group.noteBox > label.error {
            top: 1rem;
        }
        #description-error{
            top: auto;
            bottom: 0;
        }
        .form-group > label[for=image] {
            position: relative;
            margin-top: 1rem;
            margin-bottom: 1.875rem;
            height: 4rem;

        }
        .iconBox{
            display: block;
            position: relative;
            width: 5rem;
            height: 5rem;
            padding: 1rem;
            font-size: .875rem;
           /*  background: url(${ctxStatic}/${portalPage}/wx/img/photo2.jpg) 48% 30% no-repeat; */
            background-size: 60% 50%;
           /*  border: .1rem dashed rgba(0,0,0,.2); */
			margin: .5rem;
        }
        .iconBox>.photoNumber{
            position: absolute;
            bottom: .125rem;
            left: 1.75rem;
			
        }
        /*新css伪类 “:focus-within”，当本身或后代获得焦点时触发，因支持不够宽泛而弃用*/
        .form-group.focus-within > label:not(.error):not([for=title]):not([for=note]) {
            top: 0;
            color: #000;
        }
        .form-group.focus-within > label.error {
            top: .625rem;
        }
        .form-group.focus-within > input:not(#description):not(#title){
            margin-top: 2.5rem;
            margin-bottom: 0.5rem;
        }
        #description{
            margin-top: 0;
        }
        #note{
            margin-top: 1.875rem;
        }

        #title{
            margin-top: .625rem;
            margin-bottom: 0;
            text-indent: 4em;
        }
        .form-group.titleBox > input {
            margin-top: .5rem;
            border-bottom: .1rem solid rgba(0,0,0,.125);
        }
        .titleBox > input {
        }

        .form-control,
        .form-control:focus {
            margin-top: 1rem;
            margin-bottom: .625rem;
            padding-left: 0;
            font-size: .875rem;
            border-radius: 0;
            border: none;
            box-shadow: none;
        }

        input#image {
            width: 6rem;
        }

        #toReport ::-webkit-input-placeholder {
            font-size: .875rem;
            color: #aaa;
        }
		[type=button]{
			width: 33%;
			margin: 1rem .5rem 2rem;
			font-size: 1rem;
		}

        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            /* Firefox */
            -webkit-box-sizing: border-box;
            /* Safari */
        }
        .up-section .type-upimg{
            display: none;
        }
        ::-ms-clear,::-ms-reveal{display:none;}
        textarea{
            outline: none;
            line-height: 14px;
            padding-left: 4px;
            padding-top: 4px;
            border: 1px solid #ccc;
            color: #444;
            font-size: 14px;
            outline: none;
            text-align: left;
        }
        .overflow{
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        button{
            outline: none;
            border: 0px;
            font-family: "微软雅黑", "PingFang SC", "arial, helvetica, sans-serif";
        }
        input{
            font-family: "微软雅黑", "PingFang SC", "arial, helvetica, sans-serif";
            outline: none;
        }
        a {
            outline: none;
        }
        a,button{cursor:pointer;}

        body {
            background: #f4f4f4;
            font-size: 14px;
            font-fam.upimg-div .up-sectionily: "微软雅黑", "PingFang SC", "arial, helvetica, sans-serif";
        }
        .clear {
            clear: both;
        }
        .full{
            width: 100%;
            margin: 0 auto;
        }
        .full-big{
            width: 1340px;
            min-width: 1340px;
            margin: 0 auto;
        }
        .img-full{
            display: block;
            width: 100%;
        }
        ::-webkit-input-placeholder {
            color: #777;
            font-size: 14px;
        }
        :-moz-placeholder {
            /* Firefox 18- */
            color: #777;
            font-size: 14px;
        }
        ::-moz-placeholder {
            /* Firefox 19+ */
            color: #777;
            font-size: 14px;
        }
        :-ms-input-placeholder {
            color:#777;
            font-size: 14px;
        }
        /* ====clear float====== */
        /*nav a:visited{color: rgb(65,65,65);}
        aside a:visited{color: rgb(65,65,65);}*/
        .fl {
            float: left;
        }
        .fr {
            float: right;
        }
        .clear:after {
            content: '';
            display: block;
            clear: both;
        }
        /* reset */
        .pic img{display: none;}
        i,
        p,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        hr,
        br,
        em,
        dl,
        dd,
        li,
        ul,
        ol,
        td,
        th,
        pre,
        form,
        body,
        input,
        strong,
        textarea,
        select,figcaption,figure{
            margin: 0;
            padding: 0;
        }
        em {
            font-style: normal
        }
        li {
            list-style: none
        }
        a {
            text-decoration: none;
        }
        img {
            border: none;

        }
        table {
            border-collapse: collapse;
        }
        textarea {
            resize: none;
            overflow: auto;
        }
        a,button{cursor:pointer;}
        /*上传图片插件的样式*/
        .img-box{
            margin-top: 40px;
        }
        .img-box .up-p{
            margin-bottom: 20px;
            font-size: 16px;
            color: #555;
        }
        .z_photo{
            padding: 18px;
            border:2px dashed #E7E6E6;
            /*padding: 18px;*/
        }
        .z_photo .z_file{
            position: relative;
        }
        .z_file  .file{
            width: 100%;
            height: 100%;
            opacity: 0;
            position: absolute;
            top: 0px;
            left: 0px;
            z-index: 100;
        }
        .z_photo .up-section{
            position: relative;
            margin-right: 20px;
            margin-bottom: 20px;
        }
        .up-section .close-upimg{
            position: absolute;
            top: 6px;
            right: 8px;
            display: none;
            z-index: 10;
        }
        .up-section .up-span{
            display: block;
            width: 100%;
            height: 100%;
            visibility: hidden;
            position: absolute;
            top: 0px;
            left: 0px;
            z-index: 9;
            background: rgba(0,0,0,.5);
        }
        .up-section:hover{
            border: 2px solid #f15134;
        }
        .up-section:hover .close-upimg{
            display: block;
        }
        .up-section:hover .up-span{
            visibility: visible;
        }
        .z_photo .up-img{
            display: block;
            width: 100%;
            height: 100%;
        }
        .loading{
            border: 1px solid #D1D1D1;
            background:url(${ctxStatic}/${portalPage}/wx/img/loading.gif) no-repeat center;
        }
        .up-opcity{
            opacity: 0;
        }
        .img-name-p{
            display: none;
        }
        .upimg-div .up-section {
            width:3rem;
            height: 3rem;
        }
        .img-box .upimg-div .z_file {
            width: 3rem;
            height: 3rem;
        }
        .z_file .add-img {
            display: block;
            width: 3rem;
            height: 3rem;
        }
        /*遮罩层样式*/
        .mask{
            z-index: 1000;
            display: none;
            position: fixed;
            top: 0px;
            left: 0px;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,.4);
        }
        .mask .mask-content{
            width: 500px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -250px;
            margin-top: -80px;
            background: white;
            height: 160px;
            text-align: center;
        }
        .mask .mask-content .del-p{
            color: #555;
            height: 94px;
            line-height: 94px;
            font-size: 18px;
            border-bottom: 1px solid #D1D1D1;
        }
        .mask-content .check-p{
            height: 66px;
            line-height: 66px;
            position: absolute;
            bottom: 0px;
            left: 0px;
            width: 100%;
        }
        .mask-content .check-p span{
            width: 49%;
            display:inline-block;
            text-align: center;
            color:#d4361d ;
            font-size: 18px;
        }
        .check-p .del-com{
            border-right: 1px solid #D1D1D1;
        }



    </style>
    	<!-- <script type="text/javascript">
	if(/Android (\d+\.\d+)/.test(navigator.userAgent)){
		var version = parseFloat(RegExp.$1);
		if(version>2.3){
			var phoneScale = parseInt(window.screen.width)/640;
			document.write('<meta name="viewport" content="width=640, minimum-scale = '+ phoneScale +', maximum-scale = '+ phoneScale +', target-densitydpi=device-dpi">');
		}else{
			document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
		}
	}else{
		document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
	}
	</script> -->
</head>
<body id="toReport">
<form  id="mineForm" class="toReport" method="post"  enctype="multipart/form-data" action="${portalPath}/wx/saveReport">
	<input type="hidden" id="userId" name="userId" value="${userId}" />
	<input type="hidden" id="cmsId"  name="id" value="${cmsComplaint.id}" />
	<input type="hidden" id="source" name="source" value="source" />
    <input type="hidden" id="filenew" name="filenew"  />
    <input type="hidden" id="imageArray" name="imageArray" value="" />
    <div class="container">
        <div class="form-group titleBox">
            <label class="" for="title">标题：</label>
            <input class="form-control" name="title" id="title" value="${cmsComplaint.title }" aria-describedby="titleHelp" placeholder="">
			<label id="title-error" style="display:none;" class="error" for="title">请输入标题</label>
        </div>
        <div class="form-group">
            <label class="sr-only" for="description">举报内容：</label>
            <textarea class="form-control" name="description"  value="${cmsComplaint.content }" id="description" placeholder="请填写描述内容，最少输入10个字，最多输入100个字" rows="5"></textarea>
			<label id="description-error" style="display:none;"  class="error" for="description">请填写描述的内容</label>
	   </div>
    </div>
	 <div class="container" id="statusdiv" style="display:none">
        <div class="form-group titleBox">
            <label class="" for="status">状态：</label>
            <input class="form-control" name="status" id="status"  aria-describedby="titleHelp" placeholder="">
        </div>
    </div>
    <div class="container">
        <%--<div class="form-group imageBox iconBox mineimage">--%>
            	<%--<div class="upload-btn btn-old" style="display:block;background: url(${ctxStatic}/${portalPage}/wx/img/photo2.jpg) 40% 45% no-repeat;">--%>
				<%--<input type="file" name="files"    multiple="multiple" id="files" onchange="imgChange('z_photo','z_file');"></div>--%>
				<%--<div class="upload-img " ></div>--%>
        <%----%>
        <%--</div>--%>

            <div class="img-box full">
                <section class=" img-section">
                    <p class="up-p"><span class="up-span">最多可以上传5张图片，马上上传</span></p>
                    <div class="z_photo upimg-div clear" >
                        <!--<section class="up-section fl">
                                <span class="up-span"></span>
                                <img src="/img/buyerCenter/a7.png" class="close-upimg">
                                <img src="/img/buyerCenter/3c.png" class="type-upimg" alt="添加标签">
                                <img src="/img/test2.jpg" class="up-img">
                                <p class="img-namep"></p>
                                <input id="taglocation" name="taglocation" value="" type="hidden">
                                <input id="tags" name="tags" value="" type="hidden">
                            </section>-->
                        <section class="z_file fl">
                            <img src="${ctxStatic}/${portalPage}/wx/img/a11.png" class="add-img">
                            <input type="file" name="files" multiple="multiple" id="files" class="file" accept="image/jpg,image/jpeg,image/png,image/bmp" />
                        </section>
                    </div>
                </section>
            </div>
            <aside class="mask works-mask">
                <div class="mask-content">
                    <p class="del-p">您确定要删除作品图片吗？</p>
                    <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span></p>
                </div>
            </aside>
    </div>
<!--	<div class="form-group imageBox">
            <label for="image">
                <span class="iconBox"> </span>
                <input hidden="" class="form-control" name="image" id="image" type="file" multiple="multiple" accept="image/*">
            </label>
        </div>
		-->
    <div class="container">
        <div class="form-group imageBox">
            <label for="target">举报对象：</label>
            <input class="form-control" name="target" value="${cmsComplaint.companyName }" id="target" placeholder="">
			<label id="target-error" style="display:none;" class="error" for="target">举报对象不能为空</label>
        </div>
    </div>
    <div class="container">
        <div class="form-group imageBox">
            <label for="address">发现地址：</label>
            <input class="form-control" name="address" value="${cmsComplaint.companyAddress }"  id="address" placeholder="">
			<label id="target-error" style="display:none;" class="error" for="target">举报对象不能为空</label>
        </div>
    </div>
    <div class="container">
        <div class="form-group dateBox focus-within imageBox">
            <label for="date">发现时间：</label>
            <input class="form-control" name="date" id="date"  type="date" value="${cmsComplaint.findDateStr }" placeholder="">
			<label id="date-error" style="display:none;" class="error" for="date">发现时间不能为空</label>
        </div>
    </div>
	 <div class="container">
        <div class="form-group imageBox">
            <label for="target">联系方式：</label>
            <input class="form-control" name="tel" value="${cmsComplaint.remarks }" id="tel" placeholder="">
			<label id="tel-error" style="display:none;" class="error" for="tel">请输入正确手机号</label>
        </div>
    </div>
    <div class="form-group text-center" id="btn1" style="display:none">
		<button  id="submitFormBtn" type="button" class="btn btn-success form-control center">提 交</button>
    </div>
	<div class="form-group text-center" id="btn2" style="display:none">
		<button type="button"  id="submitFormBtn"  class="btn btn-success center">提 交</button>
		<button type="button"  id="cancleFormBtn"  class="btn btn-danger center">撤 销</button>
	</div>
</form>
<script>
var portalPath='${portalPath}';
</script>
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery.validate-1.15.1.js"></script>
<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/zepto.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/iscroll-zoom.js"></script>
<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/script.js"></script>
<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery.form.js"></script>
<script>
    var imageArray;
    if("${cmsComplaint.image}" != "" && "${cmsComplaint.image}" != null){
	    imageArray = ("${cmsComplaint.image}").split(",");
    }
    var delParent;
    $(".close-upimg").on("click",function(event){
        event.preventDefault();
        event.stopPropagation();
        $(".works-mask").show();
        delParent = $(this).parent();
    });
    $(".z_photo").delegate(".close-upimg","click",function(){
        $(".works-mask").show();
        delParent = $(this).parent();
    });

    $(".wsdel-ok").click(function(){
        $(".works-mask").hide();
        var numUp = delParent.siblings().length;
        if(numUp < 6){
            delParent.parent().find(".z_file").show();
        }
        var deldelParentId = delParent.attr("id");
        if(deldelParentId != null && deldelParentId != "" && deldelParentId !="undefined"){
        	imageArray.splice($.inArray('b',imageArray),1);
        }
        	delParent.remove();
    });
    
    $(function(){
        var defaults = {
            fileType: ["jpg","png","bmp","jpeg"],   // 上传文件的类型
            fileSize: 1024 * 1024 * 10                  // 上传文件的大小 10M
        };
        /*点击图片的文本框*/
        $(".file").change(function(){
            var idFile = $(this).attr("id");
            var file = document.getElementById(idFile);
            var imgContainer = $(this).parents(".z_photo"); //存放图片的父亲元素
            var fileList = file.files; //获取的图片文件
            console.log(fileList+"======filelist=====");
            var input = $(this).parent();//文本框的父亲元素
            var imgArr = [];
            //遍历得到的图片文件
            var numUp = imgContainer.find(".up-section").length;
            var totalNum = numUp + fileList.length;  //总的数量
            if(fileList.length > 5 || totalNum > 5 ){
                alert("上传图片数目不可以超过5个，请重新选择");  //一次选择上传超过5个 或者是已经上传和这次上传的到的总数也不可以超过5个
            }
            else if(numUp < 5){
                fileList = validateUp(fileList);
                for(var i = 0;i<fileList.length;i++){
                    var imgUrl = window.URL.createObjectURL(fileList[i]);
                    imgArr.push(imgUrl);
                    var $section = $("<section class='up-section fl loading'>");
                    imgContainer.prepend($section);
                    var $span = $("<span class='up-span'>");
                    $span.appendTo($section);

                    var $img0 = $("<img class='close-upimg'>");
//                     .on("click",function(event){
//                         event.preventDefault();
//                         event.stopPropagation();
//                         $(".works-mask").show();
//                         delParent = $(this).parent();
//                     });
                    $img0.attr("src","${ctxStatic}/${portalPage}/wx/img/a7.png").appendTo($section);
                    var $img = $("<img class='up-img up-opcity'>");
                    $img.attr("src",imgArr[i]);
                    $img.appendTo($section);
                    var $p = $("<p class='img-name-p'>");
                    $p.html(fileList[i].name).appendTo($section);
                    var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
                    $input.appendTo($section);
                    var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                    $input2.appendTo($section);

                }
            }
            setTimeout(function(){
                $(".up-section").removeClass("loading");
                $(".up-img").removeClass("up-opcity");
            },450);
            numUp = imgContainer.find(".up-section").length;
            if(numUp >= 5){
                $(this).parent().hide();
            }

            //input内容清空
//             $(this).val("");
        });



//         $(".z_photo").delegate(".close-upimg","click",function(){
//             $(".works-mask").show();
//             delParent = $(this).parent();
//         });

//         $(".wsdel-ok").click(function(){
//             $(".works-mask").hide();
//             var numUp = delParent.siblings().length;
//             if(numUp < 6){
//                 delParent.parent().find(".z_file").show();
//             }
// //             var deldelParentDataId = (delParent.find(".hiddenInput")).attr("data-id");
// //             if(deldelParentDataId != null && deldelParentDataId != ""){
// //             imageArray.remove(deldelParentDataId);
// //             }
//             	delParent.remove();
//         });

        $(".wsdel-no").click(function(){
            $(".works-mask").hide();
        });

        function validateUp(files){
            var arrFiles = [];//替换的文件数组
            for(var i = 0, file; file = files[i]; i++){
                //获取文件上传的后缀名
                var newStr = file.name.split("").reverse().join("");
                if(newStr.split(".")[0] != null){
                    var type = newStr.split(".")[0].split("").reverse().join("");
                    console.log(type+"===type===");
                    if(jQuery.inArray(type, defaults.fileType) > -1){
                        // 类型符合，可以上传
                        if (file.size >= defaults.fileSize) {
                            alert(file.size);
                            alert('您这个"'+ file.name +'"文件大小过大');
                        } else {
                            // 在这里需要判断当前所有文件中
                            arrFiles.push(file);
                        }
                    }else{
                        alert('您这个"'+ file.name +'"上传类型不符合');
                    }
                }else{
                    alert('您这个"'+ file.name +'"没有类型, 无法识别');
                }
            }
            return arrFiles;
        }

    })
    $(function () {	
			/**$(".mineimage").unbind().on("click",function(){
						console.log(2)
						$(".upload-btn").trigger("click");
					})**/
					
		// 手机号码验证  
		jQuery.validator.addMethod("isMobile", function(value, element) {  
			var length = value.length;  
			var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
			return this.optional(element) || (length == 11 && mobile.test(value));  
		}, "请正确填写您的手机号码");  
        judgeForBidPage();
    });
	function judgeForBidPage(){
		var useragent = navigator.userAgent;
		if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
			// 这里警告框会阻塞当前页面继续加载
			// 以下代码是用javascript强行关闭当前页面
			//var opened = window.open('about:blank', '_self');
			/*opened.opener = null;
			opened.close();*/
			var htmls='<div class="weui_msg"><div class="weui_icon_area"><i class="weui_icon_info weui_icon_msg"></i></div><div class="weui_text_area"><h4 class="weui_msg_title">请在微信客户端打开链接</h4></div></div>';			
			$("body").html(htmls);
		} else{
			window.alert = function(name){
				var iframe = document.createElement("IFRAME");
				iframe.style.display="none";
				iframe.setAttribute("src", 'data:text/plain,');
				document.documentElement.appendChild(iframe);
				window.frames[0].window.alert(name);
				iframe.parentNode.removeChild(iframe);
			};
			initPage();
		};
	};
	 var $form;
	function initPage(){
		$(":input:not(#date)").focus(function () {
            $(this).parents(".form-group").addClass("focus-within");
        }).blur(function () {
            if(!$(this).val()){
            $(this).parents(".form-group").removeClass("focus-within");
            }
        });
		$form = $("#toReport").find("form");
		initForm();
		$("#cancleFormBtn").one("click",cancleReport);
        //验证表单
        
        $form.find(":input").keyup(function () {
            //alidateForm();
			checkFormsValid();
        });
        $form.find("#submitFormBtn").one("click",submitMethods);
	};
	function checkFormsValid(){
		var can=true;
		if($("#title").val()==''){
			$("#title-error").css("display","block");
			can=false;
		}else{
			$("#title-error").css("display","none");
		}
		if($("#description").val()==''){
			$("#description-error").css("display","block");
			can=false;
		}else{
			$("#description-error").css("display","none");
		}
		if($("#target").val()==''){
			$("#target-error").css("display","block");
			can=false;
		}else{
			$("#target-error").css("display","none");
		}
		if($("#address").val()==''){
			$("#address-error").css("display","block");
			can=false;
		}else{
			$("#address-error").css("display","none");
		}
		if($("#date").val()==''){
			$("#date-error").css("display","block");
			can=false;
		}else{
			$("#date-error").css("display","none");
		}
		if($("#tel").val()==''){
			$("#tel-error").css("display","block");
			can=false;
		}else{
			var r=isPoneAvailable($("#tel"));
			if(!r){
				can=r;
			    $("#tel-error").css("display","block");
			}else{
				$("#tel-error").css("display","none");
			}
			
		}
		return can;
		
	}
	function isPoneAvailable($poneInput) {  
          var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
          if (!myreg.test($poneInput.val())) {  
              return false;  
          } else {  
              return true;  
          }  
      }  
	function  submitMethods() {
		if(null != imageArray){
			var imageArrayString = "";
			for(var i = 0; i < imageArray.length; i++){
				imageArrayString += imageArray[i] + ",";
			}
			$("#imageArray").val(imageArrayString);
		}
		if(checkFormsValid()){
			$("#submitFormBtn").html("正在提交");
			file = $("#"+"files").val();
			console.log(file)

           /* var formData = new FormData()
            for (var i = 0; i < file.length; i++) {
                formData.append('file', file[i])
            }
            console.log(formData.get('file'))*/
			$('#mineForm').submit();
		}else{
			 $form.find("#submitFormBtn").one("click",submitMethods);
		}
		 return;
            validateForm();
            if ($form.valid()) {
				$("#submitFormBtn").html("正在提交");
				var  s=parseInt($("#"+"files")[0].files[0].size)/(1024*1024);
				//alert(s)
				$('#mineForm').ajaxSubmit({
					success : function(data, status) {
                        console.log('userId',$("#userId").val());
						//alert(data);
						data=eval('('+data+')');
						
						if (data.status === "success") {

						    //alert($("#userId").val())
						   window.location.href="${portalPath}/cms/cmsComplaint/list?userId="+$("#userId").val();
						} else {
							$form.find("#submitFormBtn").one("click",submitMethods);
							$("#submitFormBtn").html("提  交");
							console.log(data.msg)
						}
					},
					error:function(e){
						alert(e)
					}
				});
				
            }else{
				$("#submitFormBtn").html("提  交");
				 $form.find("#submitFormBtn").one("click",submitMethods);
			}
        }
	function cancleReport(){
			if(confirm("确定撤销举报吗？")){
				 $.post("${portalPath}/wx/cancleReport", {
					 id: $("#cmsId").val()
                }, function (data, textStatus) {
					 data=eval('('+data+')');
					  console.log(data);
                    if (data.status === "success") {
                       window.location.href="${portalPath}/cms/cmsComplaint/list?userId="+$("#userId").val();
                    } else {
						$("#cancleFormBtn").one("click",cancleReport);
                        console.log(data.msg)
                    }
                }) ;
			}else{
				$("#cancleFormBtn").one("click",cancleReport);
			}
			
		}
		function initForm(){
			if($("#target").val()!=""){
				$("#target").parents(".form-group").addClass("focus-within");
			};
			if($("#address").val()!=""){
				$("#address").parents(".form-group").addClass("focus-within");
			};
			if($("#tel").val()!=""){
				$("#tel").parents(".form-group").addClass("focus-within");
			};
			if("${cmsComplaint.image}"!=""){
				//生成图片
// 				var img='<img src="${cmsComplaint.image}" class="mw" style="width: 100%; height: 100%; transform-origin: 0px 0px 0px; transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) scale(1) translateZ(0px); opacity: 1;">';
// 				$(".upload-img").html(img);
                 var file = "${cmsComplaint.image}";
                 var imgContainer = $(".file").parents(".z_photo"); //存放图片的父亲元素
                 var fileList = file.split(",");
				 for(var i = 0;i<fileList.length;i++){
// 	                    var imgUrl = window.URL.createObjectURL(fileList[i]);
// 	                    imgArr.push(imgUrl);
	                    var $section = $("<section class='up-section fl loading'>");
	                    $section.attr("id",fileList[i]);
	                    imgContainer.prepend($section);
	                    var $span = $("<span class='up-span'>");
	                    $span.appendTo($section);

	                    var $img0 = $("<img class='close-upimg'>");
// 	                    .on("click",function(event){
// 	                        event.preventDefault();
// 	                        event.stopPropagation();
// 	                        $(".works-mask").show();
// 	                        delParent = $(this).parent();
// 	                    });
	                    $img0.attr("src","${ctxStatic}/${portalPage}/wx/img/a7.png").appendTo($section);
	                    var $img = $("<img class='up-img up-opcity'>");
	                    $img.attr("src",fileList[i]);
	                    $img.appendTo($section);
	                    var $p = $("<p class='img-name-p'>");
	                    $p.html(fileList[i].name).appendTo($section);
	                    var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
	                    $input.appendTo($section);
	                    var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
	                    $input2.appendTo($section);

	                }
				     setTimeout(function(){
		                $(".up-section").removeClass("loading");
		                $(".up-img").removeClass("up-opcity");
		            },450);
			};
			$("#description").val("${cmsComplaint.content}");
			if("${cmsComplaint.id}"==null||"${cmsComplaint.id}"==""){
				//第一次填报
				$("#btn1").css("display","block");
				$("#btn2").remove();
				$("#statusdiv").remove();
			}else{
				$("#status").parents(".form-group").addClass("focus-within");
				$("#statusdiv").css("display","block");
				$("#status").attr('disabled','disabled');
				if("${cmsComplaint.status}"=="0"){
					//可修改 可撤销
					$("#btn1").remove();
					$("#btn2").css("display","block");
					$("#status").val("未受理");
				}else{
					//不可编辑
					$("#btn1").remove();
					$("#btn2").remove();
					$("#title").attr('disabled','disabled');
					$("#files").attr('disabled','disabled');
					$("#description").attr('disabled','disabled');
					$("#target").attr('disabled','disabled');
					$("#address").attr('disabled','disabled');
					$("#date").attr('disabled','disabled');
					$("#tel").attr('disabled','disabled');
					if("${cmsComplaint.status}"=="1"){
						$("#status").val("已受理");
					}else if("${cmsComplaint.status}"=="2"){
						$("#status").val("驳回");
					}else if("${cmsComplaint.status}"=="3"){
						$("#status").val("结束");
					}else if("${cmsComplaint.status}"=="4"){
						$("#status").val("已撤销");
					}
				}
				
			};
		}
		
        function validateForm() {
            $form.validate({
                debug: true,
                rules: {
                    title: {
                        required: true,
                        minlength: 2
                    },
                    description: {
                        required: true,
                        minlength: 10,
                        maxlength: 100
                    },
                    target: {
                        required: true
                    },
                    address: {
                        required: true
                    },
                    date: {
                        required: true
                    },
					tel:{
						 required : true,  
						minlength : 11,  
						// 自定义方法：校验手机号在数据库中是否存在  
						// checkPhoneExist : true,  
						isMobile : true  
					},
                },
                messages: {
                    title: {
                        required: "请输入标题",
                        minlength: "最少输入2个字"
                    },
                    description: {
                        required: "请填写描述的内容",
                        minlength: "最少输入10个字",
                        maxlength: "最多输入100个字"
                    },
                    target: {
                        required: "举报对象不能为空"
                    },
                    address: {
                        required: "举报地址不能为空"
                    },
                    date: {
                        required: "举报时间不能为空"
                    },
					tel: {
                        required : "请输入手机号",  
						minlength : "确认手机不能小于11个字符",  
						isMobile : "请正确填写您的手机号码"  
                    },
                }
            })
        };

	$('#files').on('change',function () {
        // console.log(this.files)
        var files = this.files;
        $('#filenew').val(files)
        console.log(files)
        for(var i =0;i<this.files.length;i++){

           // console.log(files[i].name)
        }
    })

    function imgChange(obj1, obj2) {
        //获取点击的文本框
        var file = document.getElementById("files");
        //存放图片的父级元素
        var imgContainer = document.getElementsByClassName(obj1)[0];
        //获取的图片文件
        var fileList = file.files;
        //文本框的父级元素
        var input = document.getElementsByClassName(obj2)[0];
        var imgArr = [];
        //遍历获取到得图片文件
        for (var i = 0; i < fileList.length; i++) {
            var imgUrl = window.URL.createObjectURL(file.files[i]);
            imgArr.push(imgUrl);
            var img = document.createElement("img");
            img.setAttribute("src", imgArr[i]);
            var imgAdd = document.createElement("div");
            imgAdd.setAttribute("class", "z_addImg");
            imgAdd.appendChild(img);
            imgContainer.appendChild(imgAdd);
        };
        //imgRemove();
    };

    function imgRemove() {
        var imgList = document.getElementsByClassName("z_addImg");
        var mask = document.getElementsByClassName("z_mask")[0];
        var cancel = document.getElementsByClassName("z_cancel")[0];
        var sure = document.getElementsByClassName("z_sure")[0];
        for (var j = 0; j < imgList.length; j++) {
            imgList[j].index = j;
            imgList[j].onclick = function() {
                var t = this;
                mask.style.display = "block";
                cancel.onclick = function() {
                    mask.style.display = "none";
                };
                sure.onclick = function() {
                    mask.style.display = "none";
                    t.style.display = "none";
                };

            }
        };
    };


</script>
</body>
</html>
