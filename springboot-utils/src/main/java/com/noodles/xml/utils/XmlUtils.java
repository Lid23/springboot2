package com.noodles.xml.utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @filename XmlUtils
 * @description XML工具类
 * @autor noodles
 * @date 2019/12/16 11:11
 */
@XmlRootElement
public class XmlUtils {

	/**
	 * 格式化为XML
	 * @param obj
	 * @param charset 编码格式[GBK,UTF-8]
	 * @return
	 * 作者：noodles
	 * 日期：2017年1月16日上午11:21:49
	 */
	public static String convertToXml(Object obj, String charset) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 注意jdk版本
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory
					.createXMLStreamWriter(baos, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
			xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
			marshaller.marshal(obj, xmlStreamWriter);
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.close();
			return baos.toString(charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件名：XmlUtil.java
	 * 描述：内部类监听bean的null值
	 * 作者：kj00047
	 * 日期：2018年10月15日下午6:22:51
	 */
	class MarshallerListener extends Marshaller.Listener {
		public static final String BLANK_CHAR = "";

		@Override
		public void beforeMarshal(Object source) {
			super.beforeMarshal(source);
			Field[] fields = source.getClass().getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				try {
					if (f.getType() == String.class && f.get(source) == null) {
						f.set(source, BLANK_CHAR);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 格式化为XML(null替换为空字符串)
	 * @param obj
	 * @param charset 编码格式[GBK,UTF-8]
	 * @return
	 * 作者：noodles
	 * 日期：2017年1月16日上午11:21:49
	 */
	public static String convertToXmlWithEmpty(Object obj, String charset) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

			XmlUtils.MarshallerListener marlistener = new XmlUtils().new MarshallerListener();
			marshaller.setListener(marlistener);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 注意jdk版本
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory
					.createXMLStreamWriter(baos, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
			xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
			marshaller.marshal(obj, xmlStreamWriter);
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.close();
			return baos.toString(charset);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * XML转换为JavaBean
	 * @param xml xml报文
	 * @param c 实体对象
	 * @return
	 * 作者：noodles
	 * 日期：2017年1月16日上午11:23:00
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			SAXParserFactory sax = SAXParserFactory.newInstance();
			// 忽略xml报文命名空间(xmlns)
			sax.setNamespaceAware(false);
			XMLReader xmlReader = sax.newSAXParser().getXMLReader();
			Source source = new SAXSource(xmlReader, new InputSource(reader));
			t = (T) unmarshaller.unmarshal(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

}
