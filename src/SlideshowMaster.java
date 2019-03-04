import java.util.ArrayList;

class SlideshowMaster {
    private final int count;
    private ArrayList<Photo> verticals = new ArrayList<>();
    private ArrayList<Photo> horizontals = new ArrayList<>();

    SlideshowMaster(String filePath) {
        var in = new In(filePath);
        var lines = in.readAllLines();

        count = Integer.parseInt(lines[0]);
        for (var i = 0; i < count; i++) {
            var photoParams = lines[i+1].split("\\s+");
            var tags = new String[Integer.parseInt(photoParams[1])];
            System.arraycopy(photoParams, 2, tags, 0, tags.length);
            var photo = new Photo(i, tags);
            if (photoParams[0].equals("H")) horizontals.add(photo);
            else verticals.add(photo);
        }
    }

    int getPhotosCount() {
        return this.count;
    }

    int getHorizontalsCount() {
        return this.horizontals.size();
    }

    int getVerticalsCount() {
        return this.verticals.size();
    }

    ArrayList<Photo> getPhotos() {
        var result = new ArrayList<>(this.horizontals);
        result.addAll(this.verticals);
        return result;
    }

    Iterable<Slide> Create() {
        var ids = new ArrayList<Slide>();
        return ids;
    }
}