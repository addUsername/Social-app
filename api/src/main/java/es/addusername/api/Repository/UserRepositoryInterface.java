package es.addusername.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.addusername.api.Entities.User;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {

	//WOW escribimos eso y ya tenemos la query.. osea graaciass
	User findOneByUserName(String userName);
}
