package com.iat.contracts.transactions;

public enum IdType {
    UUID, EMAIL;

    @Override
    public String toString() {
        return name();
    }
}
