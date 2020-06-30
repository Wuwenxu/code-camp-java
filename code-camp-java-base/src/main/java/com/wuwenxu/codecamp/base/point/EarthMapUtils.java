package com.wuwenxu.codecamp.base.point;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EarthMapUtils {
    /*  根据地址  批量  获取经纬度  方法1*/

    public static Map getAreaLongAndDimen(String addr) {

        try {
            addr = new String(addr.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String str = "http://restapi.amap.com/v3/geocode/geo?address=" + addr + "&output=JSON&key=ea8eda10550645fd8e71780951be1e83";
        HashMap param = new HashMap();
        param.put("info", "erro");
        InputStream inputStream = null;
        try {
            URL url = new URL(str);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5 * 1000);//超时时间
            urlConnection.setRequestProperty("contentType", "utf-8");//字符集
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            JsonNode jsonNode = new ObjectMapper().readTree(inputStream);//jackson
            if (StringUtils.equals(jsonNode.findValue("status").textValue(), "1") && jsonNode.findValue("geocodes").size() > 0) {
             String[] degree = jsonNode.findValue("geocodes").findValue("location").textValue().split(",");
             param.put("longitude", degree[0]);
             param.put("dimension", degree[1]);
             param.put("info", "success");
             }
        } catch (MalformedURLException e) {
            param.put("info", "erro");
        } catch (IOException e) {
            param.put("info", "erro");
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return param;
    }

    /*  根据地址获取经纬度  方法二*/

    private static Pattern pattern= Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");
    public static double[] addressToGPS(String address) {
        try {
            String url = "http://restapi.amap.com/v3/geocode/geo?address="+address+"&output=JSON&key=031a144c48ecc34528550ce2e0c49cf2";
            URL myURL = null;
            URLConnection httpsConn = null;
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStreamReader insr = null;
            BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = "";
                String line = null;
                while((line= br.readLine())!=null){
                    data+=line;
                }
                Matcher matcher = pattern.matcher(data);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    return gps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static void main(String[] args) {
        String dest = "吉林省长春市九台区 胡家回族乡";
        if (dest != null) {
            //Pattern p = Pattern.compile("\\s*|\t|\r|\n|\\x20");
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(dest);
            dest = m.replaceAll("");
        }
        System.out.println("dest"+dest);
        double [] data = addressToGPS(dest);
        System.out.println("经度:"+data[0]);
        System.out.println("纬度:"+data[1]);
    }

}
