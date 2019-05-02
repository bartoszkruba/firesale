package com.company.firesale.service;

import com.company.firesale.data.entity.Image;
import com.company.firesale.data.repository.ImageRepository;
import com.company.firesale.json_classes.ImageJsonClass;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private static String uploadDir = System.getProperty("user.dir") + "/images";

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostConstruct
    void createDefaultFolderIfMissing() {
        File f = new File(uploadDir);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Transactional(rollbackOn = Exception.class)
    protected Image uploadImage(String imageString) throws Exception {

        Image image = new Image();
        image = saveImage(image);

        String fileExtension = extractFileTypeFromBase64(imageString);
        String filePath = uploadDir + "/" + image.getId() + fileExtension;

        byte[] imageByte = Base64.decodeBase64(imageString.split(",")[1]);
        new FileOutputStream(filePath).write(imageByte);
        image.setFilepath("images/" + image.getId() + fileExtension);

        saveImage(image);
        return image;
    }

    private static String extractFileTypeFromBase64(final String encoded) {
        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        final Matcher matcher = mime.matcher(encoded);
        if (!matcher.find())
            return "";
        return "." + matcher.group(1).split("/")[1].toLowerCase();
    }

    public ImageJsonClass getImageById(Long id) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        return optionalImage.map(ImageJsonClass::new).orElse(null);
    }

}
