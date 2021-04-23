package pdp.uz.lesson6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.lesson6.entity.Packet;
import pdp.uz.lesson6.entity.enums.PacketType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PacketRepository extends JpaRepository<Packet, UUID> {
    boolean existsByName(String name);

    Packet findByName(String name);

    Optional<Packet> findByPacketTypeAndAmount(PacketType packetType, int amount);
}
