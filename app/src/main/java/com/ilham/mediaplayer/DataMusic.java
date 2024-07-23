package com.ilham.mediaplayer;

public class DataMusic {
    private int _file;
    private String _judul;
    private int _gambar;

    public DataMusic(int _file, String _judul, int _gambar) {
        this._file = _file;
        this._judul = _judul;
        this._gambar = _gambar;
    }

    public int get_gambar() {
        return _gambar;
    }

    public int get_file() {
        return _file;
    }

    public String get_judul() {
        return _judul;
    }

    public void set_file(int _file) {
        this._file = _file;
    }

    public void set_judul(String _judul) {
        this._judul = _judul;
    }

    public void set_gambar(int _gambar) {
        this._gambar = _gambar;
    }
}
