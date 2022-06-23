package mileage.mileage_be.review.domain;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
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
