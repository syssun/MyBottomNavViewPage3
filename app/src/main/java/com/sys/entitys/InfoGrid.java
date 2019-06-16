package com.sys.entitys;

public class InfoGrid {
    private String label;
    private String value;
    private Integer icon;
    private boolean flag = false;

    public InfoGrid(String label, String value) {
        this.label = label;
        this.value = value;

    }
    public InfoGrid(String label, String value,boolean flag) {
        this.label = label;
        this.value = value;
        this.flag = flag ;

    }
    public InfoGrid(String label, String value, Integer icon, boolean flag) {
        this.label = label;
        this.value = value;
        this.icon = icon;
        this.flag = flag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
