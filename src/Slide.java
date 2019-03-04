import java.util.ArrayList;

public class Slide {
    public ArrayList<String> CommonTags;
    public int Id;
    public int OptId;

    public Slide(ArrayList<String> tags, int... ids) {
        CommonTags = new ArrayList<>(tags);
        Id = ids[0];
        if (ids.length == 2) OptId = ids[1];
    }
}
