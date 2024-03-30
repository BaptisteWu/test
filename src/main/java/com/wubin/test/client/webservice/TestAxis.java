package com.wubin.test.client.webservice;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Vector;

public class TestAxis {

    public static void getMobileCodeInfo() throws ServiceException, MalformedURLException, RemoteException {
        Service service = new Service();
        Call call = (Call) service.createCall();

        String endpoint = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
//        String endpoint = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
        call.setTargetEndpointAddress(new URL(endpoint));

        String namespaceURI = "http://WebXml.com.cn/";
        String operationName = "getMobileCodeInfo";
        call.setOperationName(new QName(namespaceURI, operationName));

        call.addParameter(new QName(namespaceURI, "mobileCode"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName(namespaceURI, "userID"), XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);

        call.setSOAPActionURI(namespaceURI + operationName);

        String result = (String) call.invoke(new Object[]{"18368527925", ""});
        System.out.println(result);
    }

    public static void getWeatherbyCityName() throws ServiceException, MalformedURLException, RemoteException {
        Service service = new Service();
        Call call = (Call) service.createCall();

        String endpoint = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
//            String endpoint = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
        call.setTargetEndpointAddress(new URL(endpoint));

        String namespaceURI = "http://WebXml.com.cn/";
        String operationName = "getWeatherbyCityName";
        call.setOperationName(new QName(namespaceURI, operationName));

        call.addParameter(new QName(namespaceURI, "theCityName"), XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.SOAP_VECTOR);

        call.setSOAPActionURI(namespaceURI + operationName);

        Vector result = (Vector) call.invoke(new Object[]{"绍兴"});

        for (Object str : result) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) throws RemoteException, ServiceException, MalformedURLException {
//        getMobileCodeInfo();
        getWeatherbyCityName();
    }

}
