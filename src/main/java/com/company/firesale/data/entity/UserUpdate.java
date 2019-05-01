package com.company.firesale.data.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdate {

    @NotEmpty
    private String userName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @UpdateTimestamp
    private LocalDateTime updated;

}
