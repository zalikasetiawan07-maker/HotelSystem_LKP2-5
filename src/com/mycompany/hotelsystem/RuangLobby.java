package com.mycompany.hotelsystem;

import java.io.Serializable;

public class RuangLobby implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaLobby;
    private int kapasitasLounge;

    public RuangLobby(String namaLobby, int kapasitasLounge) {
        this.namaLobby = namaLobby;
        this.kapasitasLounge = kapasitasLounge;
    }

    public void bukaLobby() {
        System.out.println("-> [Komposisi] " + namaLobby + " telah dibuka. Kapasitas: " + kapasitasLounge + " tamu.");
    }
}