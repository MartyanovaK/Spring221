package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car = new Car("BMW", 567);
      Car car2 = new Car("BW", 589);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      List<User> users = userService.listUsers();
      userService.getUserByModelAndSeries("BMW", 567);
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());


      }



      context.close();

   }

}
