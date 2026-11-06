package com.wubin.test.z;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStory {

    //攻略说，我好像是反派之主
    public static void main(String[] args) throws IOException {
        story95();
//        story83();
    }

    public static void story81() throws IOException {
        //草莓不是这么吃哒
        //草莓不是这的第1本书
        //佛子太子禁欲，丰腴美人勾他破戒
        //丰腴嫡女勾佛子，太子他破戒失控
        //殿下别忍了，您的佛珠都捏碎了
        //https://www.tomcan.com/shu/375796/1488643.html
        //https://www.sjzdzzy.com/ProductDetail/s1016396/320266580.html
        String url = "https://www.0ban.net/v/908_908106/2047139/";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.reader-main > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.sectiony > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    public static void story82() throws IOException {
        String url = "https://www.7qs.org/67650/33870445.html";
//        String url = "https://www.7qs.org/189097/88619083_2.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    public static void story83() throws IOException {
        String url = "https://www.shuhaige.net/230610/93580062.html";
//        String url = "https://www.shuhaige.net/353225/123088623.html";
//        String url = "https://www.shuhaige.net/428412/151937156.html";
//        String url = "https://www.shuhaige.net/411963/141785998.html";
//        String url = "https://www.shuhaige.net/364514/125887966.html";
        //星界第三纪元
//        String url = "https://www.shuhaige.net/290341/130825976_2.html";
        //异世猎爵:从接肢神尸开始
//        String url = "https://www.shuhaige.net/306417/122329123_2.html";
        //末世：薪火崛起
//        String url = "https://www.shuhaige.net/272921/124650845_2.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content > p");
        if (elements.text().equals("")) {
            elements = document.select("div.tt-title > p");
        }
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //御兽世界:从蔓珠沙华开始
    //网游:神级刺客,我即是暗影
    public static void story84() throws IOException {
        String url = "https://www.20xs.org/220071/116382293.html";
//        String url = "https://www.20xs.org/289957/111991381.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content > p");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //我的体内有只鬼
    public static void story85() throws IOException {
        String url = "https://www.82zg.com/book/48230/19215028.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //灰雾之上：诡秘王座
    public static void story86() throws IOException {
        String url = "https://www.88xiaoshuo.net/Partlist/94006/151548223.shtml";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.zhangjieming > h1").first().text());

        Elements elements = document.select("div#content > p");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //京婚不熟
    public static void story87() throws IOException {
        String url = "https://www.00shu.info/151/151638/58114696.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //死亡十万次,我直接买通时空长河
    public static void story88() throws IOException {
        String url = "https://www.88xiaoshuo.net/Partlist/270031/169043813.shtml";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.zhangjieming > h1").first().text());

        Elements elements = document.select("div#content > p");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //末日诡异游戏:我成为了六道鬼神
    //末日诡异网游:我开局征服鬼女皇
    public static void story89() throws IOException {
        String url = "https://www.xdu7.la/html/218975/189012926.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.zhangjieming > h1").first().text());

        Elements elements = document.select("div#content > p");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //反派公主有三好，肤白貌美修为高
    //我的公主是反派？错的一定是世界
    //好想吃个火锅
    public static void story90() throws IOException {
//        String url = "https://www.599yo.com/6261624140117/6.html";
        String url = "https://m.paozww.com/biquge/590990/137033901_2.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("span.title").first().text());

        Elements elements = document.select("div#articlecon");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.nr_page > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //南宋第一卧底
    public static void story91() throws IOException {
//        String url = "https://www.bqg5555.cc/xs/19542/";
//        String url = "https://www.wmxs88.com/novel/jaNfA.html";
        String url = "https://www.7qishu.com/du/13/13194/4107293_2.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split(" ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else if (str.length() > 0) {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //将门六姝
    public static void story92() throws IOException {
        String url = "https://www.mycossyah.com/677_677228/66041058.html";
        Document document = Jsoup.connect(url).get();
        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        System.out.println(content);
        for (String str : content.split("         ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.page_chapter > ul >li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //网游之命轮之主
    public static void story93() throws IOException {
//        String url = "https://www.chuanyue1.com/cy/39683/76460981.html";
        String url = "https://www.23hh.com/book/58489/58489664/93666299.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        for (String str : content.split("         ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.page_chapter > ul >li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //曾经，我想做个好人
    public static void story94() throws IOException {
//        https://www.shenpinwu.com/25146/25146524/
//        String url = "https://www.ibiquges.com/100/100588/40388086.html";
        String url = "https://www.a6ksw.com/txt/17/17850/82563210.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        for (String str : content.split(" 　　")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem2 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //东宫避火图 九方千阙
    public static void story95() throws IOException {
        String url = "https://www.shukw.com/read/171275/46950587_2.html";
        Document document = Jsoup.connect(url).get();
        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        System.out.println(content);
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.page_chapter > ul >li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //赤心巡天
    public static void story96() throws IOException {
        String url = "https://www.biqukun.info/67/67255/18777987.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem2 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //火花溅 火云尾 连环火尾 诱影火狐 狐尾流焰 狐颜火海
    //幽萤燃 幽萤旋 幽萤吹 幽萤引 幽萤符-守 幽萤符-牢
    //空心竹 空心笋 竹叶铠 太岁炎 蚩熊冲锋
    //小风吹 尘雾袭 风之舞 尘雾迷 尘雾感 鹿角花
    //绞刑尾 地裂掌 土炮重击 大漠沙暴 三重土城 双生荒猞 山土藏
    //偷偷养只小金乌
    public static void story97() throws IOException {
//        String url = "https://m.gonb.net/19/19465/52460267_3.html";
        String url = "https://wujixsw.net/50_50728/53436664.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.bottem1 > a");
        System.out.println(elements2.get(3).attr("href"));
    }

    //这些妖怪怎么都有血条
    public static void story98() throws IOException {
        String url = "https://www.99mk.info/tag/99_99970/36455550.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
        for (String str : content.split("     ")) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }
        Elements elements2 = document.select("div.page_chapter > ul > li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //末日轮盘
    public static void story99() throws IOException {
        String url = "https://www.7722.org/html/460/414561272.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String[] arr = elements.text().split(" 　　");
        for (String str : arr) {
            if (str.length() > 100) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n").insert(100, "\n"));
            } else if (str.length() > 50) {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.insert(50, "\n"));
            } else {
                System.out.println(str);
            }
            System.out.println();
        }

        Elements elements2 = document.select("div.page_chapter > ul > li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

}
