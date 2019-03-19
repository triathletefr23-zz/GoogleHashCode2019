import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

class SlideshowMaster {
    private final static int EXIT_COUNT = 10;

    private ArrayList<Photo> photos = new ArrayList<>();
    private ArrayList<Photo> slideshow = new ArrayList<>();
    private Photo[] nonFormattedPhotos;
    private final String filePath;

    SlideshowMaster(String filePath) {
        this.filePath = filePath;
        var tempVerticalPhotos = new ArrayList<Photo>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            var count = Integer.parseInt(br.readLine());
            nonFormattedPhotos = new Photo[count];
            var i = 0;
            for (String line; (line = br.readLine()) != null;) {
                var photoParams = line.split("\\s+");
                var tags = new String[Integer.parseInt(photoParams[1])];
                System.arraycopy(photoParams, 2, tags, 0, tags.length);
                var photo = new Photo(i, tags);
                nonFormattedPhotos[i] = photo;
                if (photoParams[0].equals("H")) this.photos.add(photo);
                else tempVerticalPhotos.add(photo);
                i++;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        tempVerticalPhotos.sort(Collections.reverseOrder(Comparator.comparingInt(p -> p.Tags.length)));
        this.photos.addAll(transformVerticals(tempVerticalPhotos));

        this.photos.sort(Collections.reverseOrder(Comparator.comparingInt(p -> p.Tags.length)));
    }

    ArrayList<Photo> createSlideshow() {
        slideshow.add(this.photos.remove(0));
        var nonUsedPhotos = new ArrayList<Photo>();
        while (this.photos.size() > 0) {
            var currentPhoto = slideshow.get(slideshow.size() - 1);

            this.photos.sort(Collections.reverseOrder(currentPhoto.order()));

            var pairPhoto = this.photos.remove(0);
            var photoComparator = new PhotoComparator(currentPhoto, pairPhoto);

            if (!photoComparator.isCommonTagsExist()) {
                nonUsedPhotos.add(pairPhoto);
                continue;
            }

            slideshow.add(pairPhoto);

            System.out.println(this.photos.size());
        }

        if (nonUsedPhotos.size() > 0) {
            slideshow.addAll(nonUsedPhotos);
        }

        return slideshow;
    }

    void writeSlideShow() {
        var filePath = Helper.getFileName(this.filePath);
        try (var writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(this.slideshow.size()));
            writer.newLine();
            for (var photo : slideshow) {
                String valueToWrite;
                if (photo.Id == -1) {
                    valueToWrite = photo.VerticalPhotoIds[0] + " " + photo.VerticalPhotoIds[1];
                } else valueToWrite = String.valueOf(photo.Id);
                writer.write(valueToWrite);
                writer.newLine();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private ArrayList<Photo> transformVerticals(ArrayList<Photo> tempVerticals) {
        var verticals = new ArrayList<Photo>();
        while (tempVerticals.size() > 0) {
            var photo = tempVerticals.remove(0);
            tempVerticals.sort(Collections.reverseOrder(photo.order()));
            var pairPhoto = tempVerticals.remove(0);
            var photoComparator = new PhotoComparator(photo, pairPhoto);

            if (!photoComparator.isCommonTagsExist()) {
                continue;
            }

            var commonTags = photoComparator.getCommonTags();
            var commonPhoto = new Photo(commonTags.toArray(new String[0]), new int[]{ photo.Id, pairPhoto.Id});
            verticals.add(commonPhoto);
        }
        return verticals;
    }

    int getPhotosCount() {
        return this.nonFormattedPhotos.length;
    }

    int getHorizontalsCount() {
        return this.photos.size() - getVerticalsCount();
    }

    int getVerticalsCount() {
        return this.photos.stream()
                .map(photo -> photo.VerticalPhotoIds.length != 0)
                .collect(Collectors.toList()).size();
    }

    Photo[] getPhotos() {
        return this.nonFormattedPhotos;
    }
}
