package com.softbard.easysms;

import java.util.List;

public class UserModel {
    private List<String> clint_id;
    private List<String> cm_name;
    private List<String> product;
    private List<String> total_os;
    private List<String> minimum;
    private List<String> over;
    private List<String> mobile;

    public UserModel(List<String> clint_id, List<String> cm_name, List<String> product, List<String> total_os, List<String> minimum, List<String> over, List<String> mobile) {
        this.clint_id = clint_id;
        this.cm_name = cm_name;
        this.product = product;
        this.total_os = total_os;
        this.minimum = minimum;
        this.over = over;
        this.mobile = mobile;
    }

    public List<String> getClint_id() {
        return clint_id;
    }

    public void setClint_id(List<String> clint_id) {
        this.clint_id = clint_id;
    }

    public List<String> getCm_name() {
        return cm_name;
    }

    public void setCm_name(List<String> cm_name) {
        this.cm_name = cm_name;
    }

    public List<String> getProduct() {
        return product;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }

    public List<String> getTotal_os() {
        return total_os;
    }

    public void setTotal_os(List<String> total_os) {
        this.total_os = total_os;
    }

    public List<String> getMinimum() {
        return minimum;
    }

    public void setMinimum(List<String> minimum) {
        this.minimum = minimum;
    }

    public List<String> getOver() {
        return over;
    }

    public void setOver(List<String> over) {
        this.over = over;
    }

    public List<String> getMobile() {
        return mobile;
    }

    public void setMobile(List<String> mobile) {
        this.mobile = mobile;
    }
}
