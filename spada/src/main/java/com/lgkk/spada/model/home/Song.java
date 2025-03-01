package com.lgkk.spada.model.home;

import lombok.Data;

@Data
public class Song {
    public long albumId;
    public String albumName;
    public long artistId;
    public String artistName;
    public int duration;
    public long id;
    public String title;
    public int trackNumber;
    public float playCountScore;
    public String path;


    public Song() {
        this.id = -1;
        this.albumId = -1;
        this.artistId = -1;
        this.title = "";
        this.artistName = "";
        this.albumName = "";
        this.duration = -1;
        this.trackNumber = -1;
        this.path = "";
    }

    public Song(long _id, long _albumId, long _artistId, String _title, String _artistName,
                String _albumName, int _duration, int _trackNumber) {
        this.id = _id;
        this.albumId = _albumId;
        this.artistId = _artistId;
        this.title = _title;
        this.artistName = _artistName;
        this.albumName = _albumName;
        this.duration = _duration;
        this.trackNumber = _trackNumber;
        this.path = "";
    }

    public Song(long _id, long _albumId, long _artistId, String _title, String _artistName,
                String _albumName, int _duration, int _trackNumber, String _path) {
        this.id = _id;
        this.albumId = _albumId;
        this.artistId = _artistId;
        this.title = _title;
        this.artistName = _artistName;
        this.albumName = _albumName;
        this.duration = _duration;
        this.trackNumber = _trackNumber;
        this.path = _path;
    }
}
