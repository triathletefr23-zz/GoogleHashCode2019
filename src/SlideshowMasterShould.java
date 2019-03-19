import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class SlideshowMasterShould {
    private final static String a_example_filename = "data\\a_example.txt";
    private final static String b_lovely_landscapes_filename = "data\\b_lovely_landscapes.txt";
    private final static String c_memorable_moments_filename = "data\\c_memorable_moments.txt";
    private final static String d_pet_pictures_filename = "data\\d_pet_pictures.txt";
    private final static String e_shiny_selfies = "data\\e_shiny_selfies.txt";

    private final static String test5_file = "data\\Tests\\test5.txt";
    private final static String test8_file = "data\\Tests\\test8.txt";

    private SlideshowMaster master;

    @Test(expected = IllegalArgumentException.class)
    public void ThrowNullParameterExceptionIfFIlePathIsNull() {
        new SlideshowMaster(null);
    }

    @Test
    public void ReadExampleFile() {
        master = new SlideshowMaster(a_example_filename);
        Assert.assertEquals(4, master.getPhotosCount());
    }

    @Test
    public void CreateSlideShow_A() {
        master = new SlideshowMaster(a_example_filename);
        var slides = master.createSlideshow();
        Assert.assertEquals(3, slides.size());
    }

    @Test
    public void CreateSlideShow_LANDSCAPES() {
        master = new SlideshowMaster(b_lovely_landscapes_filename);
        var slides = master.createSlideshow();
        Assert.assertTrue(slides.size() > 0);
    }

    @Test
    public void CreateSlideShow_MOMENTS() {
        var start = Instant.now();
        master = new SlideshowMaster(c_memorable_moments_filename);
        var next = Instant.now();
        var slides = master.createSlideshow();
        var finish = Instant.now();
        var task1 = Duration.between(start, next).toMillis();
        var task2 = Duration.between(next, finish).toMillis();
        Assert.assertTrue(slides.size() > 0);
    }

    @Test
    public void CreateSlideShow_PETS() {
        master = new SlideshowMaster(d_pet_pictures_filename);
        var slides = master.createSlideshow();
        Assert.assertTrue(slides.size() > 0);
    }

    @Test
    public void CreateSlideShow_SELFIES() {
        master = new SlideshowMaster(e_shiny_selfies);
        var slides = master.createSlideshow();
        Assert.assertTrue(slides.size() > 0);
    }

    @Test
    public void WriteOutputFile_ExampleA() {
        master = new SlideshowMaster(a_example_filename);
        master.createSlideshow();
        master.writeSlideShow();
        var outputFilePath = Helper.getFileName(a_example_filename);
        var isExists = Helper.exists(outputFilePath);
        Assert.assertTrue(isExists);
    }

    @Test
    public void WriteOutputFile_ExampleB() {
        master = new SlideshowMaster(b_lovely_landscapes_filename);
        master.createSlideshow();
        master.writeSlideShow();
        var outputFilePath = Helper.getFileName(c_memorable_moments_filename);
        var isExists = Helper.exists(outputFilePath);
        Assert.assertTrue(isExists);
    }

    @Test
    public void WriteOutputFile_ExampleC() {
        var start = Instant.now();
        master = new SlideshowMaster(c_memorable_moments_filename);
        master.createSlideshow();
        master.writeSlideShow();
        var finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        var outputFilePath = Helper.getFileName(c_memorable_moments_filename);
        var isExists = Helper.exists(outputFilePath);
        Assert.assertTrue(isExists);
    }

    @Test
    public void WriteOutputFile_ExampleD() {
        master = new SlideshowMaster(d_pet_pictures_filename);
        master.createSlideshow();
        master.writeSlideShow();
        var outputFilePath = Helper.getFileName(c_memorable_moments_filename);
        var isExists = Helper.exists(outputFilePath);
        Assert.assertTrue(isExists);
    }

    @Test
    public void WriteOutputFile_ExampleE() {
        master = new SlideshowMaster(e_shiny_selfies);
        master.createSlideshow();
        master.writeSlideShow();
        var outputFilePath = Helper.getFileName(c_memorable_moments_filename);
        var isExists = Helper.exists(outputFilePath);
        Assert.assertTrue(isExists);
    }
//    @Test
//    public void ReadExampleFileAndReturnPhotos() {
//        master = new SlideshowMaster(a_example_filename);
//        Assert.assertEquals(2, master.getHorizontalsCount());
//        Assert.assertEquals(2, master.getVerticalsCount());
//    }
//
//    @Test
//    public void ReadThirdFileAndReturnPhotos() {
//        master = new SlideshowMaster(c_memorable_moments_filename);
//        Assert.assertEquals(1000, master.getHorizontalsCount() + master.getVerticalsCount());
//    }
//    @Test
//    public void TransformVerticalsIntoPairs_A() {
//        master = new SlideshowMaster(a_example_filename);
//        var pairs = master.transformVerticalsForCurrentExample();
//        Assert.assertEquals(1, pairs.size());
//    }
//
//    @Test
//    public void TransformVerticalsIntoPairs_Test5() {
//        master = new SlideshowMaster(test5_file);
//        var pairs = master.transformVerticalsForCurrentExample();
//        Assert.assertEquals(1, pairs.size());
//    }
//
//    @Test
//    public void TransformVerticalsIntoPairs_Test8() {
//        master = new SlideshowMaster(test8_file);
//        var pairs = master.transformVerticalsForCurrentExample();
//        Assert.assertEquals(1, pairs.size());
//    }
}
