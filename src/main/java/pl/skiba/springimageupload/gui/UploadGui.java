package pl.skiba.springimageupload.gui;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skiba.springimageupload.ImageUploader;

import java.awt.*;
import java.awt.event.ActionListener;

@Service
@Route("upload") //Vadin nie ma slashy z przodu jak coś
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;
        Label ourLabel = new Label();

        TextField textField = new TextField();
        Button button = new Button("Upload");
        button.addClickListener(clickEvent ->
        {
            String uploadImage = imageUploader.uploadFileAndSaveToDb(textField.getValue());
            com.vaadin.flow.component.html.Image image = new Image(uploadImage, "nie ma obrazka");
            ourLabel.setText("Udało się wrzucić obrazek!!");
            add(ourLabel);
            add(image);

        });

        add(textField);
        add(button);

    }
}
