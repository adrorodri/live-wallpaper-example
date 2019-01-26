package com.adrorodri.livewallpapertest.model;

public class DisplayParams {
    private int heigntPx;
    private int widthPx;

    public DisplayParams(int heigntPx, int widthPc) {
        this.heigntPx = heigntPx;
        this.widthPx = widthPc;
    }

    public int getHeigntPx() {
        return heigntPx;
    }

    public void setHeigntPx(int heigntPx) {
        this.heigntPx = heigntPx;
    }

    public int getWidthPx() {
        return widthPx;
    }

    public void setWidthPx(int widthPx) {
        this.widthPx = widthPx;
    }
}
