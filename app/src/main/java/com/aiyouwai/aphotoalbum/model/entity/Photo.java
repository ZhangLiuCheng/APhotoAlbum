package com.aiyouwai.aphotoalbum.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Photo implements Cloneable {

    public String date;

    public String name;
    public String brief;
    public String image;

    public Photo(String name, String brief, String image, String date) {
        this.name = name;
        this.brief = brief;
        this.image = image;
        this.date = date;
    }

    @Override
    public Photo clone() {
        Photo c = null;
        try {
            c = (Photo)super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return c;
    }

    public static List<Photo> getTestData() {
        List<Photo> data = new ArrayList<>();
        data.add(new Photo("上海",
                "上海，简称“沪”或“申”，中华人民共和国直辖市，中国国家中心城市，中国的经济、金融、贸易、航运中心，首批沿海开放城市。地处长江入海口，隔东中国海与日本九州岛相望，南濒杭州湾，西与江苏、浙江两省相接。[1-2] ",
                "http://c.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=b03c5813daf9d72a0369184fb5434351/fc1f4134970a304e8f6f9cd6d2c8a786c9175c48.jpg", "9月"));
        data.add(new Photo("南京",
                "“江南佳丽地，金陵帝王州”，南京拥有着7000多年文明史[5]  、近2600年建城史和近500年的建都史，是中国四大古都之一，有“六朝古都”、“十朝都会”之称，是中华文明的重要发祥地，历史上曾数次庇佑华夏之正朔，长期是中国南方的政治、经济、文化中心，拥有厚重的文化底蕴和丰富的历史遗存。[6-8] ",
                "http://a.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=a0e988f2d62a283457ab3e593adca28f/a044ad345982b2b71012a12b33adcbef77099b54.jpg", "9月"));
        data.add(new Photo("淮安",
                "淮安区，是江苏省淮安市下辖区。全国八大菜系之一淮扬菜发源地之一，位于江苏中北部",
                "http://d.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=ce220b7c43166d222c7a1dc6274a6292/242dd42a2834349b98e2e232c9ea15ce37d3bea5.jpg", "5月"));
        data.add(new Photo("无锡",
                "无锡，简称“锡”，古称梁溪、金匮，被誉为“太湖明珠”。无锡市位于长江三角洲平原腹地，江苏南部，太湖流域的交通中枢，京杭大运河从中穿过。无锡北倚长江，南濒太湖，东接苏州，西连常州，构成苏锡常都市圈无锡自古就是鱼米之乡，素有布码头、钱码头、窑码头、丝都、米市之称，是中国国家历史文化名城。无锡是中国民族工业和乡镇工业的摇篮，是苏南模式的发祥地。无锡文化属吴越文化，无锡人属江浙民系使用吴语。[1] ",
                "http://h.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=76553c9a612762d09433acedc185639f/95eef01f3a292df53f472facbe315c6035a87387.jpg", "4月"));
        data.add(new Photo("淮安",
                "淮安区，是江苏省淮安市下辖区。全国八大菜系之一淮扬菜发源地之一，位于江苏中北部",
                "http://d.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=ce220b7c43166d222c7a1dc6274a6292/242dd42a2834349b98e2e232c9ea15ce37d3bea5.jpg", "4月"));
        data.add(new Photo("上海",
                "上海，简称“沪”或“申”，中华人民共和国直辖市，中国国家中心城市，中国的经济、金融、贸易、航运中心，首批沿海开放城市。地处长江入海口，隔东中国海与日本九州岛相望，南濒杭州湾，西与江苏、浙江两省相接。[1-2] ",
                "http://c.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=b03c5813daf9d72a0369184fb5434351/fc1f4134970a304e8f6f9cd6d2c8a786c9175c48.jpg", "1月"));
        return data;
    }
}
