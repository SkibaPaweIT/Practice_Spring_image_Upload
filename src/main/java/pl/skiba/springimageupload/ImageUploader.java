package pl.skiba.springimageupload;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skiba.springimageupload.model.Image;
import pl.skiba.springimageupload.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
         cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "drpbc7lsl",
                "api_key", "461414337451821",
                "api_secret", "45f_qy9oAexb1_53SdkyCMLSTFU"));
    }

    public String uploadFileAndSaveToDb(String path) {
        File file = new File(path);
        Map uploadResult = null;

        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
        }

        return uploadResult.get("url").toString(); //getting it from Compliation
    }
}
