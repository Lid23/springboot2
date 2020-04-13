package com.noodles.http.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.noodles.json.utils.JsonUtils;
import com.noodles.log.MySlf4j;

/**
 * @filename HttpClientUtils
 * @description Spring Httpclient工具类
 * @autor noodles
 * @date 2019/7/23 9:04
 */
public class HttpClientUtils {

	/**编码格式。发送编码格式统一用UTF-8*/
	private static final String ENCODING = "UTF-8";

	/**设置连接超时时间，单位毫秒。*/
	private static final int CONNECT_TIMEOUT = 30000;

	/**请求获取数据的超时时间(即响应时间)，单位毫秒。*/
	private static final int SOCKET_TIMEOUT = 30000;

	/**
	 * 私有构造器
	 * @return
	 * @author noodles
	 * @date 2019/7/22 18:44
	 */
	private HttpClientUtils() {

	}

	/**
	 * Httpclient post请求
	 * @param url 请求地址
	 * @param map 请求参数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/22 18:47
	 */
	public static String doPost(final String url, final Map<String, String> map) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient实例
			httpClient = generateSSLInsecureClient();
			//2.创建Http对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			generateHttpHeader(httpPost);
			//4.封装请求参数
			if (null != map) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient post请求
	 * @param url 请求地址
	 * @param map 请求参数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/22 18:47
	 */
	public static String doRawPost(final String url, final Map<String, String> map) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient实例
			httpClient = generateSSLInsecureClient();
			//2.创建Http对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			generateRawHttpHeader(httpPost);
			//4.封装请求参数
			if (null != map) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient post 发送json请求
	 * @param url 请求地址
	 * @param data 请求参数
	 * @return 返回的数据
	 * @author 刘飞华
	 * @date 2019/7/23 16:19
	 */
	public static String doPost(final String url, final String data) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			//2.创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			generateHttpHeader(httpPost);
			//4.设置JSON格式的参数
			if (null != data) {
				StringEntity requestEntity = new StringEntity(data, ENCODING);
				httpPost.setEntity(requestEntity);
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient post 发送json请求
	 * @param url 请求地址
	 * @param data 请求参数
	 * @param headers 请求header
	 * @return 返回的数据
	 * @author noodles
	 * @date 2019/8/6 15:31
	 */
	public static String doPost(final String url, final String data, Map<String, String> headers) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			//2.创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			headers.entrySet().forEach(item -> httpPost.addHeader(item.getKey(), item.getValue()));
			//4.设置JSON格式的参数
			if (null != data) {
				StringEntity requestEntity = new StringEntity(data, ENCODING);
				httpPost.setEntity(requestEntity);
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient post 发送json请求
	 * @param url 请求地址
	 * @param data 请求参数
	 * @param headers 请求header
	 * @return 返回的数据[包含header状态及body]
	 * @author noodles
	 * @date 2019/8/6 15:31
	 */
	public static Map<String, String> doUniquePost(final String url, final String data, Map<String, String> headers) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			//2.创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			headers.entrySet().forEach(item -> httpPost.addHeader(item.getKey(), item.getValue()));
			//4.设置JSON格式的参数
			if (null != data) {
				StringEntity requestEntity = new StringEntity(data, ENCODING);
				httpPost.setEntity(requestEntity);
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			Integer statusCode = httpResponse.getStatusLine().getStatusCode();
			String bodyStr = generateHttpResponse(httpResponse);
			Map<String, String> map = new HashMap<>();
			map.put("code", statusCode.toString());
			map.put("message", bodyStr);
			return map;
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient post 发送json请求
	 * @param url 请求地址
	 * @param map 请求参数
	 * @param headers 请求header
	 * @return 返回的数据
	 * @author noodles
	 * @date 2019/8/6 15:31
	 */
	public static String doPost(final String url, final Map<String, String> map, Map<String, String> headers) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			// 1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			// 2.创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			// 3.设置头部信息
			if (null != headers) {
				headers.entrySet().forEach(item -> httpPost.addHeader(item.getKey(), item.getValue()));
			}
			// 4.设置请求参数
			if (null != map) {
				List<NameValuePair> nvps = new ArrayList<>();
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				new UrlEncodedFormEntity(nvps, ENCODING);
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
			}
			// 5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient get请求
	 * @param url 请求地址
	 * @param map 请求参数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/22 19:18
	 */
	public static String doGet(final String url, final Map<String, String> map) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();

			//2.创建访问的地址
			URIBuilder uriBuilder = new URIBuilder(url);
			if (null != map) {
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					uriBuilder.setParameter(entry.getKey(), entry.getValue());
				}
			}
			//3.创建Http对象
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			//4.设置头部信息
			generateHttpHeader(httpGet);
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient get请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient get请求
	 * @param url        请求地址
	 * @param map        请求参数
	 * @param headers    请求头部
	 * @return java.lang.String
	 * @author朱愈曌
	 * @date 2019/8/30 11:27
	 */
	public static String doGet(final String url, final Map<String, String> map, Map<String, String> headers) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();

			//2.创建访问的地址
			URIBuilder uriBuilder = new URIBuilder(url);
			if (null != map) {
				Set<Map.Entry<String, String>> entrySet = map.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					uriBuilder.setParameter(entry.getKey(), entry.getValue());
				}
			}
			//3.创建Http对象
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			//4.设置头部信息
			if (headers != null) {
				headers.entrySet().forEach(item -> httpGet.addHeader(item.getKey(), item.getValue()));
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient get请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * Httpclient压缩算法 post请求
	 * @param url 请求地址
	 * @param data 请求参数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/23 10:00
	 */
	public static String doCompressPost(final String url, final String data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			//2.创建Http对象
			HttpPost httpPost = new HttpPost(url);
			//3.设置头部信息
			generateHttpHeader(httpPost);
			//4.对请求参数进行GZIP压缩
			if (null != data) {
				ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
				originalContent.write(data.getBytes(Charset.forName(ENCODING)));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
				originalContent.writeTo(gzipOut);
				gzipOut.finish();
				httpPost.setEntity(new ByteArrayEntity(baos.toByteArray()));
			}
			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient压缩算法 post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * 封装请求头
	 * @param httpMethod
	 * @return void
	 * @author noodles
	 * @date 2019/7/24 9:03
	 */
	private static void generateHttpHeader(HttpRequestBase httpMethod) {
		//1.设置头部信息
		httpMethod.setHeader("Accept", "application/json");
		httpMethod.setHeader("Accpet-Encoding", "UTF-8");
		httpMethod.setHeader("Content-Encoding", "gzip");
		httpMethod.setHeader("Content-Type", "application/json; charset=UTF-8");
		//2.设置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setSocketTimeout(SOCKET_TIMEOUT).build();
		httpMethod.setConfig(requestConfig);
	}

	/**
	 * 封装请求头
	 * @param httpMethod
	 * @return void
	 * @author noodles
	 * @date 2019/7/24 9:03
	 */
	private static void generateRawHttpHeader(HttpRequestBase httpMethod) {
		//2.设置超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setSocketTimeout(SOCKET_TIMEOUT).build();
		httpMethod.setConfig(requestConfig);
	}

	/**
	 * 封装统一返回
	 * @param httpResponse 返回
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/6 15:22
	 */
	private static String generateHttpResponse(CloseableHttpResponse httpResponse) throws IOException {
		if (httpResponse == null) {
			return null;
		}
		HttpEntity entity = null;
		entity = httpResponse.getEntity();
		if (200 != httpResponse.getStatusLine().getStatusCode()) {
			MySlf4j.textError("httpResponse响应异常,返回码:{0},异常信息:{1}", httpResponse.getStatusLine().getStatusCode(),
					JsonUtils.toJson(httpResponse));
			return null;
		}
		String res = EntityUtils.toString(entity, ENCODING);
		httpResponse.close();
		return res;
	}

	/**
	 * 封装https支持
	 * @return org.apache.http.impl.client.CloseableHttpClient
	 * @author noodles
	 * @date 2019/8/8 9:36
	 */
	public static CloseableHttpClient generateSSLInsecureClient() {
		CloseableHttpClient httpclient = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			//不进行主机名验证
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),
					NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", new PlainConnectionSocketFactory()).register("https", sslConnectionSocketFactory)
					.build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(SOCKET_TIMEOUT);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
					.setDefaultCookieStore(new BasicCookieStore()).setConnectionManager(cm).build();
		} catch (Exception e) {
			MySlf4j.textError("httpClient异常:{0}", MySlf4j.ExceptionToString(e));
		}
		return httpclient;
	}

	/**
	 * 功能描述 post 发送文本及文件参数的请求
	 * @param url 请求地址
	 * @param parame 请求文本参数
	 * @param fileParame 请求文件参数
	 * @param headers 请求header
	 * @param parameMimeType 请求文本参数的mimeType
	 * @param charset 请求文本参数的编码
	 * @param fileMimeType 请求文件参数的mimeType
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/11/1 17:09
	 */
	public static String doPost(final String url, final Map<String, String> parame, Map<String, File> fileParame,
			Map<String, String> headers, String parameMimeType, String charset, String fileMimeType) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			// 1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();
			// 2.创建httpPost对象
			HttpPost httpPost = new HttpPost(url);
			// 3.设置头部信息
			if (null != headers) {
				headers.entrySet().forEach(item -> httpPost.addHeader(item.getKey(), item.getValue()));
			}
			ContentType contentType = ContentType.create(parameMimeType, Charset.forName(charset));
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			if (fileParame != null) {
				Set<Map.Entry<String, File>> entrySet = fileParame.entrySet();
				for (Map.Entry<String, File> entry : entrySet) {
					multipartEntityBuilder
							.addBinaryBody(entry.getKey(), entry.getValue(), ContentType.create(fileMimeType),
									entry.getValue().getName());
				}
			}
			if (parame != null) {
				Set<Map.Entry<String, String>> entrySet = parame.entrySet();
				for (Map.Entry<String, String> entry : entrySet) {
					multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
				}
			}
			HttpEntity httpEntity = multipartEntityBuilder.build();
			httpPost.setEntity(httpEntity);
			// 5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient post请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

	/**
	 * 功能描述 post 发送文本及文件参数的请求
	 * @param url 请求地址
	 * @param parame 请求文本参数
	 * @param fileParame 请求文件参数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/11/1 17:09
	 */
	public static String doPostFile(final String url, final Map<String, String> parame, Map<String, File> fileParame) {
		return doPost(url, parame, fileParame, null, "multipart/form-data", "UTF-8", "application/octet-stream");
	}

	/**
	 * Httpclient get请求
	 * @param url 请求地址
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/22 19:18
	 */
	public static String doGet(final String url) {
		CloseableHttpClient httpClient = null;
		try {
			if (null == url) {
				return null;
			}
			//1.创建HttpClient对象
			httpClient = generateSSLInsecureClient();

			//2.创建访问的地址
			URIBuilder uriBuilder = new URIBuilder(url);

			//3.创建Http对象
			HttpGet httpGet = new HttpGet(uriBuilder.build());

			//4.设置头部信息
			generateHttpHeader(httpGet);

			//5.执行请求并获取返回
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			return generateHttpResponse(httpResponse);
		} catch (Exception ex) {
			MySlf4j.textError("Httpclient get请求异常,异常信息:{0}", MySlf4j.ExceptionToString(ex));
		}
		return null;
	}

}
