import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hashmap2 {


    public static void main(String[] args) {
        Map<String, Object> abc = new HashMap<>();
        abc.put("name","Shyam");
        abc.put("email","shyamjaiswal@gmail.com");
        Map<String, Object> abc2 = new HashMap<>();
        abc2.put("name","Bob");
        abc2.put("email","bob32@gmail.com");
        Map<String, Object> abc3 = new HashMap<>();
        abc3.put("name","Jai");
        abc3.put("email","jai87@gmail.com");
        List<Map<String, Object>> ls= new ArrayList<Map<String, Object>>();
        ls.add(abc);
        ls.add(abc2);
        ls.add(abc3);
        Map<String, Object> map = new HashMap<>();
        map.put("employees",ls);
        System.out.println(map);
        JSONObject js = new JSONObject(map);
        System.out.println(js);
    }

}