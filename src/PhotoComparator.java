import com.google.common.collect.Lists;

import java.util.*;

class PhotoComparator {
    private List<String> commonTags;
    private List<String> nonCommonFirstTags;
    private List<String> nonCommonSecondTags;

    PhotoComparator(Photo p1, Photo p2) {
        this.commonTags = Lists.newArrayList(p1.Tags);
        this.nonCommonFirstTags = new ArrayList<>();
        this.nonCommonSecondTags = new ArrayList<>();

        this.commonTags.retainAll(new ArrayList<>(Arrays.asList(p2.Tags)));
        for (var tag: p1.Tags) {
            if (!commonTags.contains(tag)) this.nonCommonFirstTags.add(tag);
        }

        for (var tag: p2.Tags) {
            if (!commonTags.contains(tag)) this.nonCommonSecondTags.add(tag);
        }
    }

    int getCommonTagsCount() {
        return this.commonTags.size();
    }

    int getNonCommonFirstTagsCount() {
        return this.nonCommonFirstTags.size();
    }

    int getNonCommonSecondTagsCount() {
        return this.nonCommonSecondTags.size();
    }

    int findScore() {
        return Math.min(this.commonTags.size(),
                Math.min(this.nonCommonFirstTags.size(),
                        this.nonCommonSecondTags.size()));
    }
}
