package com.example.lxy.androiddemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by lxy on 2016/7/14.
 */
@Entity
public class Note implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String content;
    private long date;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1718584509)
    public Note(Long id, String title, String content, long date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeLong(this.date);
    }

    protected Note(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.content = in.readString();
        this.date = in.readLong();
    }

    @Transient
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
