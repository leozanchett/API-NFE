package com.viasofttest.apinfe.entity;

import com.viasofttest.apinfe.enums.StatusNFE;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_nfestatus")
public class NFE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autorizador;
    @Enumerated(EnumType.STRING)
    private StatusNFE status;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private final LocalDateTime data = LocalDateTime.now();


    public NFE(String autorizador, StatusNFE status) {
        this.autorizador = autorizador;
        this.status = status;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public StatusNFE getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "NFE{" +
                "id=" + id +
                ", autorizador='" + autorizador + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
