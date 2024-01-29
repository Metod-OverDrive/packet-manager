package com.example.packetmanager;

import com.example.packetmanager.domain.packet.Packet;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PacketManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacketManagerApplication.class, args);

    }

}
