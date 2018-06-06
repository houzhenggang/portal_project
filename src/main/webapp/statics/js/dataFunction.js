/*******************/
/*数据统计*/
//仪表盘
function echartsHot(data){
	var arrData = [];
	$.each(data,function(index,item){
		arrData.push({
			name:'热度值',
			value:item.nums*100
		})
	})
	var _hot = echarts.init($("#echartsHot")[0]);
	_hot.setOption({
	    tooltip : {
	        formatter: "{a} <br/>{c}%"
	    },
	    toolbox: {
			show : false,   //去掉功能图标
	        feature: {
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    series: [
	        {
	            name: '热度值',
	            type: 'gauge',
	            axisLine: {            // 坐标轴线
	                show: true,        // 默认显示，属性show控制显示与否
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.3, 'skyblue'],[0.7, 'orange'],[1, '#FB7293']], 
	                    width: 30
	                }
	            },
	            title : {
	                show : true,
	                offsetCenter: [0, "65%"],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: '#333',
	                    fontSize : 15
	                }
	            },
	            data:arrData
	        }
	    ]
	});
}
//饼图
function echartsMedia(data){
	var title = [];
	var arrData = [];
	$.each(data,function(index,item){
		arrData.push({
			name:item.source,
			value:item.nums
		})
		title.push({
			
			name:item.source
		})
	})
	var _media = echarts.init($("#echartsMedia")[0]);
	_media.setOption({
	    tooltip : {
	        formatter: "{b} :<br/>{c} ({d}%)"
	    },
	    legend: {
			orient : 'vertical',
	        x : 'left',
	        itemWidth:10,
	        itemHeight:10,
	        data: title
	    },
	    toolbox: {
			show : false,  //去掉功能图标
	        feature: {
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    series: [
	        {
	            name: '',
	            type: 'pie',
				radius : [50, 110],
				center : ['60%', '50%'],
				roseType : 'radius',
	            labelLine:{    
	                normal:{    
	                    length:2 
	                }    
	            }, 
	            data: arrData
	        }
	    ]
	});
}
//走势图  
function echartsTrend(data,name){
	console.log(data);
	var title = [];
	var arrData = [];
	var time=[];//获取到日期
	
	$.each(data,function(index,item){
		
		if(name==item.source){
			time.push(item.date);
		}
	})
	var all=[];
	$.each(data,function(index,item){
		if("全部"==item.source){
			all.push(item.nums);
		}
	})
	$.each(data,function(index,item){
		if("全部"==item.source){
			return false;
		}
		var arr=[];
		
		$.each(item.souceDatas,function(x,y){
				arr.push(y.nums);
		})
	
		arrData.push({
			type:'line',
            stack: '总量',
            smooth:0.3,
			itemStyle: {normal: {areaStyle: {type: 'default'}}},
			name:item.source,
			data:arr
		});
		
		title.push(item.source);
		
		
	})
	arrData.push({
		type:'line',
        stack: '总量',
        smooth:0.3,
		itemStyle: {normal: {areaStyle: {type: 'default'}}},
		name:"全部",
		data:all
	});

	
	var _trend = echarts.init($("#echartsTrend")[0]);
	_trend.setOption({
	    tooltip : {
	    	trigger: 'axis',
	        axisPointer: { 
	            type: 'cross',//十字准星
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    legend: {
	        data:title 
	    },
	    toolbox: {
			show : false,  //去掉功能图标
	        feature: {
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine:{show: false},//去除网格线
	            boundaryGap : false,
	            data : time
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            splitLine:{show: false},//去除网格线
	            splitArea : {show : true},//保留网格区域
	            axisLabel : {
	                formatter: '{value}',
	                textStyle: {
	                    color: '#888'
	                }
	            }
	        }
	    ],
	    series: arrData
	    /*[
	        {
	            name:'机锋网',
	            type:'line',
	            stack: '总量',
	            smooth:0.3,
				itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[10, 20, 30, 40, 50, 60, 70]
	        },
	        {
	            name:'百度贴吧',
	            type:'line',
	            stack: '总量',
	            smooth:0.3,
				itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[10, 20, 30, 40, 50, 60, 70]
	        },
	        {
	            name:'微博',
	            type:'line',
	            stack: '总量',
	            smooth:0.3,
				itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[10, 20, 30, 40, 10, 60, 70]
	        },
	        {
	            name:'其他来源',
	            type:'line',
	            stack: '总量',
	            smooth:0.3,
				itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[10, 30, 30, 40, 50, 60, 70]
	        },
	        {
	            name:'全部',
	            type:'line',
	            stack: '总量',
	            smooth:0.3,
				itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[50, 100, 150, 200, 250, 300, 350]
	        }
	    ]*/
	});
}
//地图
function echartsMap(data){
	var max =0;
	var arrData = [{//隐藏南海诸岛
        name:"南海诸岛",value:0,  
        itemStyle:{  
            normal:{opacity:0,label:{show:true}}  
        }  
    }];
	$.each(data,function(index,item){

		arrData.push({
			name:item.area,
			value:item.nums
		});
		max = item.max; //根据后台返回json数据格式进行修改
		
	})
	
	var _map = echarts.init($("#echartsMap")[0]);
	_map.setOption({
		 tooltip: {
	        formatter: '{b}：<br/>{c}', //提示标签格式
	        backgroundColor:"rgba(0,0,0,.5)",//提示标签背景颜色
	        textStyle:{color:"#fff"} //提示标签字体颜色
	    },
        visualMap: {
            min: 0,
            max: max, //需从后台获取
            text:['高','低'],
            realtime: true,
            calculable: true,
            inRange: {
                color: ['#89EEEF','#00A4D9']
            }
        },
	    series: [{
	        type: 'map',
	        mapType: 'china',
	        label: {
	            normal: {
	                show: false,//显示省份标签
	                textStyle:{color:"#c71585"}//省份标签字体颜色
	            },    
	            emphasis: {//对应的鼠标悬浮效果
	                show: false,
	                textStyle:{color:"#800080"}
	            } 
	        },
	        itemStyle: {
	            normal: {
	                borderWidth: 2,//区域边框宽度
	                borderColor: '#8BE8F5',//区域边框颜色
	            },
	            emphasis: {
	                borderWidth: 0,
	                borderColor: '#4b0082',
	                areaColor:"#FFDB37",
	            }
	        },
	        data:
	        	
			    arrData
	            /*{name:'北京', value:143},
	            {name:'天津', value:99},
	            {name:'上海', value:66},
	            {name:'重庆', value:69},
	            {name:'河北', value:71},
	            {name:'河南', value:68},
	            {name:'云南', value:64},
	            {name:'辽宁', value:84},
	            {name:'黑龙江', value:63},
	            {name:'湖南', value:60},
	            {name:'安徽', value:100},
	            {name:'山东', value:66},
	            {name:'新疆', value:59},
	            {name:'江苏', value:103},
	            {name:'浙江', value:67},
	            {name:'江西', value:61},
	            {name:'湖北', value:61},
	            {name:'广西', value:61},
	            {name:'甘肃', value:61},
	            {name:'山西', value:69},
	            {name:'内蒙古', value:64},
	            {name:'陕西', value:71},
	            {name:'吉林', value:63},
	            {name:'福建', value:59},
	            {name:'贵州', value:44},
	            {name:'广东', value:108},
	            {name:'青海', value:71},
	            {name:'西藏', value:62},
	            {name:'四川', value:92},
	            {name:'宁夏', value:76},
	            {name:'海南', value:52},
	            {name:'台湾', value:66},
	            {name:'香港', value:57},
	            {name:'澳门', value:19}*/
	        
	    }]
	});
}
//词云
function echartsHord(data){
	var arrData =[];
	$.each(data,function(index,item){
		arrData.push({
			name:item.key,
			value:item.value
			
		})
		if(index>24){
			return false;
		}
	})
	/*for (var i in data) {
	arrData.push({
			name:i,
			value:data[i]
		})
	    
	}*/
	var _hord = echarts.init($("#echartsHord")[0]);
	_hord.setOption({
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
	        data:arrData
	    }]
	});
}
/*******************/
/*数据分析*/
//水波纹
function echartsWater(data){
	var arrData = [];
	$.each(data,function(index,item){
		var fvalue=1-item.source/item.nums+"";
		result = fvalue.substr(0,fvalue.indexOf(".")+5);
		
		arrData.push({
			name:"总体友好度",
			value:result
		});
	})
	var _water = echarts.init($('#echartsWater')[0]);
	_water.setOption({
		series: [{
	        type: 'liquidFill',
	        center: ['50%', '50%'],
	        radius: '80%',
	        backgroundStyle: {
	            borderColor: '#41A7DC',
	            borderWidth: 1,
	            shadowColor: '#41A7DC',
	            shadowBlur: 20
	        },
	        itemStyle: {
	            normal: {
	                color: '#41A7DC' //
	            }
	        },
	        outline: {
	            show: false
	        },
	        label: {
	            normal: {
	                color: "#41A7DC", //transprant
	                show: false,
	                fontSize: 24,
	                formatter: function(param) {
	                   return param.value * 100 + '%' + '\n' + '总体好友度'
	                }
	            }
	        },
	        data: arrData
	        /*[{
	            name: "总体好友度",
	            value: 0.52
	        }]*/
	    }]
	})
}
//雷达图
function echartsRadar(data){
	var title = [];
	var arrData = [];
	var fvalue=[];
	
	var max=0;
	
	$.each(data,function(index,item){
		var n=parseInt(item.nums);
		max+=n;
	})
		
	
	$.each(data,function(index,item){
		
		
		
		fvalue.push(item.nums);
		
		
		title.push({
			name:item.source,
			max:max
		})
		/*arrData.push({
			itemStyle: {
	            normal: {
	                areaStyle: {
	                    type: 'default',
	                    background: "blue" // 图表中各个图区域的颜色
	                }
	            }
	        },
			name:'分布',
			value:fvalue //数组
		});
		title.push({
			name:item.source,
			max:500
		})*/
	})
		arrData.push({
			itemStyle: {
	            normal: {
	                areaStyle: {
	                    type: 'default',
	                    background: "blue" // 图表中各个图区域的颜色
	                }
	            }
	        },
			name:'分布',
			value:fvalue //数组
		});
	/*console.log(max);
	title.push({
		name:name,
		max:max
	})*/
	var _radar = echarts.init($("#echartsRadar")[0]);
	_radar.setOption({
	    tooltip: {
	    	trigger: 'axis'
	    },
	    legend: {
	        show:false
	    },
	    radar: {
	        // shape: 'circle', //圆形雷达
	        name: {
	            textStyle: {
	                color: '#333',
	                borderRadius: 3
	           }
	        },
	        indicator: title,
	        /*[
	           { name: '机锋网', max: 6500},
	           { name: '管理', max: 16000},
	           { name: '信息技术', max: 30000},
	           { name: '客服', max: 38000},
	           { name: '研发', max: 52000}
	        ],*/
            radius: 100
	    },
	    series: [
		    {
		        type: 'radar',
		        symbol: "none",//去掉拐点
	            tooltip: {
	                trigger: 'item'
	            },
            	itemStyle: {
            		normal: {
            			areaStyle: {
            				type: 'default',
            				color: 'rgba(55,162,218,.7)'
            			},
            			lineStyle: {
                            color:"rgba(55,162,218,.7)"
                        },
            		}
            	},
		        data : arrData
		        /*[
		             {
		                itemStyle: {
                            normal: {
                                areaStyle: {
                                    type: 'default',
                                    background: "blue" // 图表中各个图区域的颜色
                                }
                            }
                        },
		                value : [5000, 14000, 28000, 31000, 42000],
		                name : '分布'
		            }
		        ]*/
		    }
	    ]
	})
}
//圆环图
function echartsPie(data){
	var arrData = [];
	$.each(data,function(index,item){
		arrData.push({
			name:item.source,
			value:item.nums
		})
	})
	var _pie = echarts.init($("#echartsPie")[0]);
	_pie.setOption({
		tooltip: {
	        trigger: 'item',
	        formatter: "{b} <br/>{c} ({d}%)"
	    },
	    legend: {
	        show:false
	    },
	    color: [ 
		    '#37A2DA', '#ff7f50', '#da70d6', '#32cd32', '#6495ed', 
		    '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
		    '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
		    '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
		],
	    series: [
	        {
	            name:'',
	            type:'pie',
	            radius: ['50%', '70%'],
	            labelLine: {
	                normal: {
	                    length:3
	                }
	            },
	            data: arrData
	        }
	    ]
	})
}
//评价 
function echartsPJ(data){
	var title = [];
	var arrData = [];
	var arrh=[];//每次好评的数量
	var arrz=[];//每次中评的数量
	var arrc=[];//每次差评的数量
	var n=0;
	//把标题统计出来
	$.each(data,function(index,item){
		if("好评"==item.source){
			n++;
		}
	})
	$.each(data,function(index,item){
		title.push(item.comment);
		if((index+1)==n){
			return false;
		}
	})
	//每次好中差的数量
	for(var i=0;i<title.length;i++){
		$.each(data,function(index,item){
			if(title[i]==item.comment && item.source=='好评'){
				arrh.push(item.nums);
			}else if(title[i]==item.comment && item.source=='中评'){
				arrz.push(item.nums);
			}else{
				arrc.push(item.nums);
			}
		})
		
	}

	
	/*$.each(data,function(index,item){
		arrData.push({
			type:'bar',
            stack: '类型',
            barWidth : 30,
			itemStyle: {normal: {areaStyle: {type: 'default'}}},
			name:item.source,
			value:index.nums
		});
		
	})*/
	var _pj = echarts.init($("#echartsPJ")[0]);
	_pj.setOption({
		tooltip : {
	        trigger: 'axis',
	        axisPointer: {  
	            type: 'shadow',//阴影
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    legend: {
	        show:false
	    },
	    color: [ 
		    '#FB7293', '#FF9F7F', '#32C5E9'
		],
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            splitLine:{show: false},//去除网格线
	            data : title
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            splitLine:{show: false},//去除网格线
	            splitArea : {show : true},//保留网格区域
	            axisLabel : {
	                formatter: '{value}',
	                textStyle: {
	                    color: '#888'
	                }
	            }
	        }
	    ],
	    series : //arrData
	    [
	        {
	            name:'好评',
	            type:'bar',
	            barWidth : 30,
	            stack: '类型',
	            data:arrh
	        },
	        {
	            name:'中评',
	            type:'bar',
	            stack: '类型',
	            data:arrz
	        },
	        {
	            name:'差评',
	            type:'bar',
	            stack: '类型',
	            data:arrc
	        }
	    ]
	})
}
function echartsLine(data){
	var arrData = [];
	var title = [];
	$.each(data,function(index,item){
		arrData.push(index.fvalue);
		title.push(index.name);
	})
	var _line = echarts.init($("#echartsLine")[0]);
	_line.setOption({
		title: {
	        subtext: '声量走势'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        show: false
	    },
	    toolbox: {
	        show: false
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false, 
	        axisTick: {// 去除x轴上的刻度线
	            show: false
	        }, 
	        data: title//['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
	            formatter: '{value}'
	        },   
	        axisLine: {show: false},//控制y轴线是否显示
	        splitLine: {// 控制网格线是否显示
	            show: true
	        }, 
	        axisTick: {// 去除y轴上的刻度线
	            show: false
	        } 
	    },
	    series: [
	        {
	            name:'',
	            type:'line',
	            smooth:0.3,
	            color:["#00A3D8"],
	            symbol:'none',
	            data: arrData,//[1, 8, 15, 13, 5, 13, 10]
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'}
	                ]
	            }
	        }
	    ]
	})
}
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
function yesterday(){
	var day = new Date();
    day.setTime(day.getTime()-24*60*60*1000);
    var s1 = day.getFullYear()+"-" + (day.getMonth()+1) + "-" + day.getDate();
    $("#dateTime").text(s1);
}	










