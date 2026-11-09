package com.mycompany.hotelsystem;

public class RuangLobby {
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