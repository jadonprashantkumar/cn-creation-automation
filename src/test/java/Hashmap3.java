import org.json.JSONObject;

import java.util.*;

/* {"menu": {
        "id": "file",
                "value": "File",
                "popup": {
            "menuitem": [
            {"value": "New", "onclick": "CreateDoc()"},
            {"value": "Open", "onclick": "OpenDoc()"},
            {"value": "Save", "onclick": "SaveDoc()"}
    ]
        }
    }}

    */
public class Hashmap3 {

    public static void main(String[] args) {
        Map<String,Object> m1=new HashMap<>();
        m1.put("value","New");
        m1.put("onclick","creteDoc()");
        Map<String,Object> m2=new HashMap<>();
        m2.put("value","open");
        m2.put("onclick","openDoc()");
        Map<String,Object> m3=new HashMap<>();
        m3.put("value","Save");
        m3.put("onclick","saveDoc()");
        List<Map<String, Object>> ls= new ArrayList<Map<String, Object>>();
        ls.add(m1);
        ls.add(m2);
        ls.add(m3);
        Map<String, Object> map = new HashMap<>();
        map.put("menuitem",ls);
        Map<String,Object> mm1=new HashMap<>();
        mm1.put("id","files");
        mm1.put("Value","Files");
        mm1.put("popup",map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("menu",mm1);
        JSONObject js = new JSONObject(map1);
        System.out.println(js);
        

    }
}
