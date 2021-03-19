package pl.skiba.springimageupload.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.skiba.springimageupload.model.Image;
import pl.skiba.springimageupload.repo.ImageRepo;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepo imageRepo;

    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        List<Image> all = imageRepo.findAll();

        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAdress(), "brak");
            add(image);
        });
    }
}
