package org.alvarowau.model;

public record User(int id, String username, String email,
                   Address address, String phone, String webSite, Company company) {
}
