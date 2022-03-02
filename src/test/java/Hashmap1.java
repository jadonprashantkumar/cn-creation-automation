
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Hashmap1{

    public static void main(String args[]) {

        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("emp_name","Shubham");
        obj1.put("email","Shubham@gmail.com");
        obj1.put("job_profile","intern");
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("emp_name","gunjan");
        obj2.put("email","gunjan@gmail.com");
        obj2.put("job_profile","qa");
        List<Map<String, Object>> ls= new ArrayList<Map<String, Object>>();
        ls.add(obj1);
        ls.add(obj2);
        Map<String, Object> map = new HashMap<>();
        map.put("emp_details",ls);
        System.out.println(map);
        JSONObject js = new JSONObject(map);
        System.out.println(js);
    }

}