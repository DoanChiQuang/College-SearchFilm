/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Chiquang
 */
public class APIController {
    public static String imageURL = "https://image.tmdb.org/t/p/w500";
    private static String url = "https://api.themoviedb.org/3";
    private static String url_image = "https://serpapi.com/search.json";
    private static String key_imagee = "569d67b514f317533778fc15b90000c1afca4d252ee36b7766f7c80fe1f867f1";
    private static String key = "06ec375d478ec3be978523f547d14dbf";
    
    public static JSONObject callAPI(String api) throws IOException, JSONException{
        Connection.Response res;
        res = Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).ignoreHttpErrors(true).execute();
        Document doc=res.parse();
        JSONObject json= new JSONObject(doc.text());
        return json;
    }        
    
    public static JSONObject getListMovie(String search, String page) throws IOException, JSONException{
        String api = url + "/search/movie?api_key=" + key + "&language=en-US&query=" + search + "&page=" + page + "&include_adult=false";
        JSONObject json = callAPI(api);
        return json;
    }
    
    public static JSONObject getDetail(String id) throws IOException, JSONException {
        String api = url + "/movie/" + id + "?api_key=" + key + "&language=en-US";        
        JSONObject json = callAPI(api);
        return json;
    }
    
    public static JSONObject getVideo(String id) throws IOException, JSONException {
        String api = url + "/movie/" + id + "/videos?api_key=" + key + "&language=en-US";        
        JSONObject json = callAPI(api);
        return json;
    }
    
    public static boolean checkResponse(JSONObject result) {
        try {                    
            int totalResult = result.getInt("total_results");            
            if(totalResult>0) {
                return true;
            }
            else return false;
        } catch (Exception e) {            
            return false;
        }
    }
    
    public static JSONObject getSameImage(URL url) throws IOException, JSONException {
        String api = url_image + "?engine=google_reverse_image&google_domain=google.com&image_url=" + url + "&device=desktop&api_key=" + key_imagee;
        JSONObject json = callAPI(api);
        return json;
    }
    
    public static String handleCallAPI(String search, String option) {
        try {
            String temp[] = option.split("-");
            String type = temp[0];
            String page = temp[1];
            JSONObject json;
            switch(type) {
                case "list":
                    json = getListMovie(search, page);
                    if(checkResponse(json)) {
                        return "Success;" + String.valueOf(json).replace(";", ",");
                    }
                    else return "Error;Không tìm được phim!";
                case "detail":
                    json = getDetail(search);
                    if(!json.has("success")) {
                        return "Success;" + String.valueOf(json).replace(";", ",");
                    }
                    else return "Error;Không tìm được phim!";
                case "video":
                    json = getVideo(search);
                    if(!json.has("success")) {
                        return "Success;" + String.valueOf(json).replace(";", ",");
                    }
                    else return "Error;Không tìm được phim!";
                default:
                    return "Error;Không gọi được API!";
            }
        } catch (IOException | JSONException e) {            
            return "Error;Không gọi được API!";
        }
    }
       
}
