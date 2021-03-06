package com.frank.dev.model;


import com.orm.SugarRecord;

import java.util.Date;

public class MemoriaAtividade extends SugarRecord {

    public MemoriaAtividade(String chave, String titulo, String descricao, String imagem1, String imagem2, String imagem3, Double latitude, Double longitude, Date dataMemoria) {
        this.chave = chave;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataMemoria = dataMemoria;
    }

    public MemoriaAtividade() {

    }
    private String chave;
    private String titulo;
    private String descricao;
    private String imagem1;
    private String imagem2;
    private String imagem3;
    private Double latitude;
    private Double longitude;
    private Date dataMemoria = new Date();

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getDataMemoria() {
        return dataMemoria;
    }

    public void setDataMemoria(Date dataMemoria) {
        this.dataMemoria = dataMemoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem1() {
        return imagem1;
    }

    public void setImagem1(String imagem1) {
        this.imagem1 = imagem1;
    }

    public String getImagem2() {
        return imagem2;
    }

    public void setImagem2(String imagem2) {
        this.imagem2 = imagem2;
    }

    public String getImagem3() {
        return imagem3;
    }

    public void setImagem3(String imagem3) {
        this.imagem3 = imagem3;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
