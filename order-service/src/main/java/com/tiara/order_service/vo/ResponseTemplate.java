package com.tiara.order_service.vo;

import com.tiara.order_service.model.Order;

public class ResponseTemplate {
    penggajian order;
    Produk produk;
    Pelanggan pelanggan;

    

    public ResponseTemplate(penggajian order, Pelanggan pelanggan, Produk produk) {
        this.order = order;
        this.pelanggan = pelanggan;
        this.produk = produk;
    }

    public ResponseTemplate() {
    }

    public penggajian getOrder() {
        return order;
    }

    public void setOrder(penggajian order) {
        this.order = order;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }



}
