package com.wubin.test.client.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

public class TestCxf {

    public static void getMobileCodeInfo() throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");

//        QName qName = new QName("http://WebXml.com.cn/", "getMobileCodeInfo");
//        Object[] result = client.invoke(qName, "18368527925", "");

        Object[] result = client.invoke("getMobileCodeInfo", "18368527925", "");

        System.out.println(result[0]);
    }

    // 报错
    public static void getWeatherbyCityName() throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl");

//        QName qName = new QName("http://WebXml.com.cn/", "getWeatherbyCityName");
//        Object[] result = client.invoke(qName, "绍兴");

        Object[] result = client.invoke("getWeatherbyCityName", "绍兴");

        System.out.println(result[0]);
    }

    public static void main(String[] args) throws Exception {
        getMobileCodeInfo();
//        getWeatherbyCityName();
    }

}
