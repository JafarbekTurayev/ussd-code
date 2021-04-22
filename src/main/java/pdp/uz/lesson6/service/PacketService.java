package pdp.uz.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.lesson6.entity.Packet;
import pdp.uz.lesson6.entity.Tariff;
import pdp.uz.lesson6.entity.enums.PacketType;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.PacketDto;
import pdp.uz.lesson6.payload.PacketDtoForClients;
import pdp.uz.lesson6.repository.PacketRepository;
import pdp.uz.lesson6.repository.TariffRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacketService {
    @Autowired
    PacketRepository packetRepository;
    @Autowired
    TariffRepository tariffRepository;

    /*
    packetni qo'shish , packetDto da name , packetTypeId , amount , duration,
    qaysi tariflarga mo'ljallanganligi keladi
     */
    public ApiResponse addPacket(PacketDto packetDto) {
        boolean exists = packetRepository.existsByName(packetDto.getName());
        if (exists)
            return new ApiResponse("Bunday nomli paket mavjud", false);
        Packet packet = new Packet();
        packet.setName(packetDto.getName());
        if (packetDto.getPacketTypeId() == 1) {
            packet.setPacketType(PacketType.MB);
        } else if (packetDto.getPacketTypeId() == 2) {
            packet.setPacketType(PacketType.SMS);
        } else if (packetDto.getPacketTypeId() == 3) {
            packet.setPacketType(PacketType.MIN);
        } else {
            return new ApiResponse("Wrong packet type id !", false);
        }
        packet.setAmount(packetDto.getAmount());
        packet.setCost(packetDto.getCost());
        packet.setDuration(packetDto.getDuration());
        packet.setTariff(packetDto.isTariff());
        List<Tariff> tariffList = new ArrayList<>();
        List<String> availableTariffs = packetDto.getAvailableTariffs();
        for (String availableTariff : availableTariffs) {
            Tariff tariff = tariffRepository.findByName(availableTariff);
            tariffList.add(tariff);
        }
        packet.setAvailableTariffs(tariffList);
        packetRepository.save(packet);
        return new ApiResponse("Packet saved", true);

    }

    /*
    barcha packetlar listini qaytaradi
     */
    public List<Packet> getAllPackets() {
        return packetRepository.findAll();
    }

    /*
    paketlar ro'yxatini clientlar uchun qaytaramiz
     */
    public List<String> getAllPacketsForClients() {
        List<Packet> packetList = packetRepository.findAll();
        List<String> packetsName = new ArrayList<>();
        for (Packet packet : packetList) {
            packetsName.add(packet.getName());
        }
        return packetsName;
    }


    /*
    paketni ozgartirish nomi muddati narxi yoki miqdorini
     */
    public ApiResponse editPacket(PacketDtoForClients packetDto, UUID id) {
        Optional<Packet> byId = packetRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Paket id si xato !", false);
        Packet packet = byId.get();
        packet.setName(packetDto.getName());
        packet.setAmount(packetDto.getAmount());
        packet.setCost(packetDto.getCost());
        packet.setDuration(packetDto.getDuration());
        packetRepository.save(packet);
        return new ApiResponse("Paket o'zgartirildi", true);
    }

    /*
    paketni o'chirib yuborish
     */
    public ApiResponse deletePacket(UUID id) {
        Optional<Packet> optionalPacket = packetRepository.findById(id);
        if (!optionalPacket.isPresent())
            return new ApiResponse("Id wrong !", false);
        Packet packet = optionalPacket.get();
        packetRepository.delete(packet);
        return new ApiResponse("Packet deleted !", true);
    }

    /*
    paketni nomi bo'yicha topib undagi malumotlarni
    client uchun mo'ljallangan classga o'tkazib qaytaramiz
     */
    public PacketDtoForClients getPacketInfo(String packetName) {
        Packet packet = packetRepository.findByName(packetName);
        PacketDtoForClients packetDto = new PacketDtoForClients();
        packetDto.setName(packet.getName());
        packetDto.setPacketType(packet.getPacketType());
        packetDto.setTariff(packet.isTariff());
        packetDto.setDuration(packet.getDuration());
        packetDto.setAmount(packet.getAmount());
        packetDto.setCost(packet.getCost());
        List<Tariff> availableTariffs = packet.getAvailableTariffs();
        List<String> tariffsList = new ArrayList<>();
        for (Tariff availableTariff : availableTariffs) {
            tariffsList.add(availableTariff.getName());
        }
        packetDto.setAvailableTariffs(tariffsList);
        return packetDto;
    }
}
