package mileage.mileage_be.review.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mileage.mileage_be.user.domain.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Photo {
    @Id
    @Column
    private String attachedPhotoIDs;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
