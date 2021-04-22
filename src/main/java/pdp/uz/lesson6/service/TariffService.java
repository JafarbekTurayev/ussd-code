package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.repository.PacketRepository;

@Service
public class PacketService {

    @Autowired
    PacketRepository packetRepository;

}
