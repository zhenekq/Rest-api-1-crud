package com.epam.msu.entity;

import java.util.Calendar;
import java.util.Objects;


public class Certificate {

    private long id;
    private String name;
    private String description;
    private long price;
    private Calendar date;
    private Calendar createDate;
    private Calendar lastUpdateDate;

    public Certificate() {
    }

    public Certificate(long id, String name, String description, long price, Calendar date, Calendar createDate, Calendar lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return getId() == that.getId() && getPrice() == that.getPrice() && getName().equals(that.getName()) && getDescription().equals(that.getDescription()) && getDate().equals(that.getDate()) && getCreateDate().equals(that.getCreateDate()) && getLastUpdateDate().equals(that.getLastUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getDate(), getCreateDate(), getLastUpdateDate());
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
