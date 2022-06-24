package mileage.mileage_be.user.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
@Table(indexes = @Index(columnList = "userId"))
public class User {
    @Id
    private String userId;
    @Setter
    private int point;
}
