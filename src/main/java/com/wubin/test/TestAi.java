package com.wubin.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
//import io.milvus.v2.client.ConnectConfig;
//import io.milvus.v2.client.MilvusClientV2;
//import io.milvus.v2.common.DataType;
//import io.milvus.v2.common.IndexParam;
//import io.milvus.v2.service.collection.request.*;
//import io.milvus.v2.service.index.request.CreateIndexReq;
//import io.milvus.v2.service.vector.request.DeleteReq;
//import io.milvus.v2.service.vector.request.InsertReq;
//import io.milvus.v2.service.vector.request.SearchReq;
//import io.milvus.v2.service.vector.request.data.FloatVec;
//import io.milvus.v2.service.vector.response.DeleteResp;
//import io.milvus.v2.service.vector.response.InsertResp;
//import io.milvus.v2.service.vector.response.SearchResp;

import java.math.BigDecimal;
import java.util.*;

public class TestAi {

    public static void testMinerU() {
        String body = "{" +
                "    \"model\": \"mineru25\"," +
                "    \"messages\": [" +
                "      {" +
                "        \"role\": \"user\"," +
                "        \"content\": [" +
                "          {\"type\": \"image_url\", \"image_url\": {\"url\": \"data:image/jpeg;base64," + Base64.encode(FileUtil.readBytes("D:\\wubin\\python\\test\\3\\Screenshot_20261015_134420.jpg")) + "\"}}," +
                "          {\"type\": \"text\", \"text\": \"OCR:\"}" +
                "        ]" +
                "      }" +
                "    ]" +
                "  }";
        String result = HttpRequest.post("http://122.224.39.188:8983/v1/chat/completions")
                .body(body)
                .execute()
                .body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(jsonObject.toStringPretty());

//        String body = "{" +
//                "  \"model\": \"qwen3.5-ocr\"," +
//                "  \"messages\": [" +
//                "    {" +
//                "      \"role\": \"user\"," +
//                "      \"content\": [" +
//                "        {" +
//                "          \"type\": \"image_url\"," +
//                "          \"image_url\": {\"url\":\"https://img.alicdn.com/imgextra/i2/O1CN01ktT8451iQutqReELT_!!6000000004408-0-tps-689-487.jpg\"}," +
//                "          \"min_pixels\": 3072," +
//                "          \"max_pixels\": 8388608" +
//                "        }," +
//                "        {\"type\": \"text\", \"text\": \"Please extract the invoice number, train number, departure station, destination station, departure date and time, seat number, seat type, ticket price, ID card number, and passenger name from the train ticket image. Extract the key information accurately. Do not omit information or fabricate false information. Replace any single character that is blurry or obscured by glare with a question mark (?). Return the data in JSON format.\"}" +
//                "      ]" +
//                "    }" +
//                "  ]" +
//                "}";
//        String result = HttpRequest.post("https://maas.qianwenaiapi.com/compatible-mode/v1/chat/completions")
//                .body(body)
//                .header("Authorization", "Bearer sk-ws-H.EPXHLII.w1BT.MEUCIAsVI1bm5ymTrmFJMJ_rUosuGF_MyA7ow9HWxBrvqZsqAiEA2HDoG4DX2IVti8r5DCPjP91q4tx34MBCC-H-q3_DiGk")
//                .execute()
//                .body();
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        System.out.println(jsonObject.toStringPretty());
    }

    public static void testBgeM3() {
        String body = "{" +
                "  \"model\": \"bge-m3\"," +
                "  \"input\": \"helloWord\"" +
                "}";
//        List<String> texts = Arrays.asList(
//                "深圳公园的椅子竟然是外卖盒做的",
//                "44岁副院长下车避险 高架桥坠落去世",
//                "人社部：要把未参保人员找到动员参保"
//        );
//        Map<String, Object> map = new HashMap<String, Object>() {{
//            put("model", "bge-m3");
//            put("input", texts);
//        }};
//        String body = new Gson().toJson(map, new TypeToken<Map<String, Object>>() {
//        }.getType());

        String result = HttpRequest.post("http://122.224.39.188:8982/v1/embeddings")
                .body(body)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer sk-emr-local")
                .execute()
                .body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(jsonObject.toStringPretty());
        List<List<Float>> vectors = new ArrayList<>();
        List<List<BigDecimal>> vectors2 = new ArrayList<>();
        for (JSONObject data : jsonObject.getJSONArray("data").jsonIter()) {
            JSONArray embedding = data.getJSONArray("embedding");
            List<Float> vec = new ArrayList<>();
            List<BigDecimal> vec2 = new ArrayList<>();
            for (Object f : embedding) {
                vec.add(((BigDecimal) f).floatValue());
                vec2.add((BigDecimal) f);
            }
            vectors.add(vec);
            vectors2.add(vec2);
        }
        System.out.println(vectors);
        System.out.println(vectors2);
    }

    public static void testQwen3(String system, String user) {
//        String body = "{" +
//                "  \"model\": \"qwen3-14b-emr\"," +
//                "  \"chat_template_kwargs\": {\"enable_thinking\": false}," +
////                "  \"temperature\": 0.1," +
////                "  \"top_p\": 1," +
////                "  \"frequency_penalty\": 0," +
////                "  \"presence_penalty\": 0," +
////                "  \"max_tokens\": 4096," +
////                "  \"stop\": [\"<|im_end|>\"]," +
//                "  \"messages\": [" +
//                "    {" +
//                "      \"role\": \"system\"," +
//                "      \"content\": \"" + system + "\"" +
//                "    }," +
//                "    {" +
//                "      \"role\": \"user\"," +
//                "      \"content\": \"" + user + "\"" +
//                "    }" +
//                "  ]" +
//                "}";
//        System.out.println(new Date());
//        String result = HttpRequest.post("http://122.224.39.188:8980/v1/chat/completions")
//                .body(body)
//                .header("Authorization", "Bearer sk-emr-local")
//                .execute()
//                .body();
//        System.out.println(new Date());
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        System.out.println(jsonObject.toStringPretty());
//        String content = jsonObject.getJSONArray("choices").getJSONObject(0)
//                .getJSONObject("message").getStr("content");
//        System.out.println(content);

        String body = "{" +
                "  \"model\": \"qwen3.8-max\"," +
//                "  \"enable_thinking\": false," +
                "  \"chat_template_kwargs\": {\"enable_thinking\": false}," +
                "  \"messages\": [" +
                "    {" +
                "      \"role\": \"system\"," +
                "      \"content\": \"" + system + "\"" +
                "    }," +
                "    {" +
                "      \"role\": \"user\"," +
                "      \"content\": \"" + user + "\"" +
                "    }" +
                "  ]" +
                "}";
        System.out.println(new Date());
        String result = HttpRequest.post("https://maas.qianwenaiapi.com/compatible-mode/v1/chat/completions")
                .body(body)
                .header("Authorization", "Bearer sk-ws-H.EPXHLII.w1BT.MEUCIAsVI1bm5ymTrmFJMJ_rUosuGF_MyA7ow9HWxBrvqZsqAiEA2HDoG4DX2IVti8r5DCPjP91q4tx34MBCC-H-q3_DiGk")
                .execute()
                .body();
        System.out.println(new Date());
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(jsonObject.toStringPretty());
        String content = jsonObject.getJSONArray("choices").getJSONObject(0)
                .getJSONObject("message").getStr("content");
        System.out.println(content);
    }

    public static void main(String[] args) {
//        testMinerU();
//        testBgeM3();
        testQwen3("请将以下医患对话按照「医生」和「患者」两个角色进行切分，输出为 JSON 数组，格式为 [{role: 医生/患者, content: 对话内容}]。要求：保留口语原貌，不修改、不概括、不润色，逐句切分，语气词、重复词、停顿词均保留。",
                "肌瘤有多少年了？发现肌瘤去年发现的吧。去年体检，去年是七八月份，到现在有一年了吧，那时候体检嘛，全部体检。就是体检发现的，是不是？然后肌瘤说是有多大？嗯这个我忘了。要忘记了。啊要要你但是那个报告单在家里。嗯月经有改变吗。月经它反正基本上每个月都对不上。不是早就是晚。嗯不是早就是晚一般会。一般都是往后面推。就是周期推长一点。大部分都是啊推但是这个月很奇怪这个月月经它都来了有。十多天。这这个月来了十多天没干净。啊九号开始来来来了大概到二十几号才开始那个。九号来的。然后20多号还没干净。今天是29号。29号大概在二十二十三号左右。23号左右干净。量跟以前比一样吗？量都差不多但是他就是颜色很黑但黑。褐褐色的那种。哦，褐色的那一种。肚子痛有吗？没有，其他不舒服症状都没有。就今天主要还是想来子宫肌瘤的大小复查一下是吧？嗯，复查一下看一下。身高多少呀？16168左右吧，168体重呢？100斤左右。那个月经量有没有改变？月经量现在一次来的比一次少。就是这近近个半年开始月经。啊不是不是近半年，这很早哦早。四五年前我就开始吃药在调理，后面调理了大概是有半年左右，后面就这段时间就没调理了。吃药是吃什么药？中药。嗯。现在已经不调理了对吧？他也没调理。其他身体都好的，有没有高血压、糖尿病啊、开过刀啊这些。这些也还好。都没有。生过几个孩子啊？一生一个。流产有没有过？啊没有。顺产还是剖腹产的？嗯顺产。现在有没有上环？啊现在没有，早就拔了。就没没上环了啊。那有避孕吗？没有。那万一怀孕想要孩子吗？这50岁的50多岁了呀。还是有机会的，少是少一点哈。反正是没有没有避孕，但是呢就是怀孕了又不想要的啊。啊。查过那个激素没有就看之前查过激术说就是说快要那个了嘛，快要那个有查过的啊查过的查过激然后说不大好了的，但是可能还会来对就是吃药理的一段时间嘛就了不多半年半年。有卵巢功能低下的情况啊。那我给你开个B超，然后你去那个做阴道B超能接受吗？做阴道B超，嗯做阴道B超要好一些吗？阴道B超快，你不用憋尿。如果你说要做腹部B超的要喝水憋尿的。普通B超多少钱呢？价格是一样的。价格是一样的，那就做阴道吧。好的，最起码它不要憋尿。对，就因为你天气热嘛，你也憋不到嘛。卵巢囊肿巧克力囊肿得过的。对开过刀的。刚才开过刀的刚才都没说。很多年了。很多年了。他就在外面手可以摸得到的。那很大了咯。他小的时候他他也摸得到，然后呃开始一开始就腹腔镜下做的是吧？啊腹腔镜下手术的是不是？是开大刀的还是打动作的？然后他就在外面就在阴道外面。那就不是巧克力囊肿，巧克力囊肿是肚子里面的。那可不，你在外哎对。我洗澡的时候一摸就可以摸得到。那应该是前庭大腺囊肿。哦是在外阴那部分是吧？对对对对那不是巧克力囊肿。哎但是我这个好像是巧克力囊肿但是很多年了那三呃30多岁巧克力囊肿它叫做子宫腺肌症子宫内膜异位囊肿是在肚子里面的你那个是因为你看起来可能有点像脱出来的东西流出来可能有点黑黑的所以你把它记成那个。两回事，因为时间太长了，我一直知道是巧克力囊肿。不是，你别你不要告诉医生是巧克力囊肿，搞错了到时候。那我给你开个阴道 B 超，我们先做 B 超，看了再说，好吗？嗯。医保卡拿出来。");

//        createCollection();
//        insert();
//        delete();
//        search();

//        loadCollection();
//        releaseCollection();
//        getLoadState();
    }

//    public static void search() {
//        List<String> texts = Arrays.asList(
//                "深圳公园的椅子竟然是外卖盒做的"
//        );
//        Map<String, Object> map = new HashMap<String, Object>() {{
//            put("model", "bge-m3");
//            put("input", texts);
//        }};
//        String body = new Gson().toJson(map, new TypeToken<Map<String, Object>>() {
//        }.getType());
//        String res = HttpRequest.post("http://122.224.39.188:8982/v1/embeddings")
//                .body(body)
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer sk-emr-local")
//                .execute()
//                .body();
//        JSONObject jsonObject = JSONUtil.parseObj(res);
//        JSONArray embedding = jsonObject.getJSONArray("data").getJSONObject(0)
//                .getJSONArray("embedding");
//        List<Float> vector = new ArrayList<>();
//        for (Object f : embedding) {
//            vector.add(((BigDecimal) f).floatValue());
//        }
//        System.out.println(vector);
//        FloatVec queryVector = new FloatVec(vector);
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        SearchResp searchResp = client.search(SearchReq.builder()
//                .collectionName("test")
//                .annsField("vector")
//                .outputFields(Arrays.asList("text"))
//                .data(Collections.singletonList(queryVector))
//                .topK(10)
//                .build());
//        List<List<SearchResp.SearchResult>> searchResults = searchResp.getSearchResults();
//        for (List<SearchResp.SearchResult> results : searchResults) {
//            System.out.println("TopK results:");
//            for (SearchResp.SearchResult result : results) {
//                System.out.println(result);
//            }
//        }
//    }
//
//    public static void delete() {
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        DeleteResp deleteResp = client.delete(DeleteReq.builder()
//                .collectionName("test")
//                .ids(Arrays.asList(468507765064870258L, 468507765064870259L, 468507765064870260L,
//                        468507765064870261L, 468507765064870262L, 468507765064870263L,
//                        468507765064870264L, 468507765064870265L, 468507765064870266L,
//                        468507765064870267L))
//                .build());
//        System.out.println(deleteResp);
//    }
//
//    public static void insert() {
//        List<String> texts = Arrays.asList(
//                "深圳公园的椅子竟然是外卖盒做的",
//                "44岁副院长下车避险 高架桥坠落去世",
//                "人社部：要把未参保人员找到动员参保"
//        );
//        Map<String, Object> map = new HashMap<String, Object>() {{
//            put("model", "bge-m3");
//            put("input", texts);
//        }};
//        String body = new Gson().toJson(map, new TypeToken<Map<String, Object>>() {
//        }.getType());
//        String result = HttpRequest.post("http://122.224.39.188:8982/v1/embeddings")
//                .body(body)
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer sk-emr-local")
//                .execute()
//                .body();
//        List<JsonObject> rows = new ArrayList<>();
//        Gson gson = new Gson();
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        for (JSONObject data : jsonObject.getJSONArray("data").jsonIter()) {
//            int index = data.getInt("index");
//            JSONArray embedding = data.getJSONArray("embedding");
////            List<BigDecimal> vector = new ArrayList<>();
////            for (Object f : embedding) {
////                vector.add((BigDecimal) f);
////            }
//            List<Float> vector = new ArrayList<>();
//            for (Object f : embedding) {
//                vector.add(((BigDecimal) f).floatValue());
//            }
//            JsonObject row = new JsonObject();
//            row.add("vector", gson.toJsonTree(vector));
//            row.addProperty("text", texts.get(index));
//            rows.add(row);
//            System.out.println(row.toString());
//        }
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        InsertResp insertResp = client.insert(InsertReq.builder()
//                .collectionName("test")
//                .data(rows)
//                .build());
//        System.out.println(insertResp);
//    }
//
//    public static void createCollection() {
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        if (client.hasCollection(HasCollectionReq.builder().collectionName("test").build())) {
//            System.out.println("hasCollection");
//            return;
//        }
//
//        CreateCollectionReq.FieldSchema idField = CreateCollectionReq.FieldSchema.builder()
//                .name("id").dataType(DataType.Int64).isPrimaryKey(true).autoID(true).build();
//        CreateCollectionReq.FieldSchema vectorField = CreateCollectionReq.FieldSchema.builder()
//                .name("vector").dataType(DataType.FloatVector).dimension(1024).build();
//        CreateCollectionReq.FieldSchema textField = CreateCollectionReq.FieldSchema.builder()
//                .name("text").dataType(DataType.VarChar).maxLength(65535).build();
//        List<CreateCollectionReq.FieldSchema> fieldSchemaList = new ArrayList<>();
//        fieldSchemaList.add(idField);
//        fieldSchemaList.add(vectorField);
//        fieldSchemaList.add(textField);
//        CreateCollectionReq.CollectionSchema schema = CreateCollectionReq.CollectionSchema.builder()
//                .fieldSchemaList(fieldSchemaList).build();
//        client.createCollection(CreateCollectionReq.builder()
//                .collectionName("test")
//                .collectionSchema(schema)
//                .build());
//        System.out.println("createCollection");
//
//        IndexParam indexParam = IndexParam.builder()
//                .fieldName("vector")
//                .indexType(IndexParam.IndexType.AUTOINDEX)
//                .metricType(IndexParam.MetricType.COSINE)
//                .build();
//        client.createIndex(CreateIndexReq.builder()
//                .collectionName("test")
//                .indexParams(Collections.singletonList(indexParam))
//                .build());
//        System.out.println("createIndex");
//    }
//
//    public static void getLoadState() {
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        Boolean res = client.getLoadState(GetLoadStateReq.builder()
//                .collectionName("test")
//                .build());
//        System.out.println(res);
//    }
//
//    public static void loadCollection() {
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        client.loadCollection(LoadCollectionReq.builder()
//                .collectionName("test")
//                .build());
//        System.out.println("loadCollection");
//    }
//
//    public static void releaseCollection() {
//        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
//                .uri("https://in03-a3db10d405c2600.serverless.ali-cn-hangzhou.cloud.zilliz.com.cn")
//                .token("db_a3db10d405c2600:Mr6*5J~J3SW,M^ae")
//                .build());
//        client.releaseCollection(ReleaseCollectionReq.builder()
//                .collectionName("test")
//                .build());
//        System.out.println("releaseCollection");
//    }

}
