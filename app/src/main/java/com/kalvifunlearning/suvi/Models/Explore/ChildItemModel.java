package com.kalvifunlearning.suvi.Models.Explore;

import android.os.Parcel;
import android.os.Parcelable;

public class ChildItemModel implements Parcelable {
    String videoName,videoLink,subject,section,language,description,standard,category,board;
    Boolean open;



    public ChildItemModel(String videoName, String videoLink, String subject, String section, String language, String description, String standard, String category, String board, Boolean open) {
        this.videoName = videoName;
        this.videoLink = videoLink;
        this.subject = subject;
        this.section = section;
        this.language = language;
        this.description = description;
        this.standard = standard;
        this.category = category;
        this.board = board;
        this.open = open;
    }

    protected ChildItemModel(Parcel in) {
        videoName = in.readString();
        videoLink = in.readString();
        subject = in.readString();
        section = in.readString();
        language = in.readString();
        description = in.readString();
        standard = in.readString();
        category = in.readString();
        board = in.readString();
        byte tmpOpen = in.readByte();
        open = tmpOpen == 0 ? null : tmpOpen == 1;
    }

    public static final Creator<ChildItemModel> CREATOR = new Creator<ChildItemModel>() {
        @Override
        public ChildItemModel createFromParcel(Parcel in) {
            return new ChildItemModel(in);
        }

        @Override
        public ChildItemModel[] newArray(int size) {
            return new ChildItemModel[size];
        }
    };

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoName);
        dest.writeString(videoLink);
        dest.writeString(subject);
        dest.writeString(section);
        dest.writeString(language);
        dest.writeString(description);
        dest.writeString(standard);
        dest.writeString(category);
        dest.writeString(board);
        dest.writeByte((byte) (open == null ? 0 : open ? 1 : 2));
    }
}
