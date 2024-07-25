package com.iworld.algorithm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/3/20 19:56
 */
public class GeneraterSql {
    
    public static void main(String[] args) throws IOException {
        creatUpdateSql();
        creatRollBackSql();
    }
    /**
     *
     * CREATE TABLE backup_tables.t_mktmis_newsys_coupon_20240321_1 LIKE t_mktmis_newsys_coupon;
     *
     * INSERT INTO backup_tables.t_mktmis_newsys_coupon_20240321_1 SELECT
     * 	*
     * FROM
     * 	t_mktmis_newsys_coupon
     * where
     * 	newsys_coupon_rule_id = 1781 and useful_life_start = '2023-06-28 00:00:00' and status = 1 limit 100000;
     *
     * UPDATE t_mktmis_newsys_coupon a
     * INNER JOIN backup_tables.t_mktmis_newsys_coupon_20240321_1 b
     * ON a.id=b.id
     * SET a.useful_life_start = "2023-07-01 00:00:00",
     * 	a.modify_time = now()
     * WHERE
     * 	1 = 1;
     */
    private static void creatUpdateSql() throws IOException {
        Map<String, Integer> map = new TreeMap<>();
        map.put("1781", 36);
        map.put("1782", 9);
        map.put("1783", 4);
        map.put("1784", 3);
        map.put("1785", 1);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            for (int j = 1; j <= entry.getValue(); j++) {
                stringBuilder.append("CREATE TABLE backup_tables.t_mktmis_newsys_coupon_20240321_").append(i).append(" ").append("LIKE").append(" t_mktmis_newsys_coupon;");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                stringBuilder.append("INSERT INTO backup_tables.t_mktmis_newsys_coupon_20240321_").append(i).append(" ").append("SELECT");
                stringBuilder.append("\n\r");
                stringBuilder.append(" *");
                stringBuilder.append("\n\r");
                stringBuilder.append("FROM");
                stringBuilder.append("\n\r");
                stringBuilder.append(" t_mktmis_newsys_coupon");
                stringBuilder.append("\n\r");
                stringBuilder.append("where");
                stringBuilder.append(" newsys_coupon_rule_id = ").append(entry.getKey()).append(" and useful_life_start = '2023-06-28 00:00:00' and status = 1 limit 100000;");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                stringBuilder.append("UPDATE t_mktmis_newsys_coupon a");
                stringBuilder.append("\n\r");
                stringBuilder.append("INNER JOIN backup_tables.t_mktmis_newsys_coupon_20240321_").append(i).append(" b");
                stringBuilder.append("\n\r");
                stringBuilder.append("ON a.id=b.id");
                stringBuilder.append("\n\r");
                stringBuilder.append("SET a.useful_life_start = \"2023-07-01 00:00:00\",");
                stringBuilder.append(" a.modify_time = now()");
                stringBuilder.append("\n\r");
                stringBuilder.append("WHERE 1 = 1;");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                i++;
            }
        }
        File file = new File("D:\\doc\\生产工单\\carmktmis\\sql\\20240321-chinaMobileStartDateUpdate\\1_carmkt.sql");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        bufferedOutputStream.write(stringBuilder.toString().getBytes());
    }
    
    /**
     * UPDATE t_mktmis_newsys_coupon a
     * INNER JOIN
     * backup_tables.t_mktmis_newsys_coupon_20240321_1 b
     * ON
     * a.id=b.id
     * SET
     * a.useful_life_start = b.useful_life_start,
     * a.modify_time = b.modify_time
     * where
     * a.newsys_coupon_rule_id = 1782 and a.useful_life_start = '2023-07-01 00:00:00' and a.status = 1;
     * @throws IOException
     */
    private static void creatRollBackSql() throws IOException {
        Map<String, Integer> map = new TreeMap<>();
        map.put("1781", 36);
        map.put("1782", 9);
        map.put("1783", 4);
        map.put("1784", 3);
        map.put("1785", 1);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            for (int j = 1; j <= entry.getValue(); j++) {
                stringBuilder.append("UPDATE t_mktmis_newsys_coupon a");
                stringBuilder.append("\n\r");
                stringBuilder.append("INNER JOIN");
                stringBuilder.append("\n\r");
                stringBuilder.append("backup_tables.t_mktmis_newsys_coupon_20240321_").append(i).append(" b");
                stringBuilder.append("\n\r");
                stringBuilder.append("ON");
                stringBuilder.append("\n\r");
                stringBuilder.append("a.id=b.id");
                stringBuilder.append("\n\r");
                stringBuilder.append("SET");
                stringBuilder.append("\n\r");
                stringBuilder.append("a.useful_life_start = b.useful_life_start,");
                stringBuilder.append("\n\r");
                stringBuilder.append("a.modify_time = b.modify_time");
                stringBuilder.append("\n\r");
                stringBuilder.append("where 1 = 1");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                stringBuilder.append("\n\r");
                i++;
            }
        }
        File file = new File("D:\\doc\\生产工单\\carmktmis\\sql\\20240321-chinaMobileStartDateUpdate\\51_carmkt.sql");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        bufferedOutputStream.write(stringBuilder.toString().getBytes());
    }
}
