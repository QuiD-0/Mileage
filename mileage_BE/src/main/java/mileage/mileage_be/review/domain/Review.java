package mileage.mileage_be.review.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(indexes = @Index(columnList = "reviewId"))
public class Review {
    @Id
    private String reviewId;
    @Setter
    private String content;
    private String userId;
    private String placeId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
