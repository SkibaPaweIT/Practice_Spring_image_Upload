package pl.skiba.springimageupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skiba.springimageupload.model.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
