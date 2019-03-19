import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class PhotoComparator {
    private final Photo p1;
    private final Photo p2;

    PhotoComparator(Photo p1, Photo p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    List<String> getCommonTags() {
        var commonTags = Lists.newArrayList(p1.Tags);
        commonTags.retainAll(new ArrayList<>(Arrays.asList(p2.Tags)));

//        var nonCommonFirstTags = new ArrayList<>();
//        var nonCommonSecondTags = new ArrayList<>();

//        for (var tag: p1.Tags) {
//            if (!commonTags.contains(tag)) nonCommonFirstTags.add(tag);
//        }
//
//        for (var tag: p2.Tags) {
//            if (!commonTags.contains(tag)) nonCommonSecondTags.add(tag);
//        }

        return commonTags;
    }

    boolean isCommonTagsExist() {
        return this.getCommonTags().size() > 0;
    }
}
