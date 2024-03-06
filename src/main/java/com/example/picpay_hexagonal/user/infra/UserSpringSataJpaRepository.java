package com.example.picpay_hexagonal.user.infra;

import com.example.picpay_hexagonal.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserSpringSataJpaRepository extends JpaRepository<User, Long> {


}
