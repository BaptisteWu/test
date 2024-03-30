package com.wubin.test.z;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestStory {

    //攻略说，我好像是反派之主
    public static void main(String[] args) throws IOException {
        story85();
    }

    //我的体内有只鬼
    public static void story82() throws IOException {
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

    //进化:神宠时代,全球降临
    public static void story83() throws IOException {
        String url = "https://www.douyinxs.com/bqg/1055574/257580738.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("article#content > p");
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
        System.out.println(elements2.get(2).attr("href"));
    }

    //灰雾之上：诡秘王座
    public static void story84() throws IOException {
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

    //神明降世,看见血条的我杀疯了
    public static void story85() throws IOException {
        String url = "https://www.xs7.com/shu/83502/147879126.html";
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

    //末世:校花变丧尸,竟向我表白 我能打十个
    public static void story86() throws IOException {
        String url = "https://www.bqwxg8.com/wenzhang/11522/11522127/7663869.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.content > h1").first().text());

        Elements elements = document.select("div#content");
        String content = elements.text();
//        System.out.println(content);
        for (String str : content.split("         ")) {
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
        Elements elements2 = document.select("div.page_chapter >ul > li > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //死亡十万次,我直接买通时空长河
    public static void story87() throws IOException {
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
    public static void story88() throws IOException {
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

    //末日降临:百倍爆率刀刀爆物资
    public static void story89() throws IOException {
        String url = "http://www.26ks.org/book/64580/65075666.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.bookname > h1").first().text());

        Elements elements = document.select("div#content > p.content_detail");
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

    //凡人修仙，从当个小药奴开始
    public static void story90() throws IOException {
        String url = "http://www.yihanzw.com/yh/C1ENBlNe/1683314428.html";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div#content > h1").first().text());

        Elements elements = document.select("div.content");
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
        Elements elements2 = document.select("div.justify-content-between > a");
        System.out.println(elements2.get(2).attr("href"));
    }

    //网游之我有百倍奖励
    public static void story91() throws IOException {
        String url = "http://www.zhaoxiaoshuo.cc/wangyouzhiwoyoubaibeijiangli/24974056";
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

    //网游之命轮之主
    public static void story92() throws IOException {
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

    //网游：开局获得神级天赋
    public static void story93() throws IOException {
        String url = "https://www.kujiang.com/book/67371/147920940";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.html());

        System.out.println(document.select("div.chapter_header > h1").first().text());

        Elements elements = document.select("div.content > p");
        for (Element element : elements) {
            String str = element.text();
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
        Elements elements2 = document.select("li.page_next > a");
        System.out.println(elements2.get(0).attr("href"));
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

    //光明！
    public static void story95() throws IOException {
        String url = "https://www.ishuquge.org/txt/158008/46759416.html";
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
