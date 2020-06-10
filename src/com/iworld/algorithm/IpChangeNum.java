package com.iworld.algorithm;

/**
 * ip地址转换成
 *
 * @Autor iworld
 * @Date 2020-05-28 09:31
 */
public class IpChangeNum {

    public static void main(String[] args) {
        IpChangeNum ipChangeNum = new IpChangeNum();
        int ip = ipChangeNum.ipToInt("198.205.56.55");
        System.out.println("ip==" + Integer.toBinaryString(ip));
    }

    public int ipToInt(String ip){
        String[] ipSplit = ip.split("\\.");
        int rsIp = 0;
        for (int i = 0; i < ipSplit.length; i++) {
            int mvBit = 8 * (ipSplit.length - i - 1);
            int intIp = Integer.parseInt(ipSplit[i]) << mvBit;
            rsIp = rsIp | intIp;
            System.out.println("i==" + i + "==intIp==" + Integer.toBinaryString(intIp) + "==rsIp==" + Integer.toBinaryString(rsIp));
        }
        return rsIp;
    }

    public String intToIp(int ipNum){
        for (int i = 0; i < 4; i++) {

        }
        return null;
    }
}
