package com.github.bartlomiejpietrzyk.server;

public class ServerOrderDto {
    private String id;
    private String ip;
    private String name;
    private String type;
    private String game;
    private String hosting;
    private String slot;
    private String user;
    private String paymentStatus;
    private String rentStart;
    private String rentExpire;
    private String isPaid;
    private String active;


    public ServerOrderDto() {
    }

    public ServerOrderDto(Server that) {
        this.id = String.valueOf(that.getId());
        this.ip = that.getIp();
        this.name = that.getName();
        this.type = String.valueOf(that.getType());
        this.game = that.getGame().getTitle();
        this.hosting = String.valueOf(that.getHosting());
        this.slot = that.getSlot();
        this.user = String.valueOf(that.getUser());
        this.paymentStatus = that.getPaymentStatus();
        this.rentStart = String.valueOf(that.getRentStart());
        this.rentExpire = String.valueOf(that.getRentExpire());
        this.isPaid = String.valueOf(that.getPaid());
        this.active = String.valueOf(that.getActive());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHosting() {
        return hosting;
    }

    public void setHosting(String hosting) {
        this.hosting = hosting;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRentStart() {
        return rentStart;
    }

    public void setRentStart(String rentStart) {
        this.rentStart = rentStart;
    }

    public String getRentExpire() {
        return rentExpire;
    }

    public void setRentExpire(String rentExpire) {
        this.rentExpire = rentExpire;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}