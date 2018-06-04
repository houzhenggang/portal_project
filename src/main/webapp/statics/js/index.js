var val = '';//搜索条件
var topBtnTime = '';//时间	
var navLiA = '';//所属页面
var btnSearch = $("#btnSearch");//搜索对象
var time = $("#topBtn").children("button");//时间对象
var htmlInfor = $("#nav").children("li").find("a");//所属模块对象
var dataCS = '';//参数集合
var onOff1 = onOff2 = onOff3 = onOff4 = onOff5 = onOff6 = false;


//时间按钮切换
var topBtnObj = $("#topBtn").children("button");
topBtnObj.click(function(){
	$(this).removeClass("btn-secondary");
	$(this).addClass("btn-success").siblings().removeClass("btn-success");
	$(this).siblings().addClass("btn-secondary");
})
//搜索点击
btnSearch.on("click",function(){
	val = $(this).siblings().val();//获取搜索条件
	time.each(function(){
		if($(this).hasClass("btn-success")){
			topBtnTime = $(this).attr("data-val");//获取时间			
		}
	})
	htmlInfor.each(function(){
		if($(this).hasClass("router-link-active")){
			navLiA = $(this).text();//获取所属模块		
		}
	})
	dataCS = {
		"aspect":val,
    	"num":topBtnTime,
    	"textname":navLiA
	}
	//获取数据
	allData();
});
//时间点击
time.on("click",function(){
	topBtnTime = $(this).attr("data-val");//获取时间	
	val = btnSearch.siblings().val();//获取搜索条件
	htmlInfor.each(function(){
		if($(this).hasClass("router-link-active")){
			navLiA = $(this).text();//获取所属模块		
		}
	})
	dataCS = {
		"aspect":val,
    	"num":topBtnTime,
    	"textname":navLiA
	}
	//获取数据
	allData();
})
//所属模块点击
htmlInfor.on("click",function(){
	navLiA = $(this).text();//获取所属模块	
	val = btnSearch.siblings().val();//获取搜索条件
	time.each(function(){
		if($(this).hasClass("btn-success")){
			topBtnTime = $(this).attr("data-val");//获取时间			
		}
	})
	dataCS = {
		"aspect":val,
    	"num":topBtnTime,
    	"textname":navLiA
	}
	//获取数据
	allData();
})
function allData(){
	if(navLiA == "数据统计"){
		
		$(".dataTJ").show();
		$(".dataTJ").siblings().hide();
		dataTJ();
	}
	else if(navLiA == "数据分析"){
		$(".dataFX").show();
		$(".dataFX").siblings().hide();
		dataFX();
	}
	else if(navLiA == "详细数据"){
		$(".dataInfor").show();
		$(".dataInfor").siblings().hide();
		dataInfor();
	}
}
/*数据统计*/
function dataTJ(){
	
	//仪表盘
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/hot",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	echartsHot(data);
	    	onOff1 = true;
	    }
	});
	//饼图
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/resource",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	echartsMedia(data);
	    	onOff2 = true;
	    }
	});
	//走势图  
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/allresource",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	 
	    	var name=data[0].source+"";
	    	
	    	echartsTrend(data,name);
	    	
	    	onOff3 = true;
	    }
	});
	//地图 
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/area",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	echartsMap(data);
	    	onOff4 = true;
	    }
	});	
	//词云 
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/wordCloud",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	/*for (var i in data) {
	    		 console.log(i)//属性
	    	   console.log(data[i])//属性值
	    	    
	    	}*/
	    	echartsHord(data);
	    	onOff5 = true;
	    }
	});
	//最新消息
	NewInfor(dataCS);
}
//最新消息
function NewInfor(dataCS){
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/newMessage",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	//var _data = data.result;
	        var _html = '';
	        $.each(data, function(index, value) {
	        		_html += "<tr><td aria-colindex='1'>"+ value.comment
	        			  +"</td><td aria-colindex='2'>"+ value.source
	        			  +"</td><td aria-colindex='3'>"+ value.area
	        			  +"</td></tr>";
	        });
	        $('#__BVID__37 tbody').html(_html);
	    	onOff6 = true;
			//控制loading加载效果
			if(onOff1 && onOff2 && onOff3 && onOff4 && onOff5 && onOff6){
				$(".loading").hide();
			}
	    }
	});
}
/*数据分析*/
$(".text-truncate").on("mouseenter",function(){
	var obj = this;
	var text = $(obj).text();
	position(obj,text);
}).on("mouseleave",function(){
	$(".tooltip").css("display","none");
})
function dataFX(){
	//水球图
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/frendly",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	echartsWater(data);
	    	onOff1 = true;
	    }
	});
	//雷达图
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/negative",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	echartsRadar(data);
	    	onOff2 = true;
	    }
	});
	//圆环图
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/network",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	echartsPie(data);
	    	onOff3 = true;
	    }
	});
	//评价
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/evaluate",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	console.log(data);
	    	echartsPJ(data);
	    	onOff4 = true;
	    }
	});
	//评价折线、词云
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/broken",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	echartsTitle(data);
	    }
	});
	//评价信息
	pjInfor(dataCS);
}
//评价信息
function pjInfor(dataCS){
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/information",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	var _data = data.result;
	        var htmlXP,htmlZP,htmlHP;
	        $.each(_data, function(index, value) {
	        	    		
	        });
	        $('.cpxx').html(htmlXP);
	        $('.zpxx').html(htmlZP);
	        $('.hpxx').html(htmlHP);
	    	onOff5 = true;
			//控制loading加载效果
			if(onOff1 && onOff2 && onOff3 && onOff4 && onOff5){
				$(".loading").hide();
			}
	    }
	});
}
//评价折线、词云
function echartsTitle(data){
	$.each(data,function(index,item){
		$("#repu-feat-tab").append("<button type='button' class='btn btn-outline-secondary btn-sm'>"+index.source+"</button>");
	})
	var btnObj = $("#repu-feat-tab").children("button");
	var text = '';
	btnObj.click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".clickBlock").css("display","flex").siblings().hide();
		text = $(this).text();
		//折线图
		$.ajax({
		    type: "POST",
		    async: false,
		    url: "/portal/data/brokenInformation",
		    data: {"text":text},
		    dataType: "json",
		    success: function(data){
		    	echartsCY(data);
		    	echartsLine(data);
		    	yesterday();
		    }
		});
	})
}

/*详细数据*/
var textLY,textQG,textXG,textLB;
function dataInfor(){
	//来源
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/dataSource",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	var html = '';
			$.each(data,function(index,item){
				html += "<span class='badge'>"+ item.source +"</span>";
			})
			$(".listLY").append(html);
			onOff1 = true;
	    }
	});
	//类别
	$.ajax({
	    type: "POST",
	    async: false,
	    url: "/portal/data/class",
	    data: dataCS,
	    dataType: "json",
	    success: function(data){
	    	
	    	var html = '';
			$.each(data,function(index,item){
				html += "<span class='badge'>"+ item.classe +"</span>";
			})
			$(".listLB").append(html);
			onOff2 = true;
	    }
	});
	fourModel();
	
}
$(document).on("click",'.badge',function(){
	alert(5);
	$(this).addClass("badge-primary").siblings("span.badge").removeClass("badge-primary");
	fourModel();
});
function fourModel(){
	var otherSpanText = $(".detail-filter").children();	
	otherSpanText.each(function(){		
		if($(this).hasClass("listLY")){
			
			textLY = $(this).children("span.badge-primary").text();
			
		}
		else if($(this).hasClass("listQG")){
			
			textQG = $(this).children("span.badge-primary").text();
			
		}
		else if($(this).hasClass("listXG")){
			textXG = $(this).children("span.badge-primary").text();
		}
		else if($(this).hasClass("listLB")){
			textLB = $(this).children("span.badge-primary").text();
		}
	})
	dataCS = {
		"aspect":val,
    	"num":topBtnTime,
    	"textname":navLiA,
    	"textLY":textLY,
    	"textQG":textQG,
    	"textXG":textXG,
    	"textLB":textLB
	}
	$(function(){
		$.ajax({
		    type: "POST",
		    async: false,
		    url: "/portal/data/extract",
		    data: dataCS,
		    dataType: "json",
		    success: function(data){
		    	var html = '';
				$.each(data,function(index,item){
					html="<tr>"+
					"<td style=\"width:4rem;\">"+
						"<span class=\"badge badge-secondary\" data-title=\"分值: 0.545\">"+中性+"</span>"+
					"</td>"+
					"<td>"+Note8,相机使用教程+""+
						"<div class=\"mt-2 text-secondary\">"+item.comment+"&npsb"+item.date+"</div>"+
					"</td>"+
				"</tr>";
				})
				$(".tableInfor tbody").append(html);
				$('.tableInfor tbody').paginathing({
				  perPage: 5,
				  insertAfter: '.tableInfor'
				});
				$(".tableInfor tbody tr td span").on("mouseenter",function(){
					var obj = this;
					$(".arrow").css("left","31px")
					var text = $(obj).attr("data-title");
					position(obj,text);
				}).on("mouseleave",function(){
					$(".tooltip").css("display","none");
				})
				onOff3 = true;
				//控制loading加载效果
				if(onOff1 && onOff2 && onOff3){
					$(".loading").hide();
				}
		    }
		});
	});
}

//设置信息框位置
function position(obj,text){
	$(".tooltip-inner").text(text);
	var elemTop = $(obj).offset().top;//元素本身绝对位置
	var parentLeft = $(obj).parent().offset().left;//父元素绝对位置
	var parentWidth = $(obj).parent().outerWidth();//父元素占有宽度
	var parentsHeight = $(".tooltip").height();	//信息框高度
	var parentsWidth = $(".tooltip").width();//信息框宽度
	elemTop = Math.round(elemTop - parentsHeight - 10);
	var elemLeft = Math.round(parentLeft + (parentWidth / 2) - (parentsWidth / 2));	
	$(".tooltip").css({
		"transform":"translate3d("+elemLeft+"px,"+elemTop+"px,0px)",
		"willChange":"transform",
		"top":0,
		"left":0,
		"display":"block"
	})
}

