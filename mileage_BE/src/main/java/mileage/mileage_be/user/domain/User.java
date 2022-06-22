package mileage.mileage_be.user.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
public class User {
    @Id
    private String userId;
    @Setter
    private int point;
}
