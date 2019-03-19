import edu.princeton.cs.algs4.Quick3string;
import java.util.Comparator;

class Photo {
    public final int Id;
    public final String[] Tags;
    public final int[] VerticalPhotoIds;

    public Photo(int id, String[] tags) {
        VerticalPhotoIds = new int[2];
        Id = id;
        Tags = new String[tags.length];
        Quick3string.sort(tags);
        System.arraycopy(tags, 0, Tags, 0, tags.length);
    }

    public Photo(String[] tags, int[] ids) {
        Id = -1;
        Tags = new String[tags.length];
        System.arraycopy(tags, 0, Tags, 0, tags.length);
        VerticalPhotoIds = new int[ids.length];
        System.arraycopy(ids, 0, VerticalPhotoIds, 0, ids.length);
    }

    boolean isCommonTagsExist(Photo photo) {
        var comparator = new PhotoComparator(this, photo);
        return comparator.isCommonTagsExist();
    }

    int getCommonTagsCount(Photo photo) {
        var comparator = new PhotoComparator(this, photo);
        return comparator.getCommonTags().size();
    }

    Comparator<Photo> order() {
        return (p1, p2) -> {
            if (p1 == null || p2 == null) {
                throw new NullPointerException("Arguments should not be null!");
            }

            int count1 = this.getCommonTagsCount(p1);
            int count2 = this.getCommonTagsCount(p2);

            return Integer.compare(count1, count2);
        };
    }
}
