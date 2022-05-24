package ru.hse.equeue.client;
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.maps.GeoApiContext;
//import com.google.maps.GeocodingApi;
//import com.google.maps.model.GeocodingResult;
//import org.json.JSONObject;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class GoogleGeocoderClient {
//
//    public static void main(String[] args) throws Exception {
////        System.out.println(getAddress(55.5, 37.5));
//    }
//
//    public static String getAddress(double x, double y) throws Exception {
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey("AIzaSyAarhB3DdpgATWzaPcalbpG5ZZB4eu9qZ0")
//                .build();
//        GeocodingResult[] results = GeocodingApi.geocode(context,
//                "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        context.shutdown();
//        return gson.toJson(results[0].addressComponents);
//    }
//}
//

import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Test {
    String packageName = "ru.hse.equeue.service";
    List<String> exclides = Arrays.asList("wait", "equals", "toString", "hashCode", "getClass", "notify", "notifyAll");

    public List<Class> getClasses() {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

//    private String getLines(List<Class> classes) {
//        StringBuilder res = new StringBuilder();
//        classes.forEach(it -> it.getMethod());
//        return res.toString();
//
//
//    }

    private String getTable(List<Class> classes) {
        StringBuilder sb = new StringBuilder();
        classes.forEach(it -> {
            if (!it.getName().contains("Builder") && !it.getName().contains("$"))
                sb.append("\\begin{table}[H]\n" +
                        "    \t\\caption{Методы класса " + it.getName().replace(packageName + ".", "") + "}\n" +
                        "            \\begin{center}\n" +
                        "                \\begin{tabular}{|c|c|}\n" +
                        "                    \\hline\n" +
                        "                    Метод & Назначение \\\\\n" +
                        getMethods(it) +
                        "                    \\hline\n" +
                        "                \\end{tabular}\n" +
                        "            \\end{center}\n" +
                        "    \t\\end{table}\n");
        });
        return sb.toString();
    }

    private String getMethods(Class it) {
        StringBuilder res = new StringBuilder();
        for (Method method : it.getMethods()) {
            if (!exclides.contains(method.getName()))
                res.append("\t\t\t\t\\hline\n\t\t\t\t" +
                        method.getName() + "& - \\\\\n");
        }
        return res.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Test t = new Test();
        System.out.println(t.getTable(t.getClasses()));
    }
}
