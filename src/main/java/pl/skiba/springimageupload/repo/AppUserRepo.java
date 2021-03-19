package pl.skiba.springimageupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skiba.springimageupload.model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser , Long> {

    AppUser findByUsername(String username);

}
