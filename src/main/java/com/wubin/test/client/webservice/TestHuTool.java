package com.wubin.test.client.webservice;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.webservice.SoapClient;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;

public class TestHuTool {

    public static void getMobileCodeInfo() {
        SoapClient client = SoapClient.create("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx")
                .setMethod("getMobileCodeInfo", "http://WebXml.com.cn/")
                .setParam("mobileCode", "18368527925")
                .setParam("userID", "");
        String result = client.send(true);
        System.out.println(result);
        System.out.println("---------------------------");
        Document document = XmlUtil.readXML(result);
        String value = (String) XmlUtil.getByXPath("//soap:Body", document, XPathConstants.STRING);
        System.out.println(value.trim());
    }

    public static void getWeatherbyCityName() {
        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx")
                .setMethod("getWeatherbyCityName", "http://WebXml.com.cn/")
                .setParam("theCityName", "绍兴");
        String result = client.send(true);
        System.out.println(result);
        System.out.println("---------------------------");
        Document document = XmlUtil.readXML(result);
        String value = (String) XmlUtil.getByXPath("//soap:Body", document, XPathConstants.STRING);
        System.out.println(value.trim().replaceAll(" ", ""));
    }

    public static void main(String[] args) {
//        getMobileCodeInfo();
        getWeatherbyCityName();
    }

}
