package com.chengdai.cddata.widget.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by 李先俊 on 2017/7/27.
 */

public class SystemUtils {


    /**
     * 获取MAC地址
     * @param mContext
     * @return
     */
    public static String getMacAddress(Context mContext) {
        String macStr = "";
        WifiManager wifiManager = (WifiManager) mContext
                .getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getMacAddress() != null) {
            macStr = wifiInfo.getMacAddress();// MAC地址
        }

        return macStr;
    }

    /**
     * 获取WifiInfo
     * @param mContext
     * @return
     */
    public static WifiInfo getWifiInfo(Context mContext){
        WifiManager mWifiManager = (WifiManager)mContext.getSystemService(WIFI_SERVICE);
        WifiInfo info = mWifiManager.getConnectionInfo();
        return info;
    }

    /**
     * 获取设备编号
     */
    public synchronized static String getDeviceId() {

          String uniqueID;
          uniqueID = getUniquePsuedoID();
            if (TextUtils.isEmpty(uniqueID)) {     // 如果上面获取不到值，那么使用第二种方法获取唯一编号
                uniqueID = UUID.randomUUID().toString();   //第一种方法获取设备编号
                if (TextUtils.isEmpty(uniqueID)) {     // 如果上面获取不到值，那么直接获取当前时间作为唯一编号
                    uniqueID = (int) (Math.random() * 100) + "-" + System.currentTimeMillis();
                }
            }

        return uniqueID;
    }

    public static String getUniquePsuedoID() {

        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();


            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {

            serial = "serial";
        }

        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * gps获取ip
     * @return
     */
    public static String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (Exception ex) {

        }
        return null;
    }

    /**
     * wifi获取ip
     * @param context
     * @return
     */
    public static String getIp(Context context){
        try {
            //获取wifi服务
            WifiManager wifiManager = (WifiManager)context. getSystemService(Context.WIFI_SERVICE);
            //判断wifi是否开启
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = intToIp(ipAddress);
            return ip;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 格式化ip地址（192.168.11.1）
     * @param i
     * @return
     */
    private static String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
    /**
     *  3G/4g网络IP
     */
    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取本机的ip地址（3中方法都包括）
     * @param context
     * @return
     */
    public static String getOnlyIpAdress(Context context){
        String ip = null;
        try {
            ip=getIp(context);
            if (ip==null){
                ip = getIpAddress();
                LogUtil.E("ip 1");
                if (ip==null){
                    LogUtil.E("ip 2");
                    ip = getLocalIpAddress();
                }
            }
        } catch (Exception e) {

        }

        return ip;
    }
    public static String getIMEI(Context con) {
        TelephonyManager tm = (TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : "";
    }

    /*判断当前版本是不是大于version*/
    public static Boolean getAndroidVersion(int version) {
        if (Build.VERSION.SDK_INT >= version) {
            return true;

        } else {
            return false;
        }
    }


    public static String getIPAddress(final boolean useIPv4) {
        try {
            for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements(); ) {
                NetworkInterface ni = nis.nextElement();
                // 防止小米手机返回10.0.2.15
                if (!ni.isUp()) continue;
                for (Enumeration<InetAddress> addresses = ni.getInetAddresses(); addresses.hasMoreElements(); ) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean isIPv4 = hostAddress.indexOf(':') < 0;
                        if (useIPv4) {
                            if (isIPv4){
                                if(StringUtils.isIP(hostAddress)){
                                    return hostAddress;
                                }else{
                                    return "";
                                }
                            };
                        } else {
                            if (!isIPv4) {
                                int index = hostAddress.indexOf('%');

                                String ips=index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
                                if(StringUtils.isIP(ips)){
                                    return ips;
                                }else{
                                    return "";
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

}
