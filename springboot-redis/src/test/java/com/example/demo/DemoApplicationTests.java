package com.example.demo;

import com.example.demo.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        Set<String> keys = redisUtil.keys("*");
        System.out.println(keys);
    }

    @Test
    void cont(){
        System.out.println(redisUtil.get("name"));
    }

    //做一个自动生成流水号的功能
    @Test
    void getNum(){
        String pre = "fh";
        String dateStr = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        dateStr = simpleDateFormat.format(date);
        String rpre = pre+dateStr;
        int i=5;
        System.out.println(rpre);
        String res = liushuihao(rpre,i);
        System.out.println(res);
    }

    String liushuihao(String str,int i){
        char[] cc = new char[i];
        for (int n=0;n<i;n++){
            cc[n]='9';
        }
        String s = String.valueOf(cc);
        long end = Long.valueOf(s);
        long l = redisUtil.incr(str,1);
        StringBuilder sb= new StringBuilder(str);
        if(l>end){
            throw new RuntimeException("违背流水号生成规则，不生成单号");
        }else {
            String str1 = String.valueOf(l);
            if(str1.length()<i){
                for (int x=0;x<i-str1.length();x++){
                    sb.append("0");
                }
            }
            sb.append(str1);
        }
        return sb.toString();
    }






}
