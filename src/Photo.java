class Photo {
    public final int Id;
    public final String[] Tags;

    public Photo(int id, String[] tags) {
        Id = id;
        Tags = new String[tags.length];
        System.arraycopy(tags, 0, Tags, 0, tags.length);
    }
}
