package comisapolive.app.model;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010GJ\u000b\u0010K\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\bH\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u00109J\u0086\u0001\u0010T\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00c6\u0001\u00a2\u0006\u0002\u0010UJ\u0013\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010Y\u001a\u00020\bH\u00d6\u0001J\t\u0010Z\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082D\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00168F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0013R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0019\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00168F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u0018R\u0013\u0010#\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u0013R\u0013\u0010%\u001a\u0004\u0018\u00010\u00038F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0013R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0019\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00168F\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u0018R\u0011\u0010,\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b1\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0013R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0013R\u0011\u00104\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b5\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0013R\u001a\u0010\f\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010:\u001a\u0004\b8\u00109R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0013R\u0019\u0010<\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010\u00168F\u00a2\u0006\u0006\u001a\u0004\b>\u0010\u0018R\u0013\u0010?\u001a\u0004\u0018\u00010@8F\u00a2\u0006\u0006\u001a\u0004\bA\u0010BR\u0019\u0010C\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010\u00168F\u00a2\u0006\u0006\u001a\u0004\bE\u0010\u0018R\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010H\u001a\u0004\bF\u0010G\u00a8\u0006["}, d2 = {"Lcomisapolive/app/model/Liver;", "", "id", "", "originalId", "name", "platform", "followers", "", "imageUrl", "actualImageUrl", "detailUrl", "pageNumber", "updatedAt", "", "details", "Lcomisapolive/app/model/LiverDetailsData;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcomisapolive/app/model/LiverDetailsData;)V", "getActualImageUrl", "()Ljava/lang/String;", "baseURL", "categories", "", "getCategories", "()Ljava/util/List;", "channelUrl", "getChannelUrl", "collaboOK", "getCollaboOK", "collaborationComment", "getCollaborationComment", "collaborationStatus", "getCollaborationStatus", "comments", "getComments", "detailFollowers", "getDetailFollowers", "detailName", "getDetailName", "getDetailUrl", "getDetails", "()Lcomisapolive/app/model/LiverDetailsData;", "eventInfo", "getEventInfo", "followerDisplayText", "getFollowerDisplayText", "getFollowers", "()I", "fullImageURL", "getFullImageURL", "getId", "getImageUrl", "mainComment", "getMainComment", "getName", "getOriginalId", "getPageNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPlatform", "profileImages", "Lcomisapolive/app/model/ProfileImage;", "getProfileImages", "profileInfo", "Lcomisapolive/app/model/ProfileInfo;", "getProfileInfo", "()Lcomisapolive/app/model/ProfileInfo;", "schedules", "Lcomisapolive/app/model/Schedule;", "getSchedules", "getUpdatedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Lcomisapolive/app/model/LiverDetailsData;)Lcomisapolive/app/model/Liver;", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class Liver {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @com.google.gson.annotations.SerializedName(value = "originalId")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String originalId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String platform = null;
    private final int followers = 0;
    @com.google.gson.annotations.SerializedName(value = "imageUrl")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String imageUrl = null;
    @com.google.gson.annotations.SerializedName(value = "actualImageUrl")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String actualImageUrl = null;
    @com.google.gson.annotations.SerializedName(value = "detailUrl")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String detailUrl = null;
    @com.google.gson.annotations.SerializedName(value = "pageNumber")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer pageNumber = null;
    @com.google.gson.annotations.SerializedName(value = "updatedAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long updatedAt = null;
    @org.jetbrains.annotations.Nullable()
    private final comisapolive.app.model.LiverDetailsData details = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String baseURL = "https://liver-scraper-main.pwaserve8.workers.dev";
    
    public Liver(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String originalId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String platform, int followers, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String actualImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String detailUrl, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pageNumber, @org.jetbrains.annotations.Nullable()
    java.lang.Long updatedAt, @org.jetbrains.annotations.Nullable()
    comisapolive.app.model.LiverDetailsData details) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOriginalId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlatform() {
        return null;
    }
    
    public final int getFollowers() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActualImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDetailUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPageNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUpdatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final comisapolive.app.model.LiverDetailsData getDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFullImageURL() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFollowerDisplayText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMainComment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannelUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCollaborationStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCollaborationComment() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCollaboOK() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDetailName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDetailFollowers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final comisapolive.app.model.ProfileInfo getProfileInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<comisapolive.app.model.ProfileImage> getProfileImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getEventInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getComments() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<comisapolive.app.model.Schedule> getSchedules() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final comisapolive.app.model.LiverDetailsData component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final comisapolive.app.model.Liver copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String originalId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String platform, int followers, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String actualImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String detailUrl, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pageNumber, @org.jetbrains.annotations.Nullable()
    java.lang.Long updatedAt, @org.jetbrains.annotations.Nullable()
    comisapolive.app.model.LiverDetailsData details) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}