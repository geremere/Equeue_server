//package ru.hse.equeue.client;
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
