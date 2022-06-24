package mileage.mileage_be.review.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Event {
    private String type;
    private Action_type action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
}

