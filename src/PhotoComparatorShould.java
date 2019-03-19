import org.junit.Assert;
import org.junit.Test;

public class PhotoComparatorShould {
    private final static String a_example_filename = "data\\a_example.txt";

    private PhotoComparator comparator;
    private final SlideshowMaster master;

    public PhotoComparatorShould() {
        master = new SlideshowMaster(a_example_filename);
    }

    void useHorizonalPhotos() {
        var photos = master.getPhotos();
        comparator = new PhotoComparator(photos[0], photos[photos.length - 1]);
    }

//    @Test
//    public void CalculateScoreForFirstAndLastPhoto() {
//        useHorizonalPhotos();
//        Assert.assertEquals(1, comparator.findScore());
//    }
//
//    @Test
//    public void CalculateScoreForFirstAndLastPhoto1() {
//        useHorizonalPhotos();
//        Assert.assertEquals(1, comparator.getCommonTagsCount());
//    }
//
//    @Test
//    public void CalculateScoreForFirstAndLastPhoto2() {
//        useHorizonalPhotos();
//        Assert.assertEquals(2, comparator.getNonCommonFirstTagsCount());
//    }
//
//    @Test
//    public void CalculateScoreForFirstAndLastPhoto3() {
//        useHorizonalPhotos();
//        Assert.assertEquals(1, comparator.getNonCommonSecondTagsCount());
//    }
}
