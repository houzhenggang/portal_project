package com.qdch.portal.littleproject.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.littleproject.dao.DaoRuModelDao;
/**
 * 数据导入（已停用，需要请打开下面地址）
 * @author gaozhao
 *
 */
@Controller
public class DaoRuShuJu extends BaseController {
	@Autowired
	private DaoRuModelDao daoRuModelDao;

	//@RequestMapping(value = { "${portalPath}/littleproject/daoru" })
	@ResponseBody
	public String daoru(HttpServletRequest request, HttpServletResponse response) {
		BufferedReader br = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {

			String pathname = "F:\\areas.txt";
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(filename)); // 建立一个输入流对象reader
			br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line ="";

			while (line != null) {
				// 一次读入一行数据
				line = br.readLine();
				if (line != null) {// 防止最后一次把null加入到stringBuffer中
					stringBuffer.append(line);
				}

			}

			String s = stringBuffer.toString();

			JSONObject list00 = JSONObject.parseObject(s);

			Map<String, Object> map1 = list00;

			// 第一遍可以获取到爷爷辈的id
			for (Entry<String, Object> vo1 : map1.entrySet()) {

				String pname = null;
				// 得到爷爷辈id
				String parentids = vo1.getKey();

				String valueString1 = vo1.getValue().toString();

				JSONObject list11 = JSONObject.parseObject(valueString1);
				Map<String, Object> map2 = list11;

				// 第二遍
				for (Entry<String, Object> vo2 : map2.entrySet()) {
					// 获取当前名字
					String key0 = vo2.getKey();
					if ("name".equals(key0)) {
						pname = vo2.getValue().toString();
					}
					String valueString12 = vo2.getValue().toString();
					if ("child".equals(key0)) {
						JSONObject list12 = JSONObject
								.parseObject(valueString12);
						Map<String, Object> map3 = list12;
						for (Entry<String, Object> vo3 : map3.entrySet()) {
							// 得到父辈id
							String parentid = vo3.getKey();
							String pname2 = null;
							String valueString13 = vo3.getValue().toString();

							JSONObject list13 = JSONObject
									.parseObject(valueString13);
							Map<String, Object> map4 = list13;
							for (Entry<String, Object> vo4 : map4.entrySet()) {
								String key4 = vo4.getKey();
								if ("name".equals(key4)) {
									pname2 = vo4.getValue().toString();
								}

								String valueString14 = vo4.getValue()
										.toString();

								if ("child".equals(key4)) {
									JSONObject list14 = JSONObject
											.parseObject(valueString14);
									Map<String, Object> map5 = list14;
									for (Entry<String, Object> vo5 : map5
											.entrySet()) {
										String id = vo5.getKey();
										String name1 = vo5.getValue()
												.toString();
										daoRuModelDao.daoru(id, parentid,
												parentids + "," + parentid,
												name1);
									}
								}

							}
							daoRuModelDao.daoru(parentid, parentids, parentids,
									pname2);
						}
					}

				}

				daoRuModelDao.daoru(parentids, "0", "0", pname);
			}

			return this.resultSuccessData(request, response, "", "1");

		} catch (Exception e) {
			e.printStackTrace();
			return this.resultSuccessData(request, response, "", "2");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this.resultSuccessData(request, response, "", "3");
			}
		}
	}
}
