package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

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

   public User getUserByModelAndSeries(String model, int series) {

      return sessionFactory.getCurrentSession()
              .createQuery("select u from User u where " +
                      "u.car.model = :model and u.car.series = :series", User.class)
              .setParameter("model", model).setParameter("series", series)
              .getSingleResult();
   }

}
