import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args){
        Gson gson = new Gson();
        Person person = new Person("Bob",18,"Chendu");
        System.out.println(gson.toJson(person));
    }
}
