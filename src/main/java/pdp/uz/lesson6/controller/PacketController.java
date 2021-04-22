package pdp.uz.lesson6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.lesson6.entity.Packet;
import pdp.uz.lesson6.payload.ApiResponse;
import pdp.uz.lesson6.payload.PacketDto;
import pdp.uz.lesson6.payload.PacketDtoForClients;
import pdp.uz.lesson6.service.PacketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/packet")
public class PacketController {
    @Autowired
    PacketService packetService;

    /*
    paket qo'shish managerlar uchun
     */
    @PreAuthorize(value = "hasRole('ROLE_MANGER')")
    @PostMapping("/addPacket")
    public HttpEntity<?> addPacket(@RequestBody PacketDto packetDto) {
        ApiResponse apiResponse = packetService.addPacket(packetDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /*
    barcha paketlar ro'yxati xodimlar uchun
     */
    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER','ROLE_STAFF')")
    @GetMapping("/getAllPacketsForStaff")
    public HttpEntity<?> getAllPackets() {
        List<Packet> packetList=packetService.getAllPackets();
        return ResponseEntity.ok(packetList);
    }

    /*
    paketni update qilish paket manageri uchun
     */
    @PreAuthorize(value = "hasRole('ROLE_MANGER')")
    @PostMapping("/editPacket/{id}")
    public HttpEntity<?> editPacket(@RequestBody PacketDtoForClients packetDto, @PathVariable UUID id) {
        ApiResponse apiResponse = packetService.editPacket(packetDto,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /*
    paketni o'chirish paket manageri uchun
     */
    @PreAuthorize(value = "hasRole('ROLE_MANGER')")
    @DeleteMapping("/deletePacket/{id}")
    public HttpEntity<?> deletePacket(@PathVariable UUID id){
        ApiResponse apiResponse=packetService.deletePacket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /*
    paketlar ro'yxati clientlar uchun
     */
    @PreAuthorize(value = "hasRole('ROLE_CLIENT')")
    @GetMapping("/getAllPackets")
    public HttpEntity<?> getAllPacketsForClients() {
        List<String> packetList=packetService.getAllPacketsForClients();
        return ResponseEntity.ok(packetList);
    }

    /*
   bitta paket haqida malumotini qaytarish clientlar uchun
    */
    @PreAuthorize(value = "hasRole('ROLE_CLIENT')")
    @GetMapping("/getPacketInfo/{packetName}")
    public HttpEntity<?> getPacketInfo(@PathVariable String packetName) {
        PacketDtoForClients packetInfo=packetService.getPacketInfo(packetName);
        return ResponseEntity.ok(packetInfo);
    }


}
