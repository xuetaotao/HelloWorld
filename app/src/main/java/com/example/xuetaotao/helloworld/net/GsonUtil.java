package com.example.xuetaotao.helloworld.net;

/**
 * Gson解析工具类
 */
public class GsonUtil {


}



//public class GsonUtil {
//    /**
//     * 把json解析成T类型
//     *
//     * @param json 需要解析的json
//     * @return 返回结果
//     */
//    public static <T> T get(String json, Class<T> clazz) {
//        try {
//            Gson gson = new Gson();
//            return gson.fromJson(json, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 把json解析成T类型
//     *
//     * @param json 需要解析的json
//     * @return 返回结果
//     */
//    public static <T> T get(String json, Type type) {
//        try {
//            Gson gson = new Gson();
//            return gson.fromJson(json, type);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}