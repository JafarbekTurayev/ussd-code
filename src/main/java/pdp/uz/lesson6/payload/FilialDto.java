package pdp.uz.lesson6.payload;

import lombok.Data;
import pdp.uz.lesson6.entity.Staff;

import java.util.List;

@Data
public class FilialDto {
    private String name;

    private String DirectorUserName;

    private String DirectorFullName;

    private Integer DirectorRoleId;

    private Integer filialId;

    private String DirectorPosition;

    private String DirectorPassword;

    private List<String> StaffUsernames;
}
