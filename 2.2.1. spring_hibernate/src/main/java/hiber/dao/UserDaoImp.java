package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Transactional(readOnly = true)
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Transactional
   @Override
   public User getUserByModel(String model) {
      User user = sessionFactory.getCurrentSession()
              .createQuery("FROM User where Car.model = 'model'", User.class)
              .getSingleResult();
      return user;
   }

   @Transactional
   @Override
   public List<User> getUserBySeries(int series) {
      List<Car> cars = sessionFactory.getCurrentSession().
              createQuery("from Car where series = series", Car.class).list();
      List<User> users = new ArrayList<>();
      for (Car car: cars) {
         users.add(car.getUser());
      }
      return users;
   }

}
