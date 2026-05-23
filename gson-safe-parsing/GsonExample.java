import com.google.gson.Gson;

// Plain Java Object (POJO)
class User {
    private String name;
    private int age;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters (optional for Gson serialization)
    public String getName() { return name; }
    public int getAge() { return age; }
}

public class GsonExample {

    public static void main(String[] args) {

        Gson gson = new Gson();

        // ✅ Java Object → JSON
        User user = new User("Aashish", 30);
        String json = gson.toJson(user);
        System.out.println("JSON Output: " + json);

        // ✅ JSON → Java Object
        String jsonInput = "{\"name\":\"John\",\"age\":25}";
        User newUser = gson.fromJson(jsonInput, User.class);
        System.out.println("Name: " + newUser.getName());
        System.out.println("Age: " + newUser.getAge());
    }
}