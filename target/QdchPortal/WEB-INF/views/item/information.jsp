<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="font-size: 14px">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="shortcut icon" href="favicon.ico">
<meta name="theme-color" content="#37a2da">
<link href="${pageContext.request.contextPath}/statics/css/index.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/statics/css/loading.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">
		<div class="body">
			<div>
				<div class="container result-wrap">
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>地名提取</h6>
								</div>
								<div class="card-body">
									<p class="card-text" id="area">
										<b>天大:</b> 贵阳;贵阳大酒店
									</p>
									<p class="card-text">
										<b>中科院:</b> 贵阳
									</p>
									<p class="card-text">
										<b>结巴:</b> 贵阳
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">地名提取说明</h6>
									<p class="card-text mt-3">体验使用，输入字数限500字以内</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>联系方式提取</h6>
								</div>
								<div class="card-body" id="relaction">
									<p class="card-text">
										<b>QQ:</b> 354343453
									</p>
									<p class="card-text">
										<b>QQ群:</b> 345343
									</p>
									<p class="card-text">
										<b>微信:</b> ewtafsdf
									</p>
									<p class="card-text">
										<b>公众号:</b> vdbsar
									</p>
									<p class="card-text">
										<b>电话号:</b> 14512313445
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">联系方式提取说明</h6>
									<p class="card-text mt-3">体验使用，输入字数限500字以内</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>观点提取</h6>
								</div>
								<div class="card-body" id="viewpoint">
									<p class="card-text">
										<b>天大:</b> 服务很亲切***中老年顾客很适合***位置较优越***设备设施不够现代***早餐很丰富
									</p>
									<p class="card-text">
										<b>BAT(汽车):</b> 贵阳
									</p>
									<p class="card-text">
										<b>BAT(酒店):</b> 服务好*位置不错
									</p>
									<p class="card-text">
										<b>BAT(景区):</b> 位置优越
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">观点提取说明</h6>
									<p class="card-text mt-3">体验使用，输入字数限500字以内</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>情感分析</h6>
								</div>
								<div class="card-body" id="emotionId">
									<p>天大:</p>
									<div class="emotion">
										<span class="emotion-face positive"> <img
											src="${pageContext.request.contextPath}/statics/images/load1.png" />
										</span> <span class="emotion-face negetive"> <img
											src="${pageContext.request.contextPath}/statics/images/load2.png" />
										</span>
										<div class="emotion-bar">
											<span class="emotion-text positive" style="right: 47.5446%;">
												正面指数：0.95 </span>
											<div class="emotion-text negetive" style="left: 22.45545%;">
												负面指数：0.05</div>
											<div class="emotion-indicator" style="width: 24.91089%;"></div>
										</div>
									</div>
									<p>BAT：</p>
									<div class="emotion">
										<span class="emotion-face positive"> <img
											src="${pageContext.request.contextPath}/statics/images/load1.png" />
										</span> <span class="emotion-face negetive"> <img
											src="${pageContext.request.contextPath}/statics/images/load2.png" />
										</span>
										<div class="emotion-bar">
											<span class="emotion-text positive" style="right: 48%;">
												正面指数：0.96 </span>
											<div class="emotion-text negetive" style="left: 2%;">
												负面指数：0.04</div>
											<div class="emotion-indicator" style="width: 4%;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">情感分析说明</h6>
									<p class="card-text mt-3">体验使用，输入字数限500字以内</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>文本分析</h6>
								</div>
								<div class="card-body" id="text">
									<div class="mb-2">
										<p>洗人</p>
										<div class="progress">
											<div style="width: 0.01%;" role="progressbar"
												aria-valuemin="0" aria-valuemax="1" aria-valuenow="0"
												class="progress-bar bg-danger">
												<span>0.01%</span>
											</div>
										</div>
									</div>
									<div class="mb-2">
										<p>充值</p>
										<div class="progress">
											<div style="width: 80%;" role="progressbar" aria-valuemin="0"
												aria-valuemax="1" aria-valuenow="0"
												class="progress-bar bg-warning">
												<span>80%</span>
											</div>
										</div>
									</div>
									<div class="mb-2">
										<p>广告</p>
										<div class="progress">
											<div style="width: 71.58%;" role="progressbar"
												aria-valuemin="0" aria-valuemax="1" aria-valuenow="0"
												class="progress-bar bg-info">
												<span>71.58%</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col">
											<p>涉黄</p>
											<div class="progress">
												<div style="width: 42%;" role="progressbar"
													aria-valuemin="0" aria-valuemax="1" aria-valuenow="0"
													class="progress-bar bg-warning"></div>
											</div>
										</div>
										<div class="col">
											<p>涉政</p>
											<div class="progress">
												<div style="width: 0%;" role="progressbar" aria-valuemin="0"
													aria-valuemax="1" aria-valuenow="0"
													class="progress-bar bg-danger"></div>
											</div>
										</div>
										<div class="col">
											<p>正常</p>
											<div class="progress">
												<div style="width: 100%;" role="progressbar"
													aria-valuemin="0" aria-valuemax="1" aria-valuenow="0"
													class="progress-bar bg-success"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">文本分析说明</h6>
									<p class="card-text mt-3">洗人：分值越高，洗人概率越大。适用于游戏聊天</p>
									<p class="card-text">充值：分值越高，充值概率越大。适用于游戏聊天</p>
									<p class="card-text">广告：分值越高，广告概率越大。适用于网页评论</p>
									<p class="card-text">涉黄：布尔值，含相关词汇即1分，否则0分</p>
									<p class="card-text">涉政：布尔值，含相关词汇即1分，否则0分</p>
									<p class="card-text">正常：布尔值，无涉黄无涉政即为正常文本</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-lg-8">
							<div class="card">
								<div class="card-header">
									<h6>词云</h6>
								</div>
								<div class="card-body">
									<div class="echarts" id="echartsCiYun"></div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="card">
								<div class="card-body">
									<h6 class="card-subtitle mb-2 text-muted">地名提取说明</h6>
									<p class="card-text mt-3">体验使用，输入字数限500字以内</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/jquery-2.2.4.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/echarts-3.x.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/echarts-wordcloud.min.js"></script>
	<script type="text/javascript">
			$.ajax({
			    type: "POST",
			    async: false,
			    url: "",
			    data: {},
			    dataType: "json",
			    success: function(data){
			    	echartsCY(data);
			    }
			});
			function echartsCY(data){
				var arrData = [];
				$.each(data,function(index,item){
					arrData.push({
						name:index.cynx,
						value:index.fvalue
					})
				})
				var _cy = echarts.init($("#echartsCY")[0]);
				_cy.setOption({
					title: {
				        text: ''
				    },
				    tooltip: {
				        show: true
				    },
				    series: [{
				        name: '',
				        type: 'wordCloud',
				        size: ['80%', '80%'],
				        textRotation : [0, 45, 90, -45],
				        textPadding: 0,
				        autoSize: {
				            enable: true,
				            minSize: 14
				        },
				        textStyle: {
				            normal: {
				                color: function () {
				                    return 'rgb(' + [
				                            Math.round(Math.random() * 255),
				                            Math.round(Math.random() * 255),
				                            Math.round(Math.random() * 255)
				                        ].join(',') + ')';
				                }
				            },
				            emphasis: {
				                shadowBlur: 10,
				                shadowColor: '#333'
				            }
				        },
				        data: arrData
				        /*[
				        	{
				        		name:'技术',
				        		value:23
				        	},
				        	{
				        		name:'第三方',
				        		value:67
				        	},
				        	{
				        		name:'手动阀',
				        		value:13
				        	},
				        	{
				        		name:'问题',
				        		value:90
				        	},
				        	{
				        		name:'李若彤',
				        		value:345
				        	}
				        ]*/
				    }]
				});
			}
			
			//地名接口
			function areaInterface(keyword){
				var _html="";
				$.ajax({
					type:"post",
					url:"/portal/land",
					data:{"query":keyword},
					dataType:"json",
					success:function(data){
						_html+="<p class=\"card-text\\">"+
						"<b>天大:</b> "+data.result.tju+"</p>"+
					"<p class=\"card-text\">"+
						"<b>中科院:</b> "+data.result.cas+"</p>"+
					"<p class=\"card-text\">"+
						"<b>结巴:</b> "+ data.result.jb+"</p>";
					}
					
				})
				$('#area').html(_html);
			}
			//联系方式接口
			function relactionInterface(keyword){
				var _html="";
				$.ajax({
					type:"post",
					url:"/portal/relaction",
					data:{"query":keyword},
					dataType:"json",
					success:function(data){
						_html+="<p class=\"card-text\">"+
						"<b>QQ:</b> "+data.result.QQ+""+
						"</p>"+
						"<p class=\"card-text\">"+
							"<b>QQ群:</b> "+data.result.QG+""+
						"</p>"+
						"<p class=\"card-text\">"+
							"<b>微信:</b> "+data.result.WX+""+
						"</p>"+
						"<p class=\"card-text\">"+
							"<b>公众号:</b> "+data.result.GZ+""+
						"</p>"+
						"<p class=\"card-text\">"+
							"<b>电话号:</b> "+data.result.TE+""+
						"</p>";
					}
					
				})
				$('#relaction').html(_html);
			}
			//观点提取
			function viewpointInterface(keyword){
				var _html="";
				$.ajax({
					type:"post",
					url:"/portal/viewpoint",
					data:{"query":keyword},
					dataType:"json",
					success:function(data){
						_html+="<p class=\"card-text\">"+
						"<b>天大:</b> "+data.result.tju+""+
					"</p>"+
					"<p class=\"card-text\">"+
						"<b>BAT(汽车):</b> "+data.result.baidu.car+""+
					"</p>"+
					"<p class=\"card-text\">"+
						"<b>BAT(酒店):</b> "+data.result.baidu.hotel+""+
					"</p>"+
					"<p class=\"card-text\">"+
						"<b>BAT(景区):</b> "+data.result.baidu.spot+""+
					"</p>";
					}
					
				})
				$('#viewpoint').html(_html);
			}
			//情感分析接口
			function emotionInterface(keyword){
				var _html="";
				$.ajax({
					type:"post",
					url:"/portal/emotion",
					data:{"query":keyword},
					dataType:"json",
					success:function(data){
						_html+="<p>天大:</p>"+
						"<div class=\"emotion\" >"+
						"<span class=\"emotion-face positive\"> <img"+
							"src=\"${pageContext.request.contextPath}/statics/images/load1.png\" />"+
						"</span> <span class=\"emotion-face negetive\"> <img"+
							"src=\"${pageContext.request.contextPath}/statics/images/load2.png\" />"+
						"</span>"+
						"<div class=\"emotion-bar\">"+
							"<span class=\"emotion-text positive\" style=\"right: "+data.result.tju*100+"%;\">"+
								"正面指数："+data.result.tju+" </span>"+
							"<div class=\"emotion-text negetive\" style=\"left: "+(1-data.result.tju)+"%;\">"+
								"负面指数："+(1-data.result.tju)+"</div>"+
							"<div class=\"emotion-indicator\" style=\"width: "+(1-data.result.tju)*2+"%;\"></div>"+
						"</div>"+
					"</div>"+
					"<p>BAT：</p>"+
						"<div class=\"emotion\">"+
							"<span class=\"emotion-face positive\"> <img"+
								"src=\"${pageContext.request.contextPath}/statics/images/load1.png\" />"+
							"</span> <span class=\"emotion-face negetive\"> <img"+
								"src=\"${pageContext.request.contextPath}/statics/images/load2.png\" />"+
							"</span>"
							"<div class=\"emotion-bar\">"+
								"<span class=\"emotion-text positive\" style=\"right: "+data.result.baidu*100+"%;\">"+
								"正面指数："+data.result.baidu+" </span>"+
								"<div class=\"emotion-text negetive\" style=\"left: 2%;\">"+
								"负面指数：0.04</div>"+
								"<div class=\"emotion-indicator\" style=\"width: 4%;\"></div>"+
							"</div>"+
						"</div>";
					
					}
					
				})
				$('#emotionId').html(_html);
			}
			//文本分析接口
			function textInterface(keyword){
				var _html="";
				$.ajax({
					type:"post",
					url:"/portal/text",
					data:{"query":keyword},
					dataType:"json",
					success:function(data){
						_html+="<div class=\"mb-2\">"+
						"<p>洗人</p>"+
						"<div class=\"progress\">"+
							"<div style=\"width: "+data.result.xi*100+"%;\" role=\"progressbar\""+
								"aria-valuemin=\"0\" aria-valuemax=\"1\" aria-valuenow=\"0\""+
								"class=\"progress-bar bg-danger\">"+
								"<span>"+data.result.xi*100+"%</span>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class=\"mb-2\">"+
						"<p>充值</p>"+
						"<div class=\"progress\">"+
							"<div style=\"width: "+data.result.ch*100+"%;\" role=\"progressbar\" aria-valuemin=\"0\""+
								"aria-valuemax=\"1\" aria-valuenow=\"0\""+
								"class=\"progress-bar bg-warning\">"+
								"<span>"+data.result.ch*100+"%</span>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class=\"mb-2\">"+
						"<p>广告</p>"+
						"<div class=\"progress\">"+
							"<div style=\"width: "+data.result.ad*100+"%;\" role=\"progressbar\""+
								"aria-valuemin=\"0\" aria-valuemax=\"1\" aria-valuenow=\"0\""+
								"class=\"progress-bar bg-info\">"+
								"<span>"+data.result.ad*100+"%</span>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class=\"row\">"+
						"<div class=\"col\">"+
							"<p>涉黄</p>"+
							"<div class=\"progress\">"+
								"<div style=\"width: 42%;\" role=\"progressbar\""+
									"aria-valuemin=\"0\" aria-valuemax=\"1\" aria-valuenow=\""+data.result.sex+"\""+
									"class=\"progress-bar bg-warning\"></div>"+
							"</div>"+
						"</div>"+
						"<div class=\"col\">"+
							"<p>涉政</p>"+
							"<div class=\"progress\">"+
								"<div style=\"width: "+data.result.politic*100+"%;\" role=\"progressbar\" aria-valuemin=\"0\""+
									"aria-valuemax=\"1\" aria-valuenow=\""+data.result.politic*100+"\""+
									"class=\"progress-bar bg-danger\"></div>"+
							"</div>"+
						"</div>"+
						"<div class=\"col\">"+
							"<p>正常</p>"+
							"<div class=\"progress\">"+
								"<div style=\"width: "+data.result.normal*100+"%;\" role=\"progressbar\""+
									"aria-valuemin=\"0\" aria-valuemax=\"1\" aria-valuenow=\""+data.result.normal*100+"\""+
									"class=\"progress-bar bg-success\"></div>"+
							"</div>"+
						"</div>"+
					"</div>";
					}
					
				})
				$('#text').html(_html);
			}
		</script>
</body>
</html>
