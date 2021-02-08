package com.noodles.springbootdemos.datacollect;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.util.CollectionUtils;

import com.noodles.file.utils.FileUtil;
import com.noodles.json.utils.JsonUtils;
import com.noodles.jsoup.JsoupUtils;

/**
 * @filename AmacDataCollectTest
 * @description 证券基金协会从业人员数据收集
 * @author 巫威
 * @date 2021/2/7 14:31
 */

public class AmacDataCollectTest {

	/**
	 * 基金从业人员资格注册信息-私募
	 * @author 巫威
	 * @date 2021/2/7 14:33
	 */
	@Test
	public void getFundOrgInfos() throws Exception {

		long start = System.currentTimeMillis();
		String filePath = System.getProperty("user.dir").concat(File.separator).concat("datacollect")
				.concat(File.separator).concat("私募基金管理人.txt");
		File file = new File(filePath);

		String contentType = "application/json;charset=UTF-8";
		String url = "https://gs.amac.org.cn/";
		double rand = Math.random();
		int size = 10;
		int totalPages = 2413;

		Map<String, Object> dataMap = new HashMap();
		dataMap.put("orgType", "私募基金管理人");
		dataMap.put("page", 1);

		List<String> responseList = new ArrayList<>();
		for (int i = 0; i < totalPages; i++) {
			String requestUrl = url + "amac-infodisc/api/pof/personOrg?rand=" + rand + "&page=" + i + "&size=" + size;
			String data = JsonUtils.toJson(dataMap);
			System.out.println("Request -> " + data);
			String response = JsoupUtils.executeAsString(requestUrl, contentType, JsonUtils.toJson(dataMap));
			System.out.println("Response -> " + response);

			if (StringUtils.isNotBlank(response)) {
				responseList.add(response);
			}
			if (i % 100 == 0 || i == totalPages - 1) {
				FileUtil.appendUtf8Lines(responseList, file);
				responseList.clear();
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("done -> 耗时：" + (end - start) / 1000 + "秒");

	}

	/**
	 * 基金从业人员信息
	 * @author 巫威
	 * @date 2021/2/7 15:26
	 */
	@Test
	public void getFundPersons() throws Exception {

		long start = System.currentTimeMillis();
		String orgFilePath = System.getProperty("user.dir").concat(File.separator).concat("datacollect")
				.concat(File.separator).concat("私募基金管理人-20210207.txt");

		String personFilePath = System.getProperty("user.dir").concat(File.separator).concat("datacollect")
				.concat(File.separator).concat("私募基金人员信息.txt");

		File personFile = new File(personFilePath);

		String contentType = "application/json;charset=UTF-8";
		String url = "https://gs.amac.org.cn/";

		int size = 300;

		Map<String, Object> dataMap = new HashMap();
		dataMap.put("page", 1);

		List<String> orgInfoListStrs = Files.readAllLines(Paths.get(orgFilePath), Charsets.UTF_8);
		for (String orgInfoListStr : orgInfoListStrs) {

			OrgInfoList orgInfoList = JsonUtils.fromJson(orgInfoListStr, OrgInfoList.class);
			if (CollectionUtils.isEmpty(orgInfoList.getContent())) {
				continue;
			}

			for (OrgInfo orgInfo : orgInfoList.getContent()) {
				double rand = Math.random();
				String requestUrl = url + "amac-infodisc/api/pof/person?rand=" + rand + "&page=" + 0 + "&size=" + size;
				dataMap.put("userId", orgInfo.getUserId());
				String data = JsonUtils.toJson(dataMap);
				System.out.println("request -> " + data);
				String response = JsoupUtils.executeAsString(requestUrl, contentType, data);
				//System.out.println("response -> " + response);
				if (StringUtils.isNotBlank(response) && response.contains("A0380621010001")) {
					List<String> responseList = new ArrayList<>();
					responseList.add(data);
					responseList.add(response);
					FileUtil.appendUtf8Lines(responseList, personFile);
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("done -> 耗时：" + (end - start) / 1000 + "秒");

	}

	class OrgInfoList implements Serializable {

		private static final long serialVersionUID = -3268520195483837445L;
		private List<OrgInfo> content;

		public List<OrgInfo> getContent() {
			return content;
		}

		public void setContent(List<OrgInfo> content) {
			this.content = content;
		}
	}

	class OrgInfo implements Serializable {

		private static final long serialVersionUID = 6573310231098920303L;

		private String userId;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
	}
}
