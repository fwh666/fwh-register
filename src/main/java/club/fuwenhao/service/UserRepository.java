package club.fuwenhao.service;

import club.fuwenhao.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Title:
 * @ClassName: club.fuwenhao.service.UserRepository.java
 * @Description:
 * @author: fuwenhao
 * @date:  2023-04-16 11:53 AM
 * @version V4.5.3
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}