package pdp.uz.lesson6.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pdp.uz.lesson6.entity.Staff;

import java.util.Optional;
import java.util.UUID;

public class KimYozganiBilishUchun implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")){
            Staff employee = (Staff) authentication.getPrincipal();
            return Optional.of(employee.getId());
        }
            return Optional.empty();

    }

}
