import RestAP.communication.Communication;
import RestAP.config.MyConfig;
import RestAP.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;


public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        List<User> userList = communication.getAllUsers();
        System.out.println(userList);

        String cookie = communication.getCookie();
        System.out.println(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);

        User user = new User(3l, "Ivan", "Putin", (byte) 11);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        communication.saveUser(httpEntity);

        User user1 = new User(3l, "Petya", "Petrov", (byte) 51);
        HttpEntity<User> httpEntity1 = new HttpEntity<>(user1, headers);
        communication.updateUser(httpEntity1);

        HttpEntity<User> httpEntity2 = new HttpEntity<>(user1, headers);
        communication.deleteUser(3, httpEntity2);


    }
}