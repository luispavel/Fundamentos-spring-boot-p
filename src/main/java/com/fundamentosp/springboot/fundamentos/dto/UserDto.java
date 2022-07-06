package com.fundamentosp.springboot.fundamentos.dto;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String name;
    private LocalDate bitrhDate;

    public UserDto(Long id, String name, LocalDate bitrhDate) {
        this.id = id;
        this.name = name;
        this.bitrhDate = bitrhDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bitrhDate='" + bitrhDate + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBitrhDate() {
        return bitrhDate;
    }

    public void setBitrhDate(LocalDate bitrhDate) {
        this.bitrhDate = bitrhDate;
    }
}
