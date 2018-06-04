<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="font-size: 14px">
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<link rel="shortcut icon" href="favicon.ico">
		<meta name="theme-color" content="#37a2da">
		<link href="${pageContext.request.contextPath}/statics/css/index.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/statics/css/loading.css" rel="stylesheet">
	</head>
	<body>
		<div id="app">
			<div class="body">
				<div>
					<div class='container pb-4'>
						<div class='card'>
							<div class='card-body'>
								<div class='row'>
									<div class='col-md-6 d-flex'>
										<input type='text' class='mb-2 form-control' style='max-width: 200px;;'/>
										<button id='btnSearch' type='button' class='btn mb-2 btn-primary btn-block' style='width: 60px; margin-left: 10px;'>搜索</button>
									</div>
									<div id='topBtn' class='col-md-6 text-right'>
										<button data-val='0' type='button' class='btn btn-success'>全部</button>
										<button data-val='1' type='button' class='btn btn-secondary'>一天</button>
										<button data-val='3' type='button' class='btn btn-secondary'>三天</button>
										<button data-val='7' type='button' class='btn btn-secondary'>一周</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--loading-->
					<div class='loading overlay fixed'>
						<div class='loading-spinner'>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
						</div>
					</div>
					<!--loading-->
					<div class='container'>
						<ul class='row' id='nav'>
							<li data-id='' data='dataCount' class='result-tab col'><a class='router-link-exact-active router-link-active'>数据统计</a></li>
							<li data-id='' data='dataAnalyse' class='result-tab col'><a>数据分析</a></li>
							<li data-id='' data='dataInfor' class='result-tab col'><a>详细数据</a></li>
						</ul>
					</div>
					<div>
						<!--数据统计-->
						<div class="container result-wrap dataTJ">
							<div class="row">
								<div class="col-lg-6">
									<div class="card">
										<div class="card-header">
											<h6>热度概况</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsHot"></div>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="card">
										<div class="card-header">
											<h6>活跃媒体</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsMedia">
											   
											</div>
										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="card">
										<div class="card-header">
											<h6>声量走势</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsTrend"></div>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="card">
										<div class="card-header">
											<h6>地域分布</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsMap"></div>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="card">
										<div class="card-header">
											<h6>词云</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsHord"></div>
										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="card">
										<div class="card-header">
											<h6>最新信息</h6>
										</div>
										<div class="card-body">
											<table id="__BVID__37" aria-busy="false" aria-colcount="3" class="table b-table table-striped">
												<thead>
													<tr>
														<th aria-colindex="1" width:500px>最新信息</th>
														<th aria-colindex="2">信息来源</th>
														<th aria-colindex="3">发布地点</th>
													</tr>
												</thead>
												<tbody>
													
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--数据分析-->
						<div class="container result-wrap dataFX">
							<div class="row">
								<div class="col-lg-4">
									<div class="card">
										<div class="card-header">
											<h6>总体友好度</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsWater"></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card">
										<div class="card-header">
											<h6>负面评论分布</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsRadar"></div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card">
										<div class="card-header">
											<h6>全网声量</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsPie"></div>
										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="card">
										<div class="card-header">
											<h6>评价</h6>
										</div>
										<div class="card-body">
											<div class="echarts" id="echartsPJ"></div>
										</div>
									</div>
								</div>
								<div class="col-12">
									<div class="card">
										<div class="card-header"><!--active-->
											<div class="d-flex justify-content-around" id="repu-feat-tab">
												
											</div>
										</div>
										<div class="card-body">
											<div class="text-secondary text-center py-5">请在上方选择特性</div>
											<div class="row clickBlock">
												<div class="col-md-6" style="position: relative;">
													<div class="echarts" id="echartsLine"></div>
													<div id="dateTime"></div>
												</div>
												<div class="col-md-6">
													<div class="echarts" id="echartsCY"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card opinion-infolist">
										<div class="card-header">
											<h6>差评信息</h6>
										</div>
										<div class="card-body cpxx">
											
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card opinion-infolist">
										<div class="card-header">
											<h6>中评信息</h6>
										</div>
										<div class="card-body zpxx">
											
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card opinion-infolist">
										<div class="card-header">
											<h6>好评信息</h6>
										</div>
										<div class="card-body hpxx">
											
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--详细数据-->
						<div class="container result-wrap dataInfor">
							<div class="row">
								<div class="col-12 my-2 detail-table">
									<div class="card">
										<div class="card-body">
											<div class="list-group detail-filter" id="test">
												<div class="list-group-item listLY">
													<div class="detail-filter-label">来源</div>
													<span class="badge badge-primary">全部</span>
													<span class="mx-1" style="color: rgb(204, 204, 204);">|</span>
													<!-- <span class="badge">机锋网</span>
													<span class="badge">百度贴吧</span>
													<span class="badge">盖世乐论坛</span>
													<span class="badge">微博</span>
													<span class="badge">其他</span> -->
												</div>
												<div class="list-group-item listQG">
													<div class="detail-filter-label">情感</div>
													<span class="badge badge-primary">全部</span>
													<span class="mx-1" style="color: rgb(204, 204, 204);">|</span>
													<span class="badge">正向</span>
													<span class="badge">中性</span>
													<span class="badge">负向</span>
												</div>
												<div class="list-group-item listXG">
													<div class="detail-filter-label">相关</div>
													<span class="badge badge-primary">全部</span>
													<span class="mx-1" style="color: rgb(204, 204, 204);">|</span>
													<span class="badge">相关</span>
													<span class="badge">无关</span>
												</div>
												<div class="list-group-item listLB">
													<div class="detail-filter-label">类别</div>
													<span class="badge badge-primary">全部</span>
													<span class="mx-1" style="color: rgb(204, 204, 204);">|</span>
													<!-- <span class="badge">第三方APP</span>
													<span class="badge">基础APP</span>
													<span class="badge">外观质量</span>
													<span class="badge">性能</span>
													<span class="badge">电池</span>
													<span class="badge">通讯</span>
													<span class="badge">发热</span>
													<span class="badge">屏幕</span>
													<span class="badge">识别</span>
													<span class="badge">其他</span> -->
												</div>
											</div>
											
											<div class="table-responsive-xl">
												<table class="table b-table table-striped table-bordered tableInfor">
													<thead>
														<tr>
															<th tabindex="0" aria-colindex="1" aria-label="Click to sort Descending" class="sorting sorting_asc" aria-sort="ascending">
																标签
															</th>
															<th aria-colindex="2"></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td style="width:4rem;">
																<span class="badge badge-secondary"  data-original-title="分值: 0.545">
																	中性
																</span>
															</td>
															<td>
																Note8,相机使用教程
																<div class="mt-2 text-secondary">微博  2018-01-29 05:55:50</div>
															</td>
														</tr>
														<tr>
															<td style="width:4rem;">
																<span class="badge badge-secondary" data-title="分值: 0.545">
																	中性
																</span>
															</td>
															<td>
																Note8,相机使用教程
																<div class="mt-2 text-secondary">微博  2018-01-29 05:55:50</div>
															</td>
														</tr>
														<tr>
															<td style="width:4rem;">
																<span class="badge badge-secondary" data-title="分值: 0.110">
																	中性
																</span>
															</td>
															<td>
																Note8,相机使用教程
																<div class="mt-2 text-secondary">微博  2018-01-29 05:55:50</div>
															</td>
														</tr>
														<tr>
															<td style="width:4rem;">
																<span class="badge badge-secondary" data-title="分值: 0.001">
																	中性
																</span>
															</td>
															<td>
																Note8,相机使用教程
																<div class="mt-2 text-secondary">微博  2018-01-29 05:55:50</div>
															</td>
														</tr>
														
													</tbody>
												</table>
											</div>
											
											
										</div>
									</div>
								</div>
								
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tooltip bs-tooltip-top show" role="tooltip" tabindex="-1" x-placement="top">
			<div class="arrow" style="left:95px;">
				
			</div>
			<div class="tooltip-inner">
				
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-2.2.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/echarts-3.x.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/echarts-liquidfill.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/china.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/echarts-wordcloud.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/index.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/dataFunction.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/paginathing.js"></script>
	</body>
</html>