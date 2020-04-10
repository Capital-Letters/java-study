package com.imooc.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MukeHandler extends DefaultHandler {

	//定义一个变量来保存当前正在处理的tag
	private String currentTag;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("解析文档开始");
	}

	//解析文档结束时触发该方法
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("解析文档结束");
	}

	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("开始处理元素"+qName);
		currentTag = qName;
		if(attributes.getLength()>0) {
			System.out.println("<"+currentTag+">元素属性如下：");
			for(int i=0;i<attributes.getLength();i++) {
				System.out.println(attributes.getQName(i)+"-->"+attributes.getValue(i));
			}
		}
	}

	
	//解析元素结束时触发该方法
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("处理元素结束"+qName);
	}
	
	
	//每当处理文本数据时触发该方法
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		String content = new String(ch,start,length);
		if(content.trim().length()>0) {
			System.out.println("<"+currentTag+">"+content.trim());
		}
		
	}
	
}
